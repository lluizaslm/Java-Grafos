package Q05_grafo;

public class Aresta5<T>{
    private Double peso;
    private Vertice5<T> inicio;
    private Vertice5<T> fim;


    public Aresta5(Double peso, Vertice5<T> inicio, Vertice5<T> fim){
        this.peso = peso;
        this.inicio = inicio;
        this.fim = fim;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Vertice5<T> getInicio() {
        return inicio;
    }

    public void setInicio(Vertice5<T> inicio) {
        this.inicio = inicio;
    }

    public Vertice5<T> getFim() {
        return fim;
    }

    public void setFim(Vertice5<T> fim) {
        this.fim = fim;
    }
}
