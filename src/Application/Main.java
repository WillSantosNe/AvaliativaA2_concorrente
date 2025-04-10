package Application;

import cliente.Cliente;
import fabrica.EstacaoMontagem;
import fabrica.EsteiraFabrica;
import fabrica.EstoquePecas;
import loja.Loja;

public class Main {
	public static void main(String[] args) {
		
		// 1. Inicia o estoque da fábrica com 500 peças
		EstoquePecas estoque = new EstoquePecas(100);

		// 2. Cria a esteira da fábrica com capacidade para 40 carros
		EsteiraFabrica esteiraFabrica = new EsteiraFabrica(40);

		// 3. Cria as 4 estações de montagem, cada uma com 5 funcionários
		EstacaoMontagem[] estacoes = new EstacaoMontagem[4];
		for (int i = 0; i < estacoes.length; i++) {
			estacoes[i ] = new EstacaoMontagem(i + 1, estoque, esteiraFabrica);
			estacoes[i].iniciarProducao(); // inicia os 5 funcionários
		}

		// 4. Cria as 3 lojas com suas próprias esteiras de 20 posições
		Loja[] lojas = new Loja[3];
		lojas[0] = new Loja(1, "Loja A", esteiraFabrica, 20);
		lojas[1] = new Loja(2, "Loja B", esteiraFabrica, 20);
		lojas[2] = new Loja(3, "Loja C", esteiraFabrica, 20);

		for (Loja loja : lojas) {
			loja.start(); // cada loja é uma thread que compra da fábrica
		}

		// 5. Cria e inicia 20 clientes que compram de lojas aleatórias
		Cliente[] clientes = new Cliente[20];
		for (int i = 0; i < clientes.length; i++) {
			clientes[i] = new Cliente(i + 1, lojas);
			clientes[i].start(); // cada cliente é uma thread
		}
		
		

		// 6. Aguarda todas as estações finalizarem produção
		for (EstacaoMontagem estacao : estacoes) {
			estacao.aguardarFinalizacao();
		}
		System.out.println("🚨 Estoque de peças da fábrica esgotado. Produção encerrada.");

		
		// Parar de produzir carros
		esteiraFabrica.encerrarProducao();
		
		
		// 7. Finaliza lojas após fim da produção
		for (Loja loja : lojas) {
			loja.encerrar(); // flag para encerrar thread da loja
		}	
		
		// 8. Aguarda finalização das lojas
		for (Loja loja : lojas) {
			try {
				loja.join();
				//System.out.println("✅ Loja " + loja.getNome() + " finalizada.");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		

		// 9. Aguarda finalização dos clientes
		for (Cliente cliente : clientes) {
			try {
				cliente.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		//System.out.println("Clientes finalizados");

		// 10. Mensagem final
		System.out.println("✅ Todas as lojas e clientes finalizaram. Programa encerrado.");
	}
}
