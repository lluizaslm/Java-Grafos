package Q03_grafo;

import java.util.ArrayList;

public class Grafo3<T> {
    private ArrayList<Vertice3<T>> vertices;
    private ArrayList<Aresta3<T>> arestas;
    private boolean direcionado;//criado pra diferencia se é direcionado ou nao

    public Grafo3(){
        this.vertices = new ArrayList<>();
        this.arestas = new ArrayList<>();
    }

    public Grafo3(boolean direcionado) {
        this.vertices = new ArrayList<>();
        this.arestas = new ArrayList<>();
        this.direcionado = direcionado;
    }
    // adicionar vértice
    public void adicionarVertice(T dado) {
        Vertice3<T> novoVertice = new Vertice3<T>(dado);
        this.vertices.add(novoVertice);
    }

    // adicionar aresta
    public void adicionarAresta(Double peso, T inicio, T fim) {
        Vertice3<T> verticeInicio = buscarVertice(inicio);
        Vertice3<T> verticeFim = buscarVertice(fim);

        if (verticeInicio != null && verticeFim != null) {
            Aresta3<T> aresta = new Aresta3<>(peso, verticeInicio, verticeFim);
            arestas.add(aresta);
            verticeInicio.adicionarArestaSaida(aresta);
            verticeFim.adicionarArestaEntrada(aresta);
        }

        //adc aresta ao contrario tb, por conta do grafo não direcional que é bidirecional A->B||B->A

    }

    public void removerVertice(T dado) {
        Vertice3<T> vertice =buscarVertice(dado);
        if(vertice!=null){

            ArrayList<Aresta3<T>> arestasAssociadas =new ArrayList<>(vertice.getArestasEntrada());//aqui cria uma nova lista pra adc as arestas que vão ta associadas ao vertice
            arestasAssociadas.addAll(vertice.getArestasSaida());
            for(Aresta3<T> aresta :arestasAssociadas){
                Vertice3<T> verticeInicio =aresta.getInicio();
                Vertice3<T> verticeFim =aresta.getFim();
                verticeInicio.getArestasSaida().remove(aresta);//pra pegar o vertice que a aresta ta saindo
                verticeFim.getArestasEntrada().remove(aresta);//e aqui o vertice que aresta ta chegando

                arestas.remove(arestas);//pra remover as arestas
            }
            vertices.remove(vertice);
        }
    }

    //so ta grafo direcionado
    public void removerAresta(T inicio, T fim) {
        Vertice3<T> verticeInicio=buscarVertice(inicio);
        Vertice3<T> verticeFim = buscarVertice(fim);

        if(verticeInicio != null && verticeFim != null){
            Aresta3<T> aresta = buscarAresta(inicio, fim);
            if(aresta != null){
                arestas.remove(aresta);
                verticeInicio.getArestasSaida().remove(aresta);//remover a aresta que ta saindo do vertice de inicio
                verticeFim.getArestasEntrada().remove(aresta);//remover a aresta que ta entrando do vertice "final"
            }

        }
    }

    //so ta grafo direcionado
    public Aresta3<T> buscarAresta(T inicio, T fim){
        Vertice3<T> verticeInicio=buscarVertice(inicio);
        Vertice3<T> verticeFim = buscarVertice(fim);

        if(verticeInicio != null && verticeFim != null ){
            for(Aresta3<T> aresta :arestas){
                if(aresta.getInicio().equals(verticeInicio) && aresta.getFim().equals(verticeFim)){
                    return aresta;
                }
            }
        }
        return null;
    }

    // buscar o vertice
    private Vertice3<T> buscarVertice(T dado) {
        for (Vertice3<T> vertice : vertices) {
            if (vertice.getDado().equals(dado)) {
                return vertice;
            }
        }
        return null;
    }

    public ArrayList<Vertice3<T>> obterAdjacentes(T dado){
        Vertice3<T> vertice =buscarVertice(dado);
        ArrayList<Vertice3<T>> adjacentes =new ArrayList<>();
        if(vertice!= null){
            for(Aresta3<T> aresta : vertice.getArestasSaida()){
                adjacentes.add(aresta.getFim());
            }
        }
        return adjacentes;
    }

    public void imprimir() {
        for (Vertice3<T> vertice : vertices) {
            System.out.print("Vértice " + vertice.getDado() + " -> ");
            ArrayList<Vertice3<T>> adjacentes = obterAdjacentes(vertice.getDado());
            if (adjacentes.isEmpty()) {
                System.out.print("Sem adjacentes");
            } else {
                System.out.print("Adjacentes -> ");
                for (Vertice3<T> adjacente : adjacentes) {
                    System.out.print(adjacente.getDado() + " ");
                }
            }
            System.out.println();
        }
        System.out.println("\nArestas:");
        for (Aresta3<T> aresta : arestas) {
            System.out.println("Aresta de " + aresta.getInicio().getDado() + " para " + aresta.getFim().getDado() + " com peso " + aresta.getPeso());
        }

    }


    public ArrayList<Vertice3<T>> temRaiz(){
        var verticesRaiz = new ArrayList<Vertice3<T>>();

        for (var vertice : vertices){
            var visitados = new ArrayList<Vertice3<T>>();
            DFS(vertice, visitados);
            if (visitados.size() == vertices.size()){
                verticesRaiz.add(vertice);
            }
        }
        return  verticesRaiz;
    }

    private void DFS(Vertice3<T> vertice, ArrayList<Vertice3<T>> visitados) {
        visitados.add(vertice);

        for (Aresta3<T> aresta : vertice.getArestasSaida()) {
            Vertice3<T> adjacente = aresta.getFim();//itera sobre todas as arestas de saída do vértice atual e vai chamar DFS para cada vértice adjacente que ainda não foi visitado.
            if (!visitados.contains(adjacente)) {
                DFS(adjacente, visitados);
            }
        }
    }
}