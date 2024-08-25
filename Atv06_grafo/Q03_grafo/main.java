package Q03_grafo;

import java.io.IOException;

public class main {
    public static void main(String[] args) {
        Grafo3<Integer> grafo = new Grafo3<>();

        try {
            grafo.carregarDeArquivo("Atv06_grafo/Arquivos/Q3_Arquivo.txt");
        } catch (IOException e) {
            System.out.println("Erro ao carregar o arquivo: " + e.getMessage());
        }

        grafo.imprimir();

        var results = grafo.temRaiz();

        if (results.size() > 0) {
            for (var result : results) {
                System.out.println("O VR é: " + result.getDado());
            }
        } else {
            System.out.println("O grafo não possui VR");
        }
    }
}