package Q03_grafo;

public class main {
    public static void main(String[] args) {
        Grafo<Integer> grafo = new Grafo<>();

        grafo.adicionarVertice(5);
        grafo.adicionarVertice(4);
        grafo.adicionarVertice(0);
        grafo.adicionarVertice(3);
        grafo.adicionarVertice(1);
        grafo.adicionarVertice(2);

        grafo.adicionarAresta(1.0, 4, 5);
        grafo.adicionarAresta(1.0, 4, 3);
        grafo.adicionarAresta(1.0, 5, 0);
        grafo.adicionarAresta(1.0, 0, 1);
        grafo.adicionarAresta(1.0, 3, 0);
        grafo.adicionarAresta(1.0, 1, 2);
        grafo.adicionarAresta(1.0, 2, 3);

        var results = grafo.temRaiz();

        if (results.size() > 0)
            for(var result : results){
                System.out.println("O VR é:" + result.getDado());
            }
        else System.out.println("O grafo não possui VR");
    }
}
