package Q06_grafo;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Grafo6<T> {
    private ArrayList<Vertice<T>> vertices;
    private ArrayList<Aresta<T>> arestas;
    private boolean direcionado;//criado pra diferencia se é direcionado ou nao


    public Grafo6(){
        this.vertices = new ArrayList<>();
        this.arestas = new ArrayList<>();
    }
    public Grafo6(boolean direcionado) {
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