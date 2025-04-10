package carro;

public class EsteiraCarro {
	private final Carro[] buffer;
	private int inicio = 0, fim = 0, total = 0;
	private volatile boolean encerrado = false;

	public EsteiraCarro(int capacidade) {
		buffer = new Carro[capacidade];
	}

	
	// Função sobrecarregada usada pela fábrica
	public synchronized int adicionar(Carro carro) {
		
		// Verifica se o total de carros na esteira estourou a capacidade máxima
		while (total == buffer.length) {
			try {
				// Se estiver cheio, ele vai esperar e nao vai adicionar mais nenhum carro até uma loja pegar
				wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		
		// Colocando o carro no final da esteira (ultima posicao do momento)
		buffer[fim] = carro;
		
		// Posição adicionada é a última posição da esteira
		int posicao = fim;
		
		// Vamos atribuir o próximo índice do fim com um cálculo, pois ela é circular.
		fim = (fim + 1) % buffer.length;
		
		// Aumenta o total da esteira
		total++;
		
		// Notifica as threads que tem um novo carro
		notifyAll();
		
		// Retorna a posicao da esteira em que o carro foi adicionado (para fins de log)
		return posicao;
	}
	
	
	// Função sobrecarregada usada pela loja
	public synchronized int adicionar(Carro carro, boolean lojaAtiva) {
		while (total == buffer.length) {
			
			// A única diferença é que retorna -1 caso a loja esteja fechada
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

	
	// Função sobrecarregada usada pela loja para retirar da fábrica
	public synchronized Carro retirar(boolean encerramento) {
		// Nao vai retirar enquanto nao houver carros na esteira (da fabrica)
		while (total == 0) {
			
			// Caso nao tenha na esteira e a loja encerrou, retorna null indicando que acabou a operacao
			if (encerramento || encerrado) {				
				return null;
			}
			
			// Caso nao tenha na esteira e a loja ainda nao encerrou, vai esperar até  ter um carro novo na esteira
			try {
				wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				return null;
			}
		}
		
		// Vai pegar o carro que na na posicao inicial da esteira (próxima)
		Carro carro = buffer[inicio];
		
		// Vai liberar o espaço pois o carro foi retirado
		buffer[inicio] = null;
		
		// Indica a próxima posicao da esteira a ser pego na proxima operacao
		inicio = (inicio + 1) % buffer.length;
		
		// Diminui o total de carros da esteira
		total--;
		
		// Notifica as threads para sair do wait caso ele tenha ficado em espera
		notifyAll();
		
		// Retorna o carro retirado
		return carro;
	}

	
	// Função sobrecarregada usada pelo cliente para retirar da Loja
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