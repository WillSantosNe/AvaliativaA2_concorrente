package fabrica;

import carro.Carro;
import carro.EsteiraCarro;

public class EsteiraFabrica {
	private final EsteiraCarro esteira;
	private volatile boolean producaoEncerrada = false;

	public EsteiraFabrica(int capacidade) {
		this.esteira = new EsteiraCarro(capacidade);
	}

	public int adicionarCarro(Carro carro) {
		int posicao = esteira.adicionar(carro);
		carro.setPosicaoEsteiraFabrica(posicao); // coloca no objeto carro para fins de log em qual posicao da esteira da fabrica ele foi colocado
		return posicao;
	}

	public Carro retirarCarro(boolean encerramento) {
		return esteira.retirar(encerramento);
	}

	public boolean estaVazia() {
		return esteira.estaVazia();
	}

	public void encerrarProducao() {
		producaoEncerrada = true;
		esteira.encerrar();
	}

	public boolean isProducaoEncerrada() {
		return producaoEncerrada;
	}

	public int getTotal() {
		return esteira.getTotal();
	}
}