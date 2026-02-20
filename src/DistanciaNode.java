public class DistanciaNode<T> 
        implements Comparable<DistanciaNode<T>>{
            T vertice;
            int distancia;

    public DistanciaNode(T vertice, int distancia) {
        this.vertice = vertice;
        this.distancia = distancia;
    }

    @Override
    public int compareTo(DistanciaNode<T> outro){
        return 
        Integer.compare(this.distancia, outro.distancia);

    }           

}