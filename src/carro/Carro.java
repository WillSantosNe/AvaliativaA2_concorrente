package carro;

import loja.Loja;

public class Carro {
	// Contador de id que vai incrementando - inicia como 1
	private static int idCounter = 1;

	// Valores que vao alternando a cada carro fabricado
	private static int corIndex = 0;
	private static int tipoIndex = 0;

	// Atributos do carro
	private int id;
	private CorCarro cor;
	private TipoVeiculo tipo;
	private int idEstacao;
	private int idFuncionario;
	private int posicaoEsteiraFabrica;

	// Atributos da Loja do carro
	private Loja lojaDestino;
	private int posicaoEsteiraLoja;

	// ----------------------------------------------------------------------

	// Construtor de carro
	public Carro(int idEstacao, int idFuncionario) {
	    this.id = gerarId();
	    this.cor = gerarCor();
	    this.tipo = gerarTipo();
	    this.idEstacao = idEstacao;
	    this.idFuncionario = idFuncionario;
	}

	// ----------------------------------------------------------------------

	// Funções que mudam atributos de acordo com o carro
	private static synchronized int gerarId() {
		return idCounter++;
	}

	private static synchronized CorCarro gerarCor() {
		return CorCarro.proximaCor(corIndex++);
	}

	private static synchronized TipoVeiculo gerarTipo() {
		return TipoVeiculo.proximoTipo(tipoIndex++);
	}

	// ----------------------------------------------------------------------

	// Getters
	public int getId() {
		return id;
	}

	public CorCarro getCor() {
		return cor;
	}

	public TipoVeiculo getTipo() {
		return tipo;
	}

	public int getIdEstacao() {
		return idEstacao;
	}

	public int getIdFuncionario() {
		return idFuncionario;
	}

	public int getPosicaoEsteiraFabrica() {
		return posicaoEsteiraFabrica;
	}

	public Loja getLojaDestino() {
		return lojaDestino;
	}

	public int getPosicaoEsteiraLoja() {
		return posicaoEsteiraLoja;
	}

	// Setters
	public void setLojaDestino(Loja lojaDestino) {
		this.lojaDestino = lojaDestino;
	}

	public void setPosicaoEsteiraLoja(int posicao) {
		this.posicaoEsteiraLoja = posicao;
	}
	
	public void setPosicaoEsteiraFabrica(int posicaoEsteiraFabrica) {
	    this.posicaoEsteiraFabrica = posicaoEsteiraFabrica;
	}

	
	
	// Função que vai gerar uma string para o carro, que vai ser usada na log
	@Override
    public String toString() {
        return "Carro{" +
                "id=" + id +
                ", cor=" + cor +
                ", tipo=" + tipo +
                ", estacao=" + idEstacao +
                ", funcionario=" + idFuncionario +
                ", posicaoFabrica=" + posicaoEsteiraFabrica +
                (lojaDestino != null ? ", loja='" + lojaDestino.getNome() + '\'' : "") +
                (lojaDestino != null ? ", posicaoLoja=" + posicaoEsteiraLoja : "") +
                '}';
    }
}
