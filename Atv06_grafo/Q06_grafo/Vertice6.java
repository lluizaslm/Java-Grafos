package Q06_grafo;

import java.util.ArrayList;

public class Vertice6<T>{
    private T dado;
    private ArrayList<Aresta6<T>> arestas;
    public Vertice6(T valor){
        this.dado = valor;
        this.arestas = new ArrayList<Aresta6<T>>();
    }
    public ArrayList<Aresta6<T>> getArestas() {
        return arestas;
    }

    public  void adicionarAresta(Aresta6<T> aresta){
        arestas.add(aresta);
    }
    public T getDado() {
        return dado;
    }
    public void setDado(T dado) {
        this.dado = dado;
    }
}
