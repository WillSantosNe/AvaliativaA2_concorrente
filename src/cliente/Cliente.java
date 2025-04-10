package cliente;

import java.util.Random;

import carro.Carro;
import loja.LogLoja;
import loja.Loja;

public class Cliente extends Thread {
    private final int idCliente;
    private final Loja[] lojasDisponiveis;
    private final GaragemCliente garagem;
    private final Random random = new Random();

    public Cliente(int idCliente, Loja[] lojas) {
        this.idCliente = idCliente;
        this.lojasDisponiveis = lojas;
        this.garagem = new GaragemCliente();
    }

    @Override
    public void run()	 {
        while (algumaLojaAtiva()) {
        	// Pegando uma loja aleatoriamente
            Loja loja = lojasDisponiveis[random.nextInt(lojasDisponiveis.length)];
            
            // Compra carro
            Carro carro = loja.venderCarroParaCliente(); // pode esperar

            // Se conseguiu pegar o carro
            if (carro != null) {
            	
            	// Adiciona na garagem
                garagem.adicionar(carro);
                
                System.out.println("Cliente " + idCliente + " comprou");
                
                // Registra log da venda o carro
                LogLoja.registrarVenda(carro.getLojaDestino(), carro, idCliente);
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Cliente " + idCliente + " finalizou as compras. Total: " + garagem.getTotalCompras());
    }

    
    // Percorre todas as lojas e verifica se alguma delas está "viva"
    private boolean algumaLojaAtiva() {
        for (Loja loja : lojasDisponiveis) {
            if (loja.isAlive()) {
                return true;
            }
        }
        return false;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public GaragemCliente getGaragem() {
        return garagem;
    }
}
