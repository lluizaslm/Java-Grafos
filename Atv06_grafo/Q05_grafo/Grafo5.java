package Q05_grafo;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Grafo5<T> {
    private ArrayList<Vertice5<T>> vertices;
    private ArrayList<Aresta5<T>> arestas;



    public Grafo5(){
        this.vertices = new ArrayList<>();
        this.arestas = new ArrayList<>();
    }

    // adicionar vértice
    public void adicionarVertice(T dado) {
        Vertice5<T> novoVertice = new Vertice5<T>(dado);
        this.vertices.add(novoVertice);
    }

    // adicionar aresta
    public void adicionarAresta(Double peso, T inicio, T fim) {
        Vertice5<T> verticeInicio = buscarVertice(inicio);
        Vertice5<T> verticeFim = buscarVertice(fim);

        if (verticeInicio != null && verticeFim != null) {
            Aresta5<T> aresta = new Aresta5<>(peso, verticeInicio, verticeFim);
            Aresta5<T> aresta2 = new Aresta5<>(peso, verticeFim, verticeInicio);

            arestas.add(aresta);
            verticeInicio.adicionarAresta(aresta);
            verticeFim.adicionarAresta(aresta2);
        }
    }

    public void removerVertice(T dado) {
        Vertice5<T> vertice =buscarVertice(dado);
        if(vertice!=null){

            ArrayList<Aresta5<T>> arestasAssociadas =new ArrayList<>(vertice.getArestas());//aqui cria uma nova lista pra adc as arestas que vão ta associadas ao vertice
            for(Aresta5<T> aresta :arestasAssociadas){
                Vertice5<T> verticeInicio = aresta.getInicio();
                Vertice5<T> verticeFim = aresta.getFim();
                verticeInicio.getArestas().remove(aresta);//pra pegar o vertice que a aresta ta saindo
                verticeFim.getArestas().remove(aresta);//e aqui o vertice que aresta ta chegando

                arestas.remove(aresta);//pra remover as arestas
            }
            vertices.remove(vertice);
        }
    }

    public void removerAresta(T inicio, T fim) {
        Vertice5<T> verticeInicio=buscarVertice(inicio);
        Vertice5<T> verticeFim = buscarVertice(fim);

        if(verticeInicio != null && verticeFim != null){
            Aresta5<T> aresta = buscarAresta(inicio, fim);
            if(aresta != null){
                arestas.remove(aresta);
                verticeInicio.getArestas().remove(aresta);//remover a aresta que ta saindo do vertice de inicio
                verticeFim.getArestas().remove(aresta);//remover a aresta que ta entrando do vertice "final"
            }

        }
    }

    public Aresta5<T> buscarAresta(T inicio, T fim){
        Vertice5<T> verticeInicio=buscarVertice(inicio);
        Vertice5<T> verticeFim = buscarVertice(fim);

        if(verticeInicio != null && verticeFim != null ){
            for(Aresta5<T> aresta :arestas){
                if(aresta.getInicio().equals(verticeInicio) && aresta.getFim().equals(verticeFim)){
                    return aresta;
                }
            }
        }
        return null;
    }

    private Vertice5<T> buscarVertice(T dado) {
        for (Vertice5<T> vertice : vertices) {
            if (vertice.getDado().equals(dado)) {
                return vertice;
            }
        }
        return null;
    }

    public ArrayList<Vertice5<T>> obterAdjacentes(T dado){
        Vertice5<T> vertice =buscarVertice(dado);
        ArrayList<Vertice5<T>> adjacentes =new ArrayList<>();
        if(vertice!= null){
            for(Aresta5<T> aresta : vertice.getArestas()){
                adjacentes.add(aresta.getFim());
            }
        }
        return adjacentes;
    }

    public  void ArvorePrim(){
        var naArvore = new boolean[vertices.size()];
        var pilha = new PriorityQueue<Aresta5<T>>();
        var resultado = new ArrayList<Aresta5<T>>();

        naArvore[0] = true;;
        pilha.addAll(vertices.get(0).getArestas());

        while (!pilha.isEmpty()){
            var aresta = pilha.poll();
            int posicaoDestino = 0;
            for (int i = 0; i < vertices.size(); i++) {
                if (vertices.get(i).equals(aresta.getFim())){
                    posicaoDestino = i;
                    break;
                }
            }
            if(naArvore[posicaoDestino]){
                continue;
            }

            naArvore[posicaoDestino] = true;
            resultado.add(aresta);
            var novasArestas = vertices.get(posicaoDestino).getArestas();
            for (var adj : novasArestas){
                for (int i = 0; i < vertices.size(); i++) {
                    if (vertices.get(i).equals(adj.getFim())){
                        posicaoDestino = i;
                        break;
                    }
                }
                if (!naArvore[posicaoDestino]){
                    pilha.add(adj);
                }
            }
        }

        int pesoTotal = 0;
        for (var aresta : resultado){
            System.out.println("Aresta: " + aresta.getInicio().getDado() + " - " + aresta.getFim().getDado() + " com peso: " + aresta.getPeso());
            pesoTotal += aresta.getPeso();
        }
        System.out.println("Peso total da Arvore Prim: " + pesoTotal);
    }
    public void ArvoreKruskal(){}
    public void ArvoreBoruvka(){}

    public void imprimir() {
        for (Vertice5<T> vertice : vertices) {
            System.out.print("Vértice " + vertice.getDado() + " -> ");
            ArrayList<Vertice5<T>> adjacentes = obterAdjacentes(vertice.getDado());
            if (adjacentes.isEmpty()) {
                System.out.print("Sem adjacentes");
            } else {
                System.out.print("Adjacentes -> ");
                for (Vertice5<T> adjacente : adjacentes) {
                    System.out.print(adjacente.getDado() + " ");
                }
            }
            System.out.println();
        }
        System.out.println("\nArestas:");
        for (Aresta5<T> aresta : arestas) {
            System.out.println("Aresta de " + aresta.getInicio().getDado() + " para " + aresta.getFim().getDado() + " com peso " + aresta.getPeso());
        }

    }

}