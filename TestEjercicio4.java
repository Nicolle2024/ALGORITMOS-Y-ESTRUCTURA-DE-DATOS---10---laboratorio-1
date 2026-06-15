package ejercicio4;

public class TestEjercicio4 {

    public static void main(String[] args) {

        GraphProperties<String> propiedades = new GraphProperties<>();

        GraphLink<String> grafo1 = new GraphLink<>();
        grafo1.insertVertex("A");
        grafo1.insertVertex("B");
        grafo1.insertVertex("C");
        grafo1.insertVertex("D");
        grafo1.insertEdge("A", "B");
        grafo1.insertEdge("B", "C");
        grafo1.insertEdge("C", "D");
        grafo1.insertEdge("D", "A");

        GraphLink<String> grafo2 = new GraphLink<>();
        grafo2.insertVertex("E");
        grafo2.insertVertex("F");
        grafo2.insertVertex("G");
        grafo2.insertVertex("H");
        grafo2.insertEdge("E", "F");
        grafo2.insertEdge("F", "G");
        grafo2.insertEdge("G", "H");
        grafo2.insertEdge("H", "E");

        GraphLink<String> grafo3 = new GraphLink<>();
        grafo3.insertVertex("I");
        grafo3.insertVertex("J");
        grafo3.insertVertex("K");
        grafo3.insertEdge("I", "J");
        grafo3.insertEdge("J", "K");

        GraphLink<String> grafo4 = new GraphLink<>();
        grafo4.insertVertex("L");
        grafo4.insertVertex("M");
        grafo4.insertVertex("N");
        grafo4.insertEdge("L", "M");

        System.out.println(" ISOMORFISMO ");
        System.out.println("\nGrafo 1 y Grafo 2:");
        System.out.println("Resultado: " + propiedades.isIsomorfo(grafo1, grafo2));
        System.out.println("\nGrafo 1 y Grafo 3:");
        System.out.println("Resultado: " + propiedades.isIsomorfo(grafo1, grafo3));

        System.out.println("\n PLANARIDAD ");
        System.out.println("\nGrafo 1:");
        System.out.println("Resultado: " + propiedades.isPlanar(grafo1));

        // Grafo completo dirigido K5 (20 aristas dirigidas)
        GraphLink<String> grafoCompleto = new GraphLink<>();
        for (int i = 1; i <= 5; i++)
            grafoCompleto.insertVertex("V" + i);
        for (int i = 1; i <= 5; i++)
            for (int j = 1; j <= 5; j++)
                if (i != j)
                    grafoCompleto.insertEdge("V" + i, "V" + j);

        System.out.println("\nGrafo completo K5:");
        System.out.println("Resultado: " + propiedades.isPlanar(grafoCompleto));

        System.out.println("\n CONECTIVIDAD ");
        System.out.println("\nGrafo 1:");
        System.out.println("Resultado: " + propiedades.isConexo(grafo1));
        System.out.println("\nGrafo 4:");
        System.out.println("Resultado: " + propiedades.isConexo(grafo4));

        System.out.println("\n AUTO-COMPLEMENTARIO");
        System.out.println("\nGrafo 1:");
        System.out.println("Resultado: " + propiedades.isAutoComplementario(grafo1));

        GraphLink<String> camino = new GraphLink<>();
        camino.insertVertex("A");
        camino.insertVertex("B");
        camino.insertVertex("C");
        camino.insertVertex("D");
        camino.insertEdge("A", "B");
        camino.insertEdge("B", "C");
        camino.insertEdge("C", "D");

        System.out.println("\nGrafo tipo camino:");
        System.out.println("Resultado: " + propiedades.isAutoComplementario(camino));
    }
}
