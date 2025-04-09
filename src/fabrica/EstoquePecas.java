package fabrica;

public class EstoquePecas {
	private int pecasDisponiveis;
	private final int capacidadeMaxima;

	// Construtor do nosso estoque de peças
	public EstoquePecas(int capacidadeMaxima) {
		this.capacidadeMaxima = capacidadeMaxima;
		this.pecasDisponiveis = capacidadeMaxima;
	}

	// Função sincronizada para apenas uma thread por vez consumir a peça
	public synchronized boolean consumirPeca() {
		if (pecasDisponiveis > 0) {
			pecasDisponiveis--;
			return true;
		} else {
			return false;
		}
	}

	// Função sincronizada para obter a quantidade de peças disponíveis do estoque
	public synchronized int getPecasDisponiveis() {
		return pecasDisponiveis;
	}

	// Get da capacidade máxima
	public int getCapacidadeMaxima() {
		return capacidadeMaxima;
	}
}
