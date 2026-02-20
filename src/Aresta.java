public class Aresta<T> {
    T vertice;
    int peso;

    public Aresta(T destino, int peso){
        this.vertice = destino;
        this.peso = peso;
    }
}
