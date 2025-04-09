package loja;

import carro.Carro;
import fabrica.EsteiraFabrica;
import fabrica.LogFabrica;

public class Loja extends Thread {
	private final int id;
	private final String nome;
	private final EsteiraFabrica esteiraFabrica;
	private final EsteiraLoja esteiraLoja;
	private volatile boolean ativa = true;

	public Loja(int id, String nome, EsteiraFabrica esteiraFabrica, int capacidadeLoja) {
		this.id = id;
		this.nome = nome;
		this.esteiraFabrica = esteiraFabrica;
		this.esteiraLoja = new EsteiraLoja(capacidadeLoja);
	}

	@Override
	public void run() {
		while (true) {
			if (!ativa && esteiraFabrica.estaVazia() && esteiraLoja.estaVazia()) {
				break;
			}

			Carro carro = esteiraFabrica.retirarCarro(!ativa);
			if (carro == null) {
				if (!ativa && esteiraFabrica.estaVazia())
					break;
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					break;
				}
				continue;
			}

			carro.setLojaDestino(this);
			int posicaoLoja = esteiraLoja.adicionarCarro(carro, ativa);
			if (!ativa && posicaoLoja == -1)
				break;

			if (posicaoLoja != -1) {
				carro.setPosicaoEsteiraLoja(posicaoLoja);
				LogFabrica.registrarVenda(carro);
				LogLoja.registrarCompra(this, carro);
			}
		}
		System.out.println("❌ Loja " + nome + " finalizou compras da fábrica.");
	}

	public Carro venderCarroParaCliente() {
		return esteiraLoja.retirarCarro();
	}

	public void encerrar() {
		ativa = false;
		esteiraLoja.encerrar();
		System.out.println("Loja " + nome + " não está mais ativa");
	}

	public int getIdLoja() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public EsteiraLoja getEsteiraLoja() {
		return esteiraLoja;
	}
}