package fabrica;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import carro.Carro;

public class LogFabrica {

	private static final String LOG_PRODUCAO = "log_producao_fabrica.txt";
    private static final String LOG_VENDA = "log_venda_fabrica.txt";
    
    public static synchronized void registrarProducao(Carro carro) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_PRODUCAO, true))) {
            writer.write(
                "ID=" + carro.getId() +
                ", Cor=" + carro.getCor() +
                ", Tipo=" + carro.getTipo() +
                ", Estacao=" + carro.getIdEstacao() +
                ", Funcionario=" + carro.getIdFuncionario() +
                ", PosicaoEsteiraFabrica=" + carro.getPosicaoEsteiraFabrica()
            );
            writer.newLine(); // quebra de linha
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static synchronized void registrarVenda(Carro carro) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_VENDA, true))) {
            writer.write(
                "ID=" + carro.getId() +
                ", Cor=" + carro.getCor() +
                ", Tipo=" + carro.getTipo() +
                ", Estacao=" + carro.getIdEstacao() +
                ", Funcionario=" + carro.getIdFuncionario() +
                ", PosicaoEsteiraFabrica=" + carro.getPosicaoEsteiraFabrica() +
                ", Loja=" + carro.getLojaDestino().getNome() +
                ", PosicaoEsteiraLoja=" + carro.getPosicaoEsteiraLoja()
            );
            writer.newLine(); // quebra de linha
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
