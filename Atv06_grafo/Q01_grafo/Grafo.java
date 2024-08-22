package Q01_grafo;

import java.util.ArrayList;

public class Grafo<T> {
    private ArrayList<Vertice<T>> vertices;
    private ArrayList<Aresta<T>> arestas;

    public Grafo() {
        this.vertices = new ArrayList<>();
        this.arestas = new ArrayList<>();
    }

    // adicionar vértice
    public void adicionarVertice(T dado) {
        Vertice<T> novoVertice = new Vertice<T>(dado);
        this.vertices.add(novoVertice);
    }

    // adicionar aresta
    public void adicionarAresta(Double peso, T inicio, T fim) {
        Vertice<T> verticeInicio = buscarVertice(inicio);
        Vertice<T> verticeFim = buscarVertice(fim);

        if (verticeInicio != null && verticeFim != null) {
            Aresta<T> aresta = new Aresta<>(peso, verticeInicio, verticeFim);
            arestas.add(aresta);
            verticeInicio.adicionarArestaSaida(aresta);
            verticeFim.adicionarArestaEntrada(aresta);
        }
    }

    public void removerVertice(T dado) {

    }


    public void removerAresta(T inicio, T fim) {

    }

    // buscar o vertice
    private Vertice<T> buscarVertice(T dado) {
        for (Vertice<T> vertice : vertices) {
            if (vertice.getDado().equals(dado)) {
                return vertice;
            }
        }
        return null;
    }

    // adc a de buscar aresta

    public void imprimir() {
        for (Vertice<T> vertice : vertices) {
            System.out.print("Vértice " + vertice.getDado() + " -> ");
        }
        System.out.println("\nArestas:");
        for (Aresta<T> aresta : arestas) {
            System.out.println("Aresta de " + aresta.getInicio().getDado() + " para " + aresta.getFim().getDado() + " com peso " + aresta.getPeso());
        }

    }

    // obter adjacentes de um vértice e imprimir (apresenta cada
    //vértice seguido dos seus adjacentes)
    //falta isso tbm
}