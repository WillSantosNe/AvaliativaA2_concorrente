package loja;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import carro.Carro;

public class LogLoja {

	public static synchronized void registrarCompra(Loja loja, Carro carro) {
        String nomeArquivo = "log_compra_loja_" + loja.getNome().replaceAll(" ", "_") + ".txt";

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
        String nomeArquivo = "log_venda_loja_" + loja.getNome().replaceAll(" ", "_") + ".txt";

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
}
