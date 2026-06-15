package graph;

import java.util.ArrayList;
import java.util.Stack;

public class TestEjercicio1 {

    public static void main(String[] args) {

        GraphLink<Integer> g = new GraphLink<>();

        g.insertVertex(1);
        g.insertVertex(2);
        g.insertVertex(3);
        g.insertVertex(4);
        g.insertVertex(5);

        g.insertEdgeWeight(1, 2, 10);
        g.insertEdgeWeight(1, 3, 5);
        g.insertEdgeWeight(2, 4, 1);
        g.insertEdgeWeight(3, 2, 3);
        g.insertEdgeWeight(3, 4, 9);
        g.insertEdgeWeight(3, 5, 2);
        g.insertEdgeWeight(4, 5, 4);

        System.out.println("Grafo con pesos");
        System.out.println(g);
        System.out.println("¿El grafo es conexo?: " + g.isConexo());

        ArrayList<Integer> ruta = g.shortPath(1, 5);
        System.out.println("Ruta más corta de 1 a 5: " + ruta);

        // Se usa pop() directamente: el stack tiene origen en el tope
        Stack<Integer> stack = g.dijkstra(1, 4);
        System.out.print("Ruta más corta de 1 a 4 usando Dijkstra: ");
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
        System.out.println();

        // Grafo no conexo
        GraphLink<String> gNC = new GraphLink<>();
        gNC.insertVertex("X");
        gNC.insertVertex("Y");
        gNC.insertVertex("Z");
        gNC.insertEdgeWeight("X", "Y", 3);

        System.out.println("\nGrafo no conexo");
        System.out.println("¿El grafo es conexo?: " + gNC.isConexo());
        System.out.println("Ruta más corta de X a Z: " + gNC.shortPath("X", "Z"));
    }
}
