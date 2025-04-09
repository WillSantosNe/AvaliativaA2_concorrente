package carro;

public enum CorCarro {
	RED, GREEN, BLUE;
	
	// Função que pega o próximo índice desse enum - o enunciado diz para alternar
	// RED(0), GREEN(1) e BLUE(2)
	public static CorCarro proximaCor(int index) {
        return values()[index % values().length];
    }
}
