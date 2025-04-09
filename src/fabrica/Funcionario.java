package fabrica;

import java.util.concurrent.Semaphore;

import carro.Carro;

public class Funcionario extends Thread {
    private final int posicaoCircular;  // 0 a 4
    private final EstacaoMontagem estacao;

    public Funcionario(int posicaoCircular, EstacaoMontagem estacao) {
        this.posicaoCircular = posicaoCircular;
        this.estacao = estacao;
    }

    @Override
    public void run() {
        EstoquePecas estoque = estacao.getEstoque();
        EsteiraFabrica esteira = estacao.getEsteiraFabrica();
        Semaphore ferramentaEsquerda = estacao.getFerramenta(posicaoCircular);
        Semaphore ferramentaDireita = estacao.getFerramenta((posicaoCircular + 1) % 5);

        while (true) {
            // Verifica se a produção foi encerrada
            if (estacao.isProducaoEncerrada()) {
                break;
            }

            // Tenta consumir peça (se não conseguir, para)
            if (!estoque.consumirPeca()) {
                System.out.println("Funcionario " + posicaoCircular + " da estação " + estacao.getId()
                        + " parou: estoque esgotado.");
                break;
            }

            System.out.println("Peças disponiveis: " + estoque.getPecasDisponiveis()); 
            
            try {
                // Estratégia para evitar deadlock
                if (posicaoCircular < 4) {
                    ferramentaEsquerda.acquire();
                    ferramentaDireita.acquire();
                } else {
                    ferramentaDireita.acquire();
                    ferramentaEsquerda.acquire();
                }

                Thread.sleep(500); // Simula tempo de produção

                Carro carro = new Carro(estacao.getId(), posicaoCircular);
                int posicao = esteira.adicionarCarro(carro);

                LogFabrica.registrarProducao(carro);

                /*System.out.println("Funcionario " + posicaoCircular + " da estação " + estacao.getId()
                        + " produziu carro na posição " + posicao);*/

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            } finally {
                // Garante liberação das ferramentas
                ferramentaEsquerda.release();
                ferramentaDireita.release();
            }

            try {
                Thread.sleep(500); // Descanso após produção
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
