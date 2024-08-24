package Q03_grafo;

import java.util.ArrayList;

public class Vertice3<T>{
    private T dado;
    private ArrayList<Aresta3<T>> arestasEntrada;
    private ArrayList<Aresta3<T>> arestasSaida;



    public Vertice3(T valor){
        this.dado = valor;
        this.arestasEntrada = new ArrayList<Aresta3<T>>();
        this.arestasSaida = new ArrayList<Aresta3<T>>();
    }

    public T getDado() {
        return dado;
    }

    public void setDado(T dado) {
        this.dado = dado;
    }

    public void adicionarArestaEntrada(Aresta3<T> aresta){
        this.arestasEntrada.add(aresta);
    }

    public void adicionarArestaSaida(Aresta3<T> aresta){
        this.arestasSaida.add(aresta);
    }

    public ArrayList<Aresta3<T>> getArestasEntrada() {
        return arestasEntrada;
    }

    public ArrayList<Aresta3<T>> getArestasSaida() {
        return arestasSaida;
    }
}
