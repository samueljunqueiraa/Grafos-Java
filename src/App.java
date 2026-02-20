
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class App {

    static void carregarCidades(Grafo<Cidade> g, HashMap<Integer, Cidade> cepCidades, String caminhoArquivo) throws Exception {
        String linha;
        try (BufferedReader br = Files.newBufferedReader(Paths.get(caminhoArquivo), StandardCharsets.UTF_8)) {
            while ((linha = br.readLine()) != null) {
                String[] campos = linha.split(","); // divide os campos por vírgula
                String nome = campos[0].trim();
                String estado = campos[1].trim();
                int cep = Integer.parseInt(campos[2].trim());
                Cidade cidade = new Cidade(nome, estado, cep);
                g.adicionarVertice(cidade);
                cepCidades.put(cep, cidade);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void carregarArestas(Grafo<Cidade> g, HashMap<Integer, Cidade> cepCidades, String caminhoArquivo) throws Exception {
        String linha;
        try (BufferedReader br = Files.newBufferedReader(Paths.get(caminhoArquivo), StandardCharsets.UTF_8)) {
            while ((linha = br.readLine()) != null) {
                String[] campos = linha.split(","); // divide os campos por vírgula
                int cepOrigem = Integer.parseInt(campos[0].trim());
                int cepDestino = Integer.parseInt(campos[1].trim());
                int peso = Integer.parseInt(campos[2].trim());
                Cidade origem = cepCidades.get(cepOrigem);
                Cidade destino = cepCidades.get(cepDestino);
                g.adicionarAresta(origem, destino, peso);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int menu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Adicionar Vértice");
        System.out.println("2. Adicionar Aresta");
        System.out.println("3. Mostrar Grafo");
        System.out.println("4. Verificar Alcance");
        System.out.println("5. Busca em Profundidade (DFS)  ");
        System.out.println("6. Busca em Largura (BFS) ");
        System.out.println("7. Caminho minimo (Dijkstra) ");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
        int op = scanner.nextInt();
        return op;
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        HashMap<Integer, Cidade> cepCidades = new HashMap<>();
        Grafo<Cidade> grafo = new Grafo<>();
        carregarCidades(grafo, cepCidades, "grafoaula.csv");
        carregarArestas(grafo, cepCidades, "arestasaula.csv");
        int opcao;

        do {
            System.out.println("\n--- Menu --- Pressione uma tecla para continuar ---");
            scanner.nextLine();
            opcao = menu();

            switch (opcao) {
                case 1:
                    System.out.println("Entre com nome, estado e cep da cidade:");
                    System.out.print("Nome:");
                    String nomeCidade = scanner.nextLine();
                    System.out.print("Estado:");
                    String estado = scanner.nextLine();
                    System.out.print("CEP:");
                    int cep = scanner.nextInt();
                    Cidade novaCidade = new Cidade(nomeCidade, estado, cep);
                    if (grafo.adicionarVertice(novaCidade) == false) {
                        System.out.println("Vertice já existente");
                    } else {
                        System.out.println("Vertice adicionado");
                        cepCidades.put(cep, novaCidade);
                    }
                    break;
                case 2:
                    System.out.print("Digite o vértice de origem: ");
                    int cepOrigem = scanner.nextInt();
                    Cidade origem = cepCidades.get(cepOrigem);
                    System.out.println("Origem selecionada: " + origem);
                    System.out.print("Digite o vértice de destino: ");
                    int cepDestino = scanner.nextInt();
                    Cidade destino = cepCidades.get(cepDestino);
                    System.out.println("Destino selecionado: " + destino);
                    System.out.print("Digite o peso da aresta: ");
                    int peso = scanner.nextInt();
                    if (grafo.adicionarAresta(origem, destino, peso)) {
                        System.out.println("Aresta adicionada com sucesso.");
                    } else {
                        System.out.println("Erro ao adicionar aresta.");
                    }
                    break;
                case 3:
                    grafo.mostrarGrafo();
                    break;
                case 4: System.out.print("Digite o vértice de origem: ");
                        cepOrigem = scanner.nextInt();
                        origem = cepCidades.get(cepOrigem);
                        System.out.println("Origem selecionada: " + origem);
                        System.out.print("Digite o vértice de destino: ");
                        cepDestino = scanner.nextInt();
                        destino = cepCidades.get(cepDestino);
                        System.out.println("Destino selecionado: " + destino);
                        if(grafo.alcance(origem, destino)==true)
                            System.out.println("Existe caminho :)");
                         else
                            System.out.println("Não existe caminho :(");
                    break;     
                case 5: System.out.print("Digite o vértice de origem para DFS: ");
                        cepOrigem = scanner.nextInt();
                        origem = cepCidades.get(cepOrigem);
                        System.out.println("Origem selecionada: " + origem);
                        System.out.println("*** Percurso DFS ***");
                        System.out.println(grafo.buscaProfundidadeDFS(origem));
                        break;
                case 6: 
                    System.out.println("Busca em Largura (BFS)");
                    System.out.print("Digite o vértice de origem para BFS: ");
                        cepOrigem = scanner.nextInt();
                        origem = cepCidades.get(cepOrigem);
                        System.out.println("Origem selecionada: " + origem);
                        System.out.println("*** Percurso BFS ***");
                        System.out.println(grafo.explorarLarguraBFS(origem));
                    break;      
                case 7: 
                    System.out.println("Caminho minimo (Dijkstra)");
                    System.out.print("Digite o vértice de origem: ");
                        cepOrigem = scanner.nextInt();
                        origem = cepCidades.get(cepOrigem);
                        System.out.println("Origem selecionada: " + origem);
                        System.out.print("Digite o vértice de destino: ");
                        cepDestino = scanner.nextInt();
                        destino = cepCidades.get(cepDestino);
                        System.out.println("Destino selecionado: " + destino);
                        List<Cidade> caminho = grafo.dijkstra(origem, destino);
                         System.out.println("*** Caminho ***");
                        System.out.println(caminho);

                    break;      
                case 0:
                    System.out.println("Saindo...");
                    break;

            }

        } while (opcao != 0);

    }
}
