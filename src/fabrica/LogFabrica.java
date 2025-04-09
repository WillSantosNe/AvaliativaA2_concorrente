package fabrica;

import carro.Carro;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogFabrica {

    private static final String LOG_DIR = "logs/";
    private static final String LOG_PRODUCAO = LOG_DIR + "log_producao_fabrica.txt";
    private static final String LOG_VENDA = LOG_DIR + "log_venda_fabrica.txt";

    public static synchronized void registrarProducao(Carro carro) {
        criarDiretorioLogs();
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
        criarDiretorioLogs();
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

    private static void criarDiretorioLogs() {
        File dir = new File(LOG_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }
}
