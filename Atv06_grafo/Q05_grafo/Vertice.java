package Q05_grafo;

import java.util.ArrayList;

public class Vertice <T>{
    private T dado;
    private ArrayList<Aresta<T>> arestas;

    public Vertice (T valor){
        this.dado = valor;
        this.arestas = new ArrayList<Aresta<T>>();
    }
    public ArrayList<Aresta<T>> getArestas() {
        return arestas;
    }

    public  void adicionarAresta(Aresta<T> aresta){
        arestas.add(aresta);
    }
    public T getDado() {
        return dado;
    }
    public void setDado(T dado) {
        this.dado = dado;
    }


}
