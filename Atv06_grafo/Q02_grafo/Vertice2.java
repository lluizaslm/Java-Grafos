package Q02_grafo;


import java.util.ArrayList;

public class Vertice2<T>{
    private T dado;
    private ArrayList<Aresta2<T>> arestasEntrada;
    private ArrayList<Aresta2<T>> arestasSaida;
    private boolean visitado; // pra ver se foi visitado
    private int tempoChegada;
    private int tempoPartida;

    public Vertice2(T valor){
        this.dado = valor;
        this.arestasEntrada = new ArrayList<Aresta2<T>>();
        this.arestasSaida = new ArrayList<Aresta2<T>>();
        this.visitado = false;
    }

    public T getDado() {
        return dado;
    }

    public void setDado(T dado) {
        this.dado = dado;
    }

    public void adicionarArestaEntrada(Aresta2<T> aresta){
        this.arestasEntrada.add(aresta);
    }

    public void adicionarArestaSaida(Aresta2<T> aresta){
        this.arestasSaida.add(aresta);
    }

    public ArrayList<Aresta2<T>> getArestasEntrada() {
        return arestasEntrada;
    }

    public ArrayList<Aresta2<T>> getArestasSaida() {
        return arestasSaida;
    }

    public boolean isVisitado() {
        return visitado;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }

    public int getTempoChegada() {
        return tempoChegada;
    }

    public void setTempoChegada(int tempoChegada) {
        this.tempoChegada = tempoChegada;
    }

    public int getTempoPartida() {
        return tempoPartida;
    }

    public void setTempoPartida(int tempoPartida) {
        this.tempoPartida = tempoPartida;
    }

}
