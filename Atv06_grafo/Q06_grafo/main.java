package Q06_grafo;

import java.io.IOException;

public class main {
    public static void main(String[] args) {
        //ArvorePrimTeste01();
        //ArvoreKruskalTeste01();
        //ArvoreBoruvkaTeste01();
        Grafo6<String> grafo = new Grafo6<>();
        try{
            grafo.carregarDeArquivo("Atv06_grafo/Arquivos/Q6_Arquivo.txt");

            grafo.TSP();
        } catch (IOException e) {
            System.out.println("Erro ao carregar o arquivo: " + e.getMessage());
        }


    }

    public static void ArvorePrimTeste01(){
        var grafo = new Grafo6<String>();

        grafo.adicionarVertice("A");
        grafo.adicionarVertice("B");
        grafo.adicionarVertice("C");
        grafo.adicionarVertice("D");
        grafo.adicionarVertice("E");
        grafo.adicionarVertice("F");
        grafo.adicionarVertice("G");

        grafo.adicionarAresta(7.0, "A", "B");
        grafo.adicionarAresta(8.0, "B", "C");
        grafo.adicionarAresta(5.0, "A", "G");
        grafo.adicionarAresta(9.0, "G", "B");
        grafo.adicionarAresta(7.0, "B", "D");
        grafo.adicionarAresta(5.0, "D", "C");
        grafo.adicionarAresta(16.0, "G", "D");
        grafo.adicionarAresta(6.0, "G", "F");
        grafo.adicionarAresta(8.0, "F", "D");
        grafo.adicionarAresta(9.0, "D", "E");
        grafo.adicionarAresta(11.0, "F", "E");

        grafo.ArvorePrim();
    }
}
