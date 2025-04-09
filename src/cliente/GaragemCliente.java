package cliente;

import carro.Carro;
import java.util.ArrayList;
import java.util.List;

public class GaragemCliente {
    private final List<Carro> carros = new ArrayList<>();

    public synchronized void adicionar(Carro carro) {
        carros.add(carro);
    }

    public synchronized int getTotalCompras() {
        return carros.size();
    }

    public synchronized List<Carro> getTodosCarros() {
        return new ArrayList<>(carros);
    }
}
