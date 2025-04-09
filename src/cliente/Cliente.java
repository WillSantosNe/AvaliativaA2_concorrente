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
    public void run() {
        while (algumaLojaAtiva()) {
            Loja loja = lojasDisponiveis[random.nextInt(lojasDisponiveis.length)];
            Carro carro = loja.venderCarroParaCliente(); // pode esperar

            if (carro != null) {
                garagem.adicionar(carro);
                System.out.println("Cliente " + idCliente + " comprou");
                LogLoja.registrarVenda(carro.getLojaDestino(), carro, idCliente);
            }

            try {
                Thread.sleep(500 + random.nextInt(500));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Cliente " + idCliente + " finalizou as compras. Total: " + garagem.getTotalCompras());
    }

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
