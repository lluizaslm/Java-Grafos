package Q04_grafo;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Grafo4<String> grafo = new Grafo4<>(false);

        try {
            grafo.carregarDeArquivo("Atv06_grafo/Arquivos/Q4_Arquivo.txt");
        } catch (IOException e) {
            System.out.println("Erro ao carregar o arquivo: " + e.getMessage());
        }


        grafo.imprimir();
        grafo.imprimirParticoes();
    }
}

//        A;B;1 é bipartido
//        A;D;1
//        A;H;1
//        B;C;1
//        B;I;1
//        B;G;1
//        C;D;1
//        C;F;1
//        D;I;1
//        D;E;1
//        E;H;1
//        E;F;1
//        F;G;1
//        F;I;1
//        G;H;1
//        H;I;1

//A;B;1 não é bipartido
//A;C;1
//A;F;1
//B;G;1
//B;H;1
//C;I;1
//C;D;1
//D;I;1
//E;F;1
//E;G;1