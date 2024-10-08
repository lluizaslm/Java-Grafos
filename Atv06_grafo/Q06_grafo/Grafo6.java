package Q06_grafo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Grafo6<T> {
    private ArrayList<Vertice6<T>> vertices;
    private ArrayList<Aresta6<T>> arestas;

    public Grafo6(){
        this.vertices = new ArrayList<>();
        this.arestas = new ArrayList<>();
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
        Vertice6<T> novoVertice = new Vertice6<T>(dado);
        this.vertices.add(novoVertice);
    }

    // adicionar aresta
    public void adicionarAresta(Double peso, T inicio, T fim) {
        Vertice6<T> verticeInicio = buscarVertice(inicio);
        Vertice6<T> verticeFim = buscarVertice(fim);

        if (verticeInicio != null && verticeFim != null) {
            Aresta6<T> aresta = new Aresta6<>(peso, verticeInicio, verticeFim);
            Aresta6<T> aresta2 = new Aresta6<>(peso, verticeFim, verticeInicio);

            arestas.add(aresta);
            verticeInicio.adicionarAresta(aresta);
            verticeFim.adicionarAresta(aresta2);
        }
    }

    public void removerVertice(T dado) {
        Vertice6<T> vertice =buscarVertice(dado);
        if(vertice!=null){

            ArrayList<Aresta6<T>> arestasAssociadas =new ArrayList<>(vertice.getArestas());//aqui cria uma nova lista pra adc as arestas que vão ta associadas ao vertice
            for(Aresta6<T> aresta :arestasAssociadas){
                Vertice6<T> verticeInicio = aresta.getInicio();
                Vertice6<T> verticeFim = aresta.getFim();
                verticeInicio.getArestas().remove(aresta);//pra pegar o vertice que a aresta ta saindo
                verticeFim.getArestas().remove(aresta);//e aqui o vertice que aresta ta chegando

                arestas.remove(aresta);//pra remover as arestas
            }
            vertices.remove(vertice);
        }
    }

    public void removerAresta(T inicio, T fim) {
        Vertice6<T> verticeInicio=buscarVertice(inicio);
        Vertice6<T> verticeFim = buscarVertice(fim);

        if(verticeInicio != null && verticeFim != null){
            Aresta6<T> aresta = buscarAresta(inicio, fim);
            if(aresta != null){
                arestas.remove(aresta);
                verticeInicio.getArestas().remove(aresta);//remover a aresta que ta saindo do vertice de inicio
                verticeFim.getArestas().remove(aresta);//remover a aresta que ta entrando do vertice "final"
            }

        }
    }

    public Aresta6<T> buscarAresta(T inicio, T fim){
        Vertice6<T> verticeInicio=buscarVertice(inicio);
        Vertice6<T> verticeFim = buscarVertice(fim);

        if(verticeInicio != null && verticeFim != null ){
            for(Aresta6<T> aresta :arestas){
                if(aresta.getInicio().equals(verticeInicio) && aresta.getFim().equals(verticeFim)){
                    return aresta;
                }
            }
        }
        return null;
    }

    private Vertice6<T> buscarVertice(T dado) {
        for (Vertice6<T> vertice : vertices) {
            if (vertice.getDado().equals(dado)) {
                return vertice;
            }
        }
        return null;
    }

    public ArrayList<Vertice6<T>> obterAdjacentes(T dado){
        Vertice6<T> vertice =buscarVertice(dado);
        ArrayList<Vertice6<T>> adjacentes =new ArrayList<>();
        if(vertice!= null){
            for(Aresta6<T> aresta : vertice.getArestas()){
                adjacentes.add(aresta.getFim());
            }
        }
        return adjacentes;
    }

    public  ArrayList<Aresta6<T>> ArvorePrim(){
        var naArvore = new boolean[vertices.size()];
        var pilha = new PriorityQueue<Aresta6<T>>();
        var resultado = new ArrayList<Aresta6<T>>();

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
        return resultado;
    }

    private void DFS(ArrayList<Vertice6<T>> ordemVisita, Set<Vertice6<T>> visitado, Vertice6<T> v) {
        visitado.add(v);
        ordemVisita.add(v);

        for (var aresta : obterArestas(v)){
            var vizinho = aresta.getInicio() == v ? aresta.getFim() : aresta.getInicio();
            if (!visitado.contains(vizinho)) {
                DFS(ordemVisita, visitado, vizinho);
            }
        }
    }

    private ArrayList<Aresta6<T>> obterArestas(Vertice6<T> v){
        ArrayList<Aresta6<T>> adj = new ArrayList<>();
        for (var aresta : arestas){
            if (aresta.getInicio().equals(v) || aresta.getFim().equals(v)) {
                adj.add(aresta);
            }
        }
        return adj;
    }

    public void TSP(){
        var arvoreMinima = ArvorePrim();

        var ordemVisita = new ArrayList<Vertice6<T>>();
        Set<Vertice6<T>> visitado = new HashSet<>();
        DFS(ordemVisita, visitado, vertices.get(0));

        criarCriclo(ordemVisita);
    }

    private void criarCriclo(ArrayList<Vertice6<T>> ordemVisita){
        double pesoTotal = 0;

        for (int i = 0; i < ordemVisita.size(); i++) {
            var atual = ordemVisita.get(i);
            var proximo = ordemVisita.get((i+1) % ordemVisita.size());

            var aresta = buscarAresta(atual.getDado(), proximo.getDado()) == null ?
                    buscarAresta(proximo.getDado(), atual.getDado()) :
                    buscarAresta(atual.getDado(), proximo.getDado());
            System.out.println("Vértice " + atual.getDado() + " - " + proximo.getDado() + " com peso: " + aresta.getPeso());
            pesoTotal += aresta.getPeso();
        }
        System.out.println("Peso total do ciclo: " + pesoTotal);
    }

    public void imprimir() {
        for (Vertice6<T> vertice : vertices) {
            System.out.print("Vértice " + vertice.getDado() + " -> ");
            ArrayList<Vertice6<T>> adjacentes = obterAdjacentes(vertice.getDado());
            if (adjacentes.isEmpty()) {
                System.out.print("Sem adjacentes");
            } else {
                System.out.print("Adjacentes -> ");
                for (Vertice6<T> adjacente : adjacentes) {
                    System.out.print(adjacente.getDado() + " ");
                }
            }
            System.out.println();
        }
        System.out.println("Arestas:");
        for (Aresta6<T> aresta : arestas) {
            System.out.println("Aresta de " + aresta.getInicio().getDado() + " para " + aresta.getFim().getDado() + " com peso " + aresta.getPeso());
        }

    }

}