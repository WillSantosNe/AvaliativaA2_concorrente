package loja;

import carro.Carro;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogLoja {

    private static final String LOG_DIR = "logs/";

    public static synchronized void registrarCompra(Loja loja, Carro carro) {
        criarDiretorioLogs();
        String nomeArquivo = LOG_DIR + "log_compra_loja_" + loja.getNome().replaceAll(" ", "_") + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo, true))) {
            writer.write(
                "ID=" + carro.getId() +
                ", Cor=" + carro.getCor() +
                ", Tipo=" + carro.getTipo() +
                ", Estacao=" + carro.getIdEstacao() +
                ", Funcionario=" + carro.getIdFuncionario() +
                ", PosicaoEsteiraFabrica=" + carro.getPosicaoEsteiraFabrica() +
                ", Loja=" + loja.getNome() +
                ", PosicaoEsteiraLoja=" + carro.getPosicaoEsteiraLoja()
            );
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void registrarVenda(Loja loja, Carro carro, int idCliente) {
        criarDiretorioLogs();
        String nomeArquivo = LOG_DIR + "log_venda_loja_" + loja.getNome().replaceAll(" ", "_") + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo, true))) {
            writer.write(
                "ID=" + carro.getId() +
                ", Cor=" + carro.getCor() +
                ", Tipo=" + carro.getTipo() +
                ", Estacao=" + carro.getIdEstacao() +
                ", Funcionario=" + carro.getIdFuncionario() +
                ", PosicaoEsteiraFabrica=" + carro.getPosicaoEsteiraFabrica() +
                ", Loja=" + loja.getNome() +
                ", PosicaoEsteiraLoja=" + carro.getPosicaoEsteiraLoja() +
                ", ClienteID=" + idCliente
            );
            writer.newLine();
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
