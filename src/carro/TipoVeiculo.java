package carro;

public enum TipoVeiculo {
	SUV, SEDAN;

	// Função que pega o próximo índice desse enum - o enunciado diz para alternar
	// SUV(0), SEDAN(1)
    public static TipoVeiculo proximoTipo(int index) {
        return values()[index % values().length];
    }
}
