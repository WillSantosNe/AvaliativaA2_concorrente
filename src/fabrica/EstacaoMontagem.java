package fabrica;

import java.util.concurrent.Semaphore;

public class EstacaoMontagem {
	private final int idEstacao;
	private final Funcionario[] funcionarios = new Funcionario[5];
	private final Semaphore[] ferramentas = new Semaphore[5];
	private final EstoquePecas estoque;
	private final EsteiraFabrica esteira;
	private volatile boolean producaoEncerrada = false;

	public EstacaoMontagem(int idEstacao, EstoquePecas estoque, EsteiraFabrica esteira) {
		this.idEstacao = idEstacao;
		this.estoque = estoque;
		this.esteira = esteira;

		for (int i = 0; i < 5; i++) {
			ferramentas[i] = new Semaphore(1);
		}

		for (int i = 0; i < 5; i++) {
			funcionarios[i] = new Funcionario(i, this);
		}
	}

	public void iniciarProducao() {
		for (Funcionario f : funcionarios) {
			f.start();
		}
	}
	
	public void aguardarFinalizacao() {
	    for (Funcionario f : funcionarios) {
	        try {
	            f.join(); // espera a thread finalizar
	        } catch (InterruptedException e) {
	            Thread.currentThread().interrupt();
	        }
	    }
	}
	
	public void encerrarProducao() {
	    this.producaoEncerrada = true;
	}

	public boolean isProducaoEncerrada() {
	    return producaoEncerrada;
	}


	public int getId() {
		return idEstacao;
	}

	public EstoquePecas getEstoque() {
		return estoque;
	}

	public EsteiraFabrica getEsteiraFabrica() {
		return esteira;
	}

	public Semaphore getFerramenta(int posicao) {
		return ferramentas[posicao];
	}
}
