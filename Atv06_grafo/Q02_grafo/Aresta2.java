package Q02_grafo;

public class Aresta2<T>{
    private Double peso;
    private Vertice2<T> inicio;
    private Vertice2<T> fim;




    public Aresta2(Double peso, Vertice2<T> inicio, Vertice2<T> fim){
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
    public Vertice2<T> getInicio() {
        return inicio;
    }

    public void setInicio(Vertice2<T> inicio) {
        this.inicio = inicio;
    }

    public Vertice2<T> getFim() {
        return fim;
    }

    public void setFim(Vertice2<T> fim) {
        this.fim = fim;
    }
}
