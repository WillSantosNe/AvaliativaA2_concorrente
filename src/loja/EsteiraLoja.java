package loja;

import carro.Carro;
import carro.EsteiraCarro;

public class EsteiraLoja {
	private final EsteiraCarro esteira;

	public EsteiraLoja(int capacidade) {
		this.esteira = new EsteiraCarro(capacidade);
	}

	public int adicionarCarro(Carro carro, boolean lojaAtiva) {
		return esteira.adicionar(carro, lojaAtiva);
	}

	public Carro retirarCarro() {
		return esteira.retirar();
	}

	public boolean estaVazia() {
		return esteira.estaVazia();
	}

	public int getTotal() {
		return esteira.getTotal();
	}

	public void encerrar() {
		esteira.encerrar();
	}
}