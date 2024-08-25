package Q02_grafo;

import java.io.IOException;

public class Main2 {
    public static void main(String[] args) {
        Grafo2<Integer> grafo = new Grafo2<>();
        // Adicionando vértices e arestas conforme exemplo anterior
        try {
            grafo.carregarDeArquivo("Atv06_grafo/Arquivos/Q2_Arquivo.txt");
        } catch (IOException e) {
            System.out.println("Erro ao carregar o arquivo: " + e.getMessage());
        }

        grafo.execucaoDfs();
    }
}

//resposta:
//0 (Chegada: 0, Partida: 11)
//1 (Chegada: 1, Partida: 2)
//2 (Chegada: 3, Partida: 10)
//3 (Chegada: 4, Partida: 7)
//4 (Chegada: 8, Partida: 9)
//5 (Chegada: 5, Partida: 6)
//6 (Chegada: 12, Partida: 15)
//7 (Chegada: 13, Partida: 14)
