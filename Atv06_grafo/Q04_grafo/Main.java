package Q04_grafo;

public class Main {
    public static void main(String[] args) {
        Grafo4<String> grafo = new Grafo4<>(false);

        grafo.adicionarVertice("A");
        grafo.adicionarVertice("B");
        grafo.adicionarVertice("C");
        grafo.adicionarVertice("D");

        grafo.adicionarAresta(1.0, "A", "B");
        grafo.adicionarAresta(1.0, "B", "C");
        grafo.adicionarAresta(1.0, "C", "D");
        grafo.adicionarAresta(1.0, "D", "A");
//        grafo.adicionarAresta(1.0, "D", "A");
//        grafo.adicionarAresta(1.0, "A", "C"); com essas arestas o grafo não é bipartido

        grafo.imprimir();

        grafo.imprimirParticoes();
    }
}
//qualquer coisa
