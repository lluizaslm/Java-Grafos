package Q03_grafo;

public class Aresta3<T>{
    private Double peso;
    private Vertice3<T> inicio;
    private Vertice3<T> fim;


    public Aresta3(Double peso, Vertice3<T> inicio, Vertice3<T> fim){
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

    public Vertice3<T> getInicio() {
        return inicio;
    }

    public void setInicio(Vertice3<T> inicio) {
        this.inicio = inicio;
    }

    public Vertice3<T> getFim() {
        return fim;
    }

    public void setFim(Vertice3<T> fim) {
        this.fim = fim;
    }
}
