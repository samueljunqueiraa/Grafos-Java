# Grafos em Java — Projeto Didático

Este repositório contém um projeto didático para apoiar o ensino de estruturas de dados de grafos em Java. A ideia é permitir que você visualize, crie e manipule um grafo ponderado não dirigido usando um menu interativo no console.

O foco é a compreensão de conceitos-chave como: representação por lista de adjacência, uso de tipos genéricos, mapeamento de vértices, igualdade e hashing, além de operações básicas de inserção de vértices e arestas.

## Objetivos de aprendizagem

- Entender como representar um grafo por listas de adjacência em Java.
- Praticar Generics (uso de `Grafo<T>` e `Aresta<T>`).
- Usar coleções da Java Collections Framework (`HashMap`, `LinkedList`).
- Definir corretamente `equals` e `hashCode` para chaves de mapa (classe `Cidade`).
- Implementar operações básicas: adicionar vértice, adicionar aresta, exibir o grafo.

## Como o grafo é modelado

O grafo é representado por um mapa onde cada vértice aponta para uma lista de arestas de saída:

```
HashMap<T, LinkedList<Aresta>>
```

Neste projeto, os vértices são objetos do tipo `Cidade` e as arestas são instâncias de `Aresta<T>` contendo:
- `vertice`: o destino (vértice vizinho)
- `peso`: o custo/tempo/distância (inteiro)

O método `adicionarAresta(origem, destino, peso)` cria duas ligações (ida e volta), ou seja, o grafo é não dirigido (simétrico) por padrão.

## Estrutura do projeto

```
arestas.csv               # Dados de exemplo 
arestasNumeros.csv        # Dados de exemplo 
cidades.csv               # Dados de exemplo 
cidadesNumeros.csv        # Dados de exemplo )
bin/                      # Saída compilada (gerada pelo VS Code Java)
lib/                      # Dependências (se houver)
src/
	App.java                # Menu de console para manipular o grafo
	Aresta.java             # Classe de aresta genérica (destino + peso)
	Cidade.java             # Modelo de vértice com nome, estado e CEP
	Grafo.java              # Implementação do grafo por lista de adjacência
```

### Visão geral das classes

- `Cidade`
	- Campos: `nome`, `estado`, `cep` (CEP é a identidade única do vértice).
	- Implementa `equals` e `hashCode` baseados em `cep`, garantindo unicidade em `HashMap`.

- `Aresta<T>`
	- Representa uma ligação ponderada até um vértice `T` com um `peso` inteiro.

- `Grafo<T>`
	- Mantém `HashMap<T, LinkedList<Aresta>>`.
	- Métodos principais:
		- `adicionarVertice(T v)` — cria a lista de adjacência para o vértice caso ainda não exista.
		- `adicionarAresta(T origem, T destino, int peso)` — adiciona arestas em ambas as direções.
		- `mostrarGrafo()` — imprime todas as adjacências e seus pesos.

- `App`
	- Interface de console com menu para criar cidades, conectar arestas e exibir o grafo.

## Como executar

Você pode executar pelo próprio Visual Studio Code (com a extensão Java instalada):

1. Abra a pasta do projeto no VS Code.
2. Abra `src/App.java` e use “Run”/“Debug” na linha do `main`.
3. O programa abrirá um menu no terminal integrado.

Opcional (para referência): é possível compilar/rodar via terminal com JDK instalado, mas a execução pelo VS Code é recomendada neste contexto didático.

## Como usar (menu do console)

Ao executar, você verá um menu com opções como:

1. Adicionar Vértice — informe `nome`, `estado` e `cep` para criar uma `Cidade`.
2. Adicionar Aresta — informe os CEPs de origem e destino já cadastrados e um `peso` (inteiro).
3. Mostrar Grafo — imprime todos os vértices e suas arestas com pesos.
0. Sair — finaliza a aplicação.

Notas importantes:
- A identidade da cidade é o `CEP`. Não crie dois vértices com o mesmo CEP.
- Ao adicionar aresta, a origem e o destino devem existir previamente (mesmo CEP cadastrado). Caso contrário, a operação não será realizada.
- O menu exibe a opção “4. Verificar Alcance”, mas ela ainda não está implementada no código — é um gancho para atividades futuras (ver exercícios abaixo).

## Dados de exemplo (CSV)

Arquivos como `cidades.csv` e `arestas.csv` estão incluídos como materiais didáticos. O código atual não realiza leitura automática desses arquivos. Eles podem ser usados como base para uma atividade de “importação de grafo a partir de CSV”.

Sugestão de formato esperado (exemplo):

- `cidades.csv`: `nome,estado,cep`
- `arestas.csv`: `cep_origem,cep_destino,peso`


## Exercícios

Para aprofundar o aprendizado, implemente e/ou explore:

1. Verificar alcance (BFS/DFS)
	 - Implemente a opção de menu “4. Verificar Alcance” usando BFS para responder: “a cidade X alcança Y?”.
2. Grafo dirigido
	 - Adapte `adicionarAresta` para aceitar um parâmetro booleano indicando se adiciona aresta de volta (não dirigido) ou não (dirigido).
3. Remoção de vértices e arestas
	 - Crie métodos `removerVertice(T v)` e `removerAresta(T u, T v)` com as devidas atualizações nas listas de adjacência.
4. Caminho mínimo (Dijkstra)
	 - Dado um CEP de origem e destino, calcule a menor distância considerando os pesos.
5. Carregar a partir de CSV
	 - Leia `cidades.csv` e `arestas.csv` para popular o grafo automaticamente.
6. Validação de dados
	 - Trate entradas inválidas (ex.: CEP inexistente, peso negativo, aresta duplicada) e forneça mensagens amigáveis.

## Perguntas frequentes (FAQ)

- Por que `equals`/`hashCode` em `Cidade` usam apenas o CEP?
	- Porque `HashMap` usa hashing/igualdade para garantir unicidade das chaves; o CEP é o identificador natural de uma cidade, evitando duplicidade mesmo que o nome/estado sejam iguais.

- Por que generics (`Grafo<T>`)?
	- Para tornar o grafo reutilizável com qualquer tipo de vértice (não só `Cidade`).

## Requisitos

- JDK 17+ (recomendado) e Visual Studio Code com a extensão “Extension Pack for Java” da Microsoft/Red Hat.

---

Bom estudo! Este projeto foi pensado para ser simples, legível e extensível — sinta‑se à vontade para experimentar melhorias e algoritmos adicionais.