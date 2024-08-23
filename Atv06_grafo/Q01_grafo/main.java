package Q01_grafo;

import java.util.ArrayList;

public class main {
    public static void main(String[] args) {
        Grafo<String> grafo = new Grafo<>();//usando string só pra testar
        // adc vertices
        grafo.adicionarVertice("A");
        grafo.adicionarVertice("B");
        grafo.adicionarVertice("C");
        grafo.adicionarVertice("D");

        // adc as arestas
        grafo.adicionarAresta(1.0, "A", "B");
        grafo.adicionarAresta(2.5, "A", "C");
        grafo.adicionarAresta(1.5, "B", "D");
        grafo.adicionarAresta(3.0, "C", "D");

        System.out.println("Grafo:");
        grafo.imprimir();

        grafo.removerAresta("C", "D");

        System.out.println("\nGrafo após remoções:");
        grafo.imprimir();

    }
}
