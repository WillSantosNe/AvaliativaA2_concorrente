package carro;

public class EsteiraCarro {
	private final Carro[] buffer;
	private int inicio = 0, fim = 0, total = 0;
	private volatile boolean encerrado = false;

	public EsteiraCarro(int capacidade) {
		buffer = new Carro[capacidade];
	}

	public synchronized int adicionar(Carro carro) {
		while (total == buffer.length) {
			try {
				wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		buffer[fim] = carro;
		int posicao = fim;
		fim = (fim + 1) % buffer.length;
		total++;
		notifyAll();
		return posicao;
	}

	public synchronized int adicionar(Carro carro, boolean lojaAtiva) {
		while (total == buffer.length) {
			if (!lojaAtiva)
				return -1;
			try {
				wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				return -1;
			}
		}
		buffer[fim] = carro;
		int posicao = fim;
		fim = (fim + 1) % buffer.length;
		total++;
		notifyAll();
		return posicao;
	}

	public synchronized Carro retirar(boolean encerramento) {
		while (total == 0) {
			if (encerramento || encerrado)
				return null;
			try {
				wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				return null;
			}
		}
		Carro carro = buffer[inicio];
		buffer[inicio] = null;
		inicio = (inicio + 1) % buffer.length;
		total--;
		notifyAll();
		return carro;
	}

	public synchronized Carro retirar() {
		while (total == 0) {
			if (encerrado)
				return null;
			try {
				wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				return null;
			}
		}
		Carro carro = buffer[inicio];
		buffer[inicio] = null;
		inicio = (inicio + 1) % buffer.length;
		total--;
		notifyAll();
		return carro;
	}

	public synchronized void encerrar() {
		encerrado = true;
		notifyAll();
	}

	public synchronized boolean estaVazia() {
		return total == 0;
	}

	public synchronized int getTotal() {
		return total;
	}
}