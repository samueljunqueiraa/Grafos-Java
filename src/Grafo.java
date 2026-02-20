
import java.util.*;

public class Grafo<T> {

    private HashMap<T, LinkedList<Aresta>> meuGrafo;
    private ArrayList<T> visitados;

    public Grafo() {
        meuGrafo = new HashMap<>();
    }

    public boolean adicionarVertice(T vertice) {
        if (!meuGrafo.containsKey(vertice)) {
            meuGrafo.put(vertice, new LinkedList<>());
            return true;
        }
        return false;
    }

    // adicionar parametro para definir se é dirigido ou não
    public boolean adicionarAresta(T origem, T destino, int peso) {
        if (meuGrafo.containsKey(origem)
                && meuGrafo.containsKey(destino)) {
            meuGrafo.get(origem).add(new Aresta<T>(destino, peso));
            meuGrafo.get(destino).add(new Aresta<T>(origem, peso));
            return true;
        }// fim if
        return false;
    }

    public void mostrarGrafo() {
        for (Map.Entry<T, LinkedList<Aresta>> registro : meuGrafo.entrySet()) {
            T vertice = registro.getKey();
            LinkedList<Aresta> arestas = registro.getValue();
            System.out.print("Vértice " + vertice + ": ");
            for (Aresta aresta : arestas) {
                System.out.print("-> (Destino: " + aresta.vertice + ", Peso: " + aresta.peso + ") ");
            }
            System.out.println("\n");
        }
    }
    public ArrayList<T> buscaProfundidadeDFS(T origem){
        visitados = new ArrayList<>();
        explorarDFS(origem, visitados);
        return visitados; 
    }

    private void explorarDFS(T verticeAtual, ArrayList<T> visitados){
        visitados.add(verticeAtual);
        LinkedList<Aresta> adjacencias = meuGrafo.get(verticeAtual);
        for(Aresta adjacente: adjacencias)
            if(!visitados.contains(adjacente.vertice))
                 explorarDFS((T)adjacente.vertice, visitados);

    }

    public boolean alcance(T origem, T destino){
        visitados = new ArrayList<>();
        boolean existe = existeCaminho(origem, destino, visitados);
        System.out.println("*** Percurso de visitados ***");
        System.out.println(visitados);
        return existe; 
    }

    private boolean existeCaminho(T verticeAtual, T destino, ArrayList<T> visitados){
        if(verticeAtual.equals(destino))
            return true;
        visitados.add(verticeAtual);
        LinkedList<Aresta> adjacencias = meuGrafo.get(verticeAtual);
        for(Aresta adjacente: adjacencias)
            if(!visitados.contains(adjacente.vertice))
                return existeCaminho((T)adjacente.vertice, destino, visitados);

        return false;
    }

    public List<T> explorarLarguraBFS(T inicio){
        Queue<T> fila = new LinkedList<>();
        Map<T,T> predecessores = new HashMap<>();
        visitados = new ArrayList<>();
        fila.add(inicio);
        while(!fila.isEmpty()){
            T verticeAtual = fila.poll();
            visitados.add(verticeAtual);
            LinkedList<Aresta> adjacencias = meuGrafo.get(verticeAtual);
            for(Aresta<T> adj : adjacencias){
                if(!visitados.contains(adj.vertice) && !fila.contains(adj.vertice)){
                    fila.add(adj.vertice);
                    predecessores.put(adj.vertice, verticeAtual);
                }
            }// fim for
        }
        System.out.println("Predecessores:"+predecessores+"\n");
        return visitados;

    }

    public List<T> dijkstra(T origem, T destino){
        Map<T, Integer> distancias = new HashMap<>();
        Map<T, T> predecessores = new HashMap<>();
        PriorityQueue<DistanciaNode<T>> filaPrioridade = 
            new PriorityQueue<>();
        for(T vertice: meuGrafo.keySet()){
            distancias.put(vertice, Integer.MAX_VALUE);
            predecessores.put(vertice, null);
        }
        distancias.put(origem, 0);
        filaPrioridade.add(new DistanciaNode<>(origem,0));

        while(!filaPrioridade.isEmpty()){
            DistanciaNode<T> atual = filaPrioridade.poll();
            T verticeAtual = atual.vertice;
            int distanciaAtual = atual.distancia;

            if(distanciaAtual<= distancias.get(verticeAtual)){
                LinkedList<Aresta> adjacencias = meuGrafo.get(verticeAtual);
                for(Aresta<T> adj: adjacencias){
                    int novaDistancia = distanciaAtual + adj.peso;
                    if(novaDistancia<distancias.get(adj.vertice)){
                        distancias.put(adj.vertice, novaDistancia);
                        predecessores.put(adj.vertice, verticeAtual);
                        filaPrioridade.add(
                            new  DistanciaNode<>(adj.vertice, novaDistancia));
                    }
                }

            }// fim if explorar adjacencias

        }// fim while
        
        // Reconstruir o caminho
        List<T> caminho = new ArrayList<>();
        T vertice = destino;
        while(vertice!=null){
            caminho.add(vertice);
            vertice = predecessores.get(vertice);
        }
         Collections.reverse(caminho);

         System.out.println("Predecessores");
         System.out.println(predecessores);
         System.out.println("Distancias");
         System.out.println(distancias);
        return caminho;
    }


}
