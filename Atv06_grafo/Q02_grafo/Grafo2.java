package Q02_grafo;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Grafo2<T> {
    private ArrayList<Vertice2<T>> vertices;
    private ArrayList<Aresta2<T>> arestas;
    private int tempo;//pra ver tempos de chegada e partida

    public Grafo2(){
        this.vertices = new ArrayList<>();
        this.arestas = new ArrayList<>();
    }
    public Grafo2(boolean direcionado) {
        this.vertices = new ArrayList<>();
        this.arestas = new ArrayList<>();
        this.tempo = 0;
    }

    public void carregarDeArquivo(String caminhoArquivo) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo));
        String linha;

        while ((linha = reader.readLine()) != null) {
            String[] partes = linha.split(";");
            if (partes.length == 2) {
                T verticeInicio = (T) partes[0];
                T verticeFim = (T) partes[1];

                adicionarVerticeSeNaoExistir(verticeInicio);
                adicionarVerticeSeNaoExistir(verticeFim);

                adicionarAresta(1.0, verticeInicio, verticeFim);
            }
        }

        reader.close();
    }

    private void adicionarVerticeSeNaoExistir(T dado) {
        if (buscarVertice(dado) == null) {
            adicionarVertice(dado);
        }
    }

    // adicionar vértice
    public void adicionarVertice(T dado) {
        Vertice2<T> novoVertice = new Vertice2<T>(dado);
        this.vertices.add(novoVertice);
    }

    // adicionar aresta
    public void adicionarAresta(Double peso, T inicio, T fim) {
        Vertice2<T> verticeInicio = buscarVertice(inicio);
        Vertice2<T> verticeFim = buscarVertice(fim);

        if (verticeInicio != null && verticeFim != null) {
            Aresta2<T> aresta = new Aresta2<>(peso, verticeInicio, verticeFim);
            arestas.add(aresta);
            verticeInicio.adicionarArestaSaida(aresta);
            verticeFim.adicionarArestaEntrada(aresta);
        }

        //adc aresta ao contrario tb, por conta do grafo não direcional que é bidirecional A->B||B->A

    }


    public void removerVertice(T dado) {
        Vertice2<T> vertice =buscarVertice(dado);
        if(vertice!=null){

            ArrayList<Aresta2<T>> arestasAssociadas =new ArrayList<>(vertice.getArestasEntrada());//aqui cria uma nova lista pra adc as arestas que vão ta associadas ao vertice
            arestasAssociadas.addAll(vertice.getArestasSaida());
            for(Aresta2<T> aresta :arestasAssociadas){
                Vertice2<T> verticeInicio =aresta.getInicio();
                Vertice2<T> verticeFim =aresta.getFim();
                verticeInicio.getArestasSaida().remove(aresta);//pra pegar o vertice que a aresta ta saindo
                verticeFim.getArestasEntrada().remove(aresta);//e aqui o vertice que aresta ta chegando

                arestas.remove(arestas);//pra remover as arestas
            }
            vertices.remove(vertice);
        }
    }

    //so ta grafo direcionado
    public void removerAresta(T inicio, T fim) {
        Vertice2<T> verticeInicio=buscarVertice(inicio);
        Vertice2<T> verticeFim = buscarVertice(fim);

        if(verticeInicio != null && verticeFim != null){
            Aresta2<T> aresta = buscarAresta(inicio, fim);
            if(aresta != null){
                arestas.remove(aresta);
                verticeInicio.getArestasSaida().remove(aresta);//remover a aresta que ta saindo do vertice de inicio
                verticeFim.getArestasEntrada().remove(aresta);//remover a aresta que ta entrando do vertice "final"
            }

        }
    }

    //so ta grafo direcionado
    public Aresta2<T> buscarAresta(T inicio, T fim){
        Vertice2<T> verticeInicio=buscarVertice(inicio);
        Vertice2<T> verticeFim = buscarVertice(fim);

        if(verticeInicio != null && verticeFim != null ){
            for(Aresta2<T> aresta :arestas){
                if(aresta.getInicio().equals(verticeInicio) && aresta.getFim().equals(verticeFim)){
                    return aresta;
                }
            }
        }
        return null;
    }


    // buscar o vertice
    private Vertice2<T> buscarVertice(T dado) {
        for (Vertice2<T> vertice : vertices) {
            if (vertice.getDado().equals(dado)) {
                return vertice;
            }
        }
        return null;
    }


    public ArrayList<Vertice2<T>> obterAdjacentes(T dado){
        Vertice2<T> vertice =buscarVertice(dado);
        ArrayList<Vertice2<T>> adjacentes =new ArrayList<>();
        if(vertice!= null){
            for(Aresta2<T> aresta : vertice.getArestasSaida()){
                adjacentes.add(aresta.getFim());
            }
        }
        return adjacentes;
    }


    private void DFS(Vertice2<T> vertice) {
        vertice.setVisitado(true);
        vertice.setTempoChegada(++tempo);

        for (Aresta2<T> aresta : vertice.getArestasSaida()) {
            Vertice2<T> adjacente = aresta.getFim();//itera sobre todas as arestas de saída do vértice atual e vai chamar DFS para cada vértice adjacente que ainda não foi visitado.
            if (!adjacente.isVisitado()) {
                DFS(adjacente);
            }
        }

        vertice.setTempoPartida(++tempo);
    }

    public void execucaoDfs(){
        tempo = -1;//foi o jeito
        for(Vertice2<T> vertice: vertices){
            vertice.setVisitado(false);
            vertice.setTempoChegada(-1);//o -1 é pq o tempo nao inicializado ainda
            vertice.setTempoPartida(-1);
        }

        for(Vertice2<T> vertice:vertices){
            if(!vertice.isVisitado()){
                DFS(vertice);
            }
        }
        System.out.println("Tempos de Chegada e Partida dos Vértices:");
        for (Vertice2<T> vertice : vertices) {
            System.out.println("Vértice " + vertice.getDado() + " (Chegada: " + vertice.getTempoChegada() + ", Partida: " + vertice.getTempoPartida() + ")");
        }
    }



    public void imprimir() {
        for (Vertice2<T> vertice : vertices) {
            System.out.print("Vértice " + vertice.getDado() + " -> ");
            ArrayList<Vertice2<T>> adjacentes = obterAdjacentes(vertice.getDado());
            if (adjacentes.isEmpty()) {
                System.out.print("Sem adjacentes");
            } else {
                System.out.print("Adjacentes -> ");
                for (Vertice2<T> adjacente : adjacentes) {
                    System.out.print(adjacente.getDado() + " ");
                }
            }
            System.out.println();
        }
        System.out.println("\nArestas:");
        for (Aresta2<T> aresta : arestas) {
            System.out.println("Aresta de " + aresta.getInicio().getDado() + " para " + aresta.getFim().getDado() + " com peso " + aresta.getPeso());
        }

    }

}
