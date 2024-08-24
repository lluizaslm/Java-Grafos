package Q05_grafo;

import java.util.ArrayList;

public class Vertice5<T>{
    private T dado;
    private ArrayList<Aresta5<T>> arestas;
    public Vertice5(T valor){
        this.dado = valor;
        this.arestas = new ArrayList<Aresta5<T>>();
    }
    public ArrayList<Aresta5<T>> getArestas() {
        return arestas;
    }

    public  void adicionarAresta(Aresta5<T> aresta){
        arestas.add(aresta);
    }
    public T getDado() {
        return dado;
    }
    public void setDado(T dado) {
        this.dado = dado;
    }
}
