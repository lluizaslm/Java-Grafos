package Q02_grafo;

import java.util.ArrayList;

public class Grafo<T> {
    private ArrayList<Vertice<T>> vertices;
    private ArrayList<Aresta<T>> arestas;
    private int tempo;//pra ver tempos de chegada e partida

    public Grafo(){
        this.vertices = new ArrayList<>();
        this.arestas = new ArrayList<>();
    }
    public Grafo(boolean direcionado) {
        this.vertices = new ArrayList<>();
        this.arestas = new ArrayList<>();
        this.tempo = 0;
    }

    // adicionar vértice
    public void adicionarVertice(T dado) {
        Vertice<T> novoVertice = new Vertice<T>(dado);
        this.vertices.add(novoVertice);
    }

    // adicionar aresta
    public void adicionarAresta(Double peso, T inicio, T fim) {
        Vertice<T> verticeInicio = buscarVertice(inicio);
        Vertice<T> verticeFim = buscarVertice(fim);

        if (verticeInicio != null && verticeFim != null) {
            Aresta<T> aresta = new Aresta<>(peso, verticeInicio, verticeFim);
            arestas.add(aresta);
            verticeInicio.adicionarArestaSaida(aresta);
            verticeFim.adicionarArestaEntrada(aresta);
        }

        //adc aresta ao contrario tb, por conta do grafo não direcional que é bidirecional A->B||B->A

    }


    public void removerVertice(T dado) {
        Vertice<T> vertice =buscarVertice(dado);
        if(vertice!=null){

            ArrayList<Aresta<T>> arestasAssociadas =new ArrayList<>(vertice.getArestasEntrada());//aqui cria uma nova lista pra adc as arestas que vão ta associadas ao vertice
            arestasAssociadas.addAll(vertice.getArestasSaida());
            for(Aresta<T> aresta :arestasAssociadas){
                Vertice<T> verticeInicio =aresta.getInicio();
                Vertice<T> verticeFim =aresta.getFim();
                verticeInicio.getArestasSaida().remove(aresta);//pra pegar o vertice que a aresta ta saindo
                verticeFim.getArestasEntrada().remove(aresta);//e aqui o vertice que aresta ta chegando

                arestas.remove(arestas);//pra remover as arestas
            }
            vertices.remove(vertice);
        }
    }

    //so ta grafo direcionado
    public void removerAresta(T inicio, T fim) {
        Vertice<T> verticeInicio=buscarVertice(inicio);
        Vertice<T> verticeFim = buscarVertice(fim);

        if(verticeInicio != null && verticeFim != null){
            Aresta<T> aresta = buscarAresta(inicio, fim);
            if(aresta != null){
                arestas.remove(aresta);
                verticeInicio.getArestasSaida().remove(aresta);//remover a aresta que ta saindo do vertice de inicio
                verticeFim.getArestasEntrada().remove(aresta);//remover a aresta que ta entrando do vertice "final"
            }

        }
    }

    //so ta grafo direcionado
    public Aresta<T> buscarAresta(T inicio, T fim){
        Vertice<T> verticeInicio=buscarVertice(inicio);
        Vertice<T> verticeFim = buscarVertice(fim);

        if(verticeInicio != null && verticeFim != null ){
            for(Aresta<T> aresta :arestas){
                if(aresta.getInicio().equals(verticeInicio) && aresta.getFim().equals(verticeFim)){
                    return aresta;
                }
            }
        }
        return null;
    }


    // buscar o vertice
    private Vertice<T> buscarVertice(T dado) {
        for (Vertice<T> vertice : vertices) {
            if (vertice.getDado().equals(dado)) {
                return vertice;
            }
        }
        return null;
    }


    public ArrayList<Vertice<T>> obterAdjacentes(T dado){
        Vertice<T> vertice =buscarVertice(dado);
        ArrayList<Vertice<T>> adjacentes =new ArrayList<>();
        if(vertice!= null){
            for(Aresta<T> aresta : vertice.getArestasSaida()){
                adjacentes.add(aresta.getFim());
            }
        }
        return adjacentes;
    }


    private void DFS(Vertice<T> vertice) {
        vertice.setVisitado(true);
        vertice.setTempoChegada(++tempo);

        for (Aresta<T> aresta : vertice.getArestasSaida()) {
            Vertice<T> adjacente = aresta.getFim();//itera sobre todas as arestas de saída do vértice atual e vai chamar DFS para cada vértice adjacente que ainda não foi visitado.
            if (!adjacente.isVisitado()) {
                DFS(adjacente);
            }
        }

        vertice.setTempoPartida(++tempo);
    }

    public void execucaoDfs(){
        //reseta o tempos e status da visitacao antes de comecar
        for(Vertice<T> vertice: vertices){
            vertice.setVisitado(false);
            vertice.setTempoChegada(-1);//o -1 é pq o tempo nao inicializado ainda
            vertice.setTempoPartida(-1);
        }

        for(Vertice<T> vertice:vertices){
            if(!vertice.isVisitado()){
                DFS(vertice);
            }
        }
        System.out.println("Tempos de Chegada e Partida dos Vértices:");
        for (Vertice<T> vertice : vertices) {
            System.out.println("Vértice " + vertice.getDado() + " (Chegada: " + vertice.getTempoChegada() + ", Partida: " + vertice.getTempoPartida() + ")");
        }
    }



    public void imprimir() {
        for (Vertice<T> vertice : vertices) {
            System.out.print("Vértice " + vertice.getDado() + " -> ");
            ArrayList<Vertice<T>> adjacentes = obterAdjacentes(vertice.getDado());
            if (adjacentes.isEmpty()) {
                System.out.print("Sem adjacentes");
            } else {
                System.out.print("Adjacentes -> ");
                for (Vertice<T> adjacente : adjacentes) {
                    System.out.print(adjacente.getDado() + " ");
                }
            }
            System.out.println();
        }
        System.out.println("\nArestas:");
        for (Aresta<T> aresta : arestas) {
            System.out.println("Aresta de " + aresta.getInicio().getDado() + " para " + aresta.getFim().getDado() + " com peso " + aresta.getPeso());
        }

    }

}
