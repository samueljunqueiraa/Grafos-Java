# Estrutura de Dados: Grafos em Java

Este projeto foi desenvolvido como parte da disciplina de **Estrutura de Dados** no final de 2024, durante o curso de Sistemas de Informação no **IFSULDEMINAS**. O objetivo é explorar a implementação de um Grafo Ponderado Não Dirigido utilizando listas de adjacência.

## 📌 Sobre o Projeto

O sistema permite a criação, manipulação e visualização de um grafo onde os vértices representam **Cidades** e as arestas representam conexões (como estradas) com pesos específicos (distância, tempo ou custo).


### Conceitos-Chave Implementados:
* **Lista de Adjacência:** Uso de `HashMap<T, LinkedList<Aresta<T>>>` para uma representação eficiente em memória.
* **Tipos Genéricos (Generics):** A classe `Grafo<T>` foi projetada para ser reutilizável com qualquer tipo de objeto, não apenas cidades.
* **Unicidade e Hashing:** Implementação rigorosa de `equals` e `hashCode` na classe `Cidade`, utilizando o **CEP** como identificador único.

## 🚀 Funcionalidades (Menu Interativo)

Através de uma interface via console (App.java), o sistema permite:
1. **Adicionar Vértice:** Cadastra uma nova cidade (Nome, Estado, CEP).
2. **Adicionar Aresta:** Conecta duas cidades existentes informando o peso da conexão.
3. **Mostrar Grafo:** Renderiza no terminal a lista de adjacências e seus respectivos pesos.
4. **Persistência de Dados (CSV):** O repositório inclui arquivos `.csv` (cidades e arestas) que servem de base para testes e futuras importações automáticas.

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java (JDK 17+)
* **Estruturas de Dados:** `HashMap`, `LinkedList`.
* **Ambiente:** VS Code / NetBeans.

## 📂 Estrutura do Código

* `src/Cidade.java`: Modelo do vértice com validação por CEP.
* `src/Aresta.java`: Classe genérica que armazena destino e peso.
* `src/Grafo.java`: A "engine" do projeto, gerenciando o mapa de adjacências.
* `src/App.java`: Ponto de entrada (Main) com o menu de navegação.

## ⚙️ Como Executar

1. Clone o repositório:
   ```bash
   git clone [https://github.com/samueljunqueiraa/Grafos-Java.git](https://github.com/samueljunqueiraa/Grafos-Java.git)

2. Abra a pasta no VS Code ou NetBeans.
3. Certifique-se de ter o Java Extension Pack instalado.
4. Execute o arquivo App.java.

**Objetivo Acadêmico:** Este projeto consolida conhecimentos de Geometria Algorítmica e Teoria dos Grafos, preparando a base para estudos avançados em algoritmos de busca (BFS/DFS) e caminho mínimo (Dijkstra).

### Desenvolvido por Samuel Junqueira 
