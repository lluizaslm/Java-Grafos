package Q01_grafo;

import java.io.IOException;

public class main {
    public static void main(String[] args) {
        Grafo<String> grafo = new Grafo<>();

        try {
            grafo.carregarDeArquivo("Atv06_grafo/Arquivos/Q1_Arquivo.txt");
        } catch (IOException e) {
            System.out.println("Erro ao carregar o arquivo: " + e.getMessage());
        }

        System.out.println("Grafo:");
        grafo.imprimir();

        grafo.removerAresta("C", "D");

        System.out.println("\nGrafo após remoções:");
        grafo.imprimir();
    }
}