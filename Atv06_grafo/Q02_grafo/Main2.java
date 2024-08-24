package Q02_grafo;

public class Main2 {
    public static void main(String[] args) {
        Grafo2<String> grafo = new Grafo2<>();
        // Adicionando vértices e arestas conforme exemplo anterior
        grafo.adicionarVertice("A");
        grafo.adicionarVertice("B");
        grafo.adicionarVertice("C");
        grafo.adicionarVertice("D");

        grafo.adicionarAresta(1.0, "A", "B");
        grafo.adicionarAresta(1.0, "A", "C");
        grafo.adicionarAresta(1.0, "A", "D");
        grafo.adicionarAresta(1.0, "B", "C");
        grafo.adicionarAresta(1.0, "B", "D");
        grafo.adicionarAresta(1.0, "C", "D");

        grafo.execucaoDfs();

    }

    //resposta esperada:
//    Vértice A (Chegada: 1, Partida: 8)
//    Vértice B (Chegada: 2, Partida: 7)
//    Vértice C (Chegada: 3, Partida: 6)
//    Vértice D (Chegada: 4, Partida: 5)
}
