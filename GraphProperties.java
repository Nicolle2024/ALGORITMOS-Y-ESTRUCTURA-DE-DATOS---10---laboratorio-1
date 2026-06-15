package ejercicio4;

import java.util.Arrays;

public class GraphProperties<E> {

    // Verifica si dos grafos son potencialmente isomorfos
    // comparando cantidad de vértices, aristas y secuencia de grados
    public boolean isIsomorfo(GraphLink<E> g1, GraphLink<E> g2) {
        int vertices1 = g1.vertexCount();
        int vertices2 = g2.vertexCount();
        int aristas1  = g1.edgeCount();
        int aristas2  = g2.edgeCount();

        System.out.println("\nComparando grafos:");
        System.out.println("Grafo 1: " + vertices1 + " vértices y " + aristas1 + " aristas");
        System.out.println("Grafo 2: " + vertices2 + " vértices y " + aristas2 + " aristas");

        if (vertices1 != vertices2) {
            System.out.println("Los grafos tienen diferente cantidad de vértices.");
            return false;
        }
        if (aristas1 != aristas2) {
            System.out.println("Los grafos tienen diferente cantidad de aristas.");
            return false;
        }

        int[] seq1 = degreeSequence(g1);
        int[] seq2 = degreeSequence(g2);

        System.out.println("Grados Grafo 1: " + Arrays.toString(seq1));
        System.out.println("Grados Grafo 2: " + Arrays.toString(seq2));

        if (!Arrays.equals(seq1, seq2)) {
            System.out.println("Los grafos tienen diferente secuencia de grados.");
            return false;
        }

        System.out.println("Los grafos tienen la misma cantidad de vértices, aristas y grados.");
        return true;
    }

    // Calcula la secuencia de grados de cada vértice
    private int[] degreeSequence(GraphLink<E> g) {
        int n = g.vertexCount();
        int[] seq = new int[n];
        for (int i = 0; i < n; i++) {
            int grado = 0;
            for (int j = 0; j < n; j++) {
                if (g.hasEdge(i, j)) grado++; // aristas de salida
                if (g.hasEdge(j, i)) grado++; // aristas de entrada
            }
            seq[i] = grado;
        }
        Arrays.sort(seq);
        return seq;
    }

    // Verifica si el grafo es plano 
    public boolean isPlanar(GraphLink<E> g) {
        int vertices = g.vertexCount();
        int aristas  = g.edgeCount();
        int limite   = 3 * vertices - 6;

        System.out.println("\nVerificando si el grafo es plano:");
        System.out.println("Vértices: " + vertices + " | Aristas: " + aristas +
                           " | Límite (3v-6): " + limite);

        if (vertices < 3) {
            System.out.println("El grafo es plano (menos de 3 vértices).");
            return true;
        }
        if (aristas > limite) {
            System.out.println("El grafo no cumple e <= 3v-6, por lo tanto no es plano.");
            return false;
        }
        System.out.println("El grafo cumple la condición de Euler, puede ser plano.");
        return true;
    }

   
    public boolean isConexo(GraphLink<E> g) {
        int n = g.vertexCount();
        if (n == 0)
            return true;

        System.out.println("\nVerificando conectividad:");

        int[][] matriz = buildMatrix(g);

        boolean[] vis1 = new boolean[n];
        dfs(matriz, 0, vis1, n);
        int count1 = 0;
        for (boolean v : vis1) if (v) count1++;

        if (count1 != n) {
            System.out.println("No todos los vértices son alcanzables desde el vértice 0.");
            return false;
        }

        int[][] trans = transpose(matriz, n);
        boolean[] vis2 = new boolean[n];
        dfs(trans, 0, vis2, n);
        int count2 = 0;
        for (boolean v : vis2) if (v) count2++;

        if (count2 != n) {
            System.out.println("El grafo no es fuertemente conexo (falla en el transpuesto).");
            return false;
        }

        System.out.println("Todos los vértices están conectados: el grafo es fuertemente conexo.");
        return true;
    }

    // Recorrido DFS recursivo sobre una matriz de adyacencia
    private void dfs(int[][] matriz, int vertice, boolean[] visitados, int n) {
        visitados[vertice] = true;
        for (int i = 0; i < n; i++) {
            if (matriz[vertice][i] == 1 && !visitados[i])
                dfs(matriz, i, visitados, n);
        }
    }

    // Construye la matriz de adyacencia del grafo
    private int[][] buildMatrix(GraphLink<E> g) {
        int n = g.vertexCount();
        int[][] m = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (g.hasEdge(i, j)) m[i][j] = 1;
        return m;
    }

    // Retorna la transpuesta de una matriz de adyacencia
    private int[][] transpose(int[][] m, int n) {
        int[][] t = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                t[j][i] = m[i][j];
        return t;
    }

    // Verifica si el grafo es auto-complementario:
    // construye el complemento y comprueba si es isomorfo al original
    public boolean isAutoComplementario(GraphLink<E> g) {
        System.out.println("\nGenerando complemento del grafo...");
        int n = g.vertexCount();
        int[][] m = buildMatrix(g);
        int[][] comp = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (i != j) comp[i][j] = (m[i][j] == 0) ? 1 : 0;

        GraphLink<E> complemento = buildFromMatrix(g, comp, n);
        boolean resultado = isIsomorfo(g, complemento);

        System.out.println(resultado
                ? "El grafo es auto-complementario."
                : "El grafo no es auto-complementario.");
        return resultado;
    }

    // Construye un nuevo GraphLink a partir de una matriz de adyacencia
    private GraphLink<E> buildFromMatrix(GraphLink<E> original, int[][] m, int n) {
        GraphLink<E> nuevo = new GraphLink<>();
        for (int i = 0; i < n; i++)
            nuevo.insertVertex(original.getVertex(i).getData());
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (m[i][j] == 1)
                    nuevo.insertEdge(
                        original.getVertex(i).getData(),
                        original.getVertex(j).getData()
                    );
        return nuevo;
    }
}
