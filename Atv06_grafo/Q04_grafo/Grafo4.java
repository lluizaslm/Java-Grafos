package Q04_grafo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Grafo4<T> {
    private ArrayList<Vertice<T>> vertices;
    private ArrayList<Aresta<T>> arestas;
    private boolean direcionado;//criado pra diferencia se é direcionado ou nao


    public Grafo4(){
        this.vertices = new ArrayList<>();
        this.arestas = new ArrayList<>();
    }
    public Grafo4(boolean direcionado) {
        this.vertices = new ArrayList<>();
        this.arestas = new ArrayList<>();
        this.direcionado = direcionado;
    }

    public void carregarDeArquivo(String caminhoArquivo) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo));
        String linha;

        while ((linha = reader.readLine()) != null) {
            String[] partes = linha.split(";");
            if (partes.length == 3) {
                T verticeInicio = (T) partes[0];
                T verticeFim = (T) partes[1];
                Double peso = Double.parseDouble(partes[2]);

                adicionarVerticeSeNaoExistir(verticeInicio);
                adicionarVerticeSeNaoExistir(verticeFim);

                adicionarAresta(peso, verticeInicio, verticeFim);
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

                arestas.remove(aresta);//pra remover as arestas
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

    private boolean Dfs(Vertice<T> vertice, Map<Vertice<T>, Integer> cores, int corAtual) {
        cores.put(vertice, corAtual);//o vertice se associa a cor atual

        for (Vertice<T> adjacente : obterAdjacentes(vertice.getDado())) {
            if (!cores.containsKey(adjacente)) {
                if (!Dfs(adjacente, cores, 1 - corAtual)) {
                    return false;//vai retornar false se algum vertice não ser colorido certo(não bipartido)
                }
            } else if (cores.get(adjacente).equals(corAtual)) {
                return false;//aqui verifica se o vertice adjacente já foi colorido e tem a mesma cor do vértice atual
            }
        }
        return true;
    }


    public boolean verificarBipartido() {
        Map<Vertice<T>, Integer> cores = new HashMap<>();//aqui vai criar esse mapa pra associar cada vertice a uma cor
        for (Vertice<T> vertice : vertices) {
            if (!cores.containsKey(vertice)) {//se nao tiver com cor ainda ele inicia o DFS nesse vertice
                if (!Dfs(vertice, cores, 0)) {
                    return false;//Se a DFS detectar uma condição onde o grafo não pode ser bipartido (vértices adjacentes com a mesma cor), o método retorna false.
                }
            }
        }
        return true;
    }


    private void dfsParticoes(Vertice<T> vertice, Map<Vertice<T>, Integer> cores, int corAtual, ArrayList<T> particao1, ArrayList<T> particao2) {

        cores.put(vertice, corAtual);

        if (corAtual == 0) {
            particao1.add(vertice.getDado());//vai adc o vertice na particao correta
        } else {
            particao2.add(vertice.getDado());
        }

        for (Vertice<T> adjacente : obterAdjacentes(vertice.getDado())) {
            if (!cores.containsKey(adjacente)) {//aqui chama recursivo pra cada adjacente q não foi colorido
                dfsParticoes(adjacente, cores, 1 - corAtual, particao1, particao2);
            }
        }
    }


    public void imprimirParticoes() {
        Map<Vertice<T>, Integer> cores = new HashMap<>();
        ArrayList<T> particao1 = new ArrayList<>();
        ArrayList<T> particao2 = new ArrayList<>();


        for (Vertice<T> vertice : vertices) {
            if (!cores.containsKey(vertice)) {
                dfsParticoes(vertice, cores, 0, particao1, particao2);
            }
        }

        if (verificarBipartido()) {
            System.out.println("O grafo É bipartido:");
            System.out.println("Partição 1: " + particao1);
            System.out.println("Partição 2: " + particao2);
        } else {
            System.out.println("O grafo NÃO é bipartido");
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