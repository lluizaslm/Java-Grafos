package Q06_grafo;

public class Aresta6<T> implements Comparable<Aresta6<T>>{
    private Double peso;
    private Vertice6<T> inicio;
    private Vertice6<T> fim;


    public Aresta6(Double peso, Vertice6<T> inicio, Vertice6<T> fim){
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

    public Vertice6<T> getInicio() {
        return inicio;
    }

    public void setInicio(Vertice6<T> inicio) {
        this.inicio = inicio;
    }

    public Vertice6<T> getFim() {
        return fim;
    }

    public void setFim(Vertice6<T> fim) {
        this.fim = fim;
    }

    @Override
    public int compareTo(Aresta6<T> o) {
        return (int)(this.peso - o.peso);
    }
}
