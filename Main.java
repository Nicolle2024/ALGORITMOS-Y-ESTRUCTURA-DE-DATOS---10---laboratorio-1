package ejercicio3;

public class Main {

    public static void main(String[] args) {

        GraphLink<String, Integer> g = new GraphLink<>();

        g.insertVertex("Arequipa");
        g.insertVertex("Cusco");
        g.insertVertex("Puno");
        g.insertVertex("Moquegua");
        g.insertVertex("Tacna");

        g.insertEdge("Arequipa", "Cusco", 510);
        g.insertEdge("Arequipa", "Moquegua", 230);
        g.insertEdge("Moquegua", "Tacna", 160);
        g.insertEdge("Cusco", "Puno", 390);
        g.insertEdge("Puno", "Tacna", 420);

        System.out.println("Conexión de ciudades:");
        System.out.println(g);

        System.out.println("Buscar ciudad Cusco: " + g.searchVertex("Cusco"));
        System.out.println("¿Existe carretera entre Arequipa y Moquegua?: " +
                           g.searchEdge("Arequipa", "Moquegua"));
        System.out.println("Ciudades conectadas con Puno: " + g.adjacentVertices("Puno"));
        System.out.println("Cantidad de ciudades registradas: " + g.vertexCount());

        g.removeEdge("Cusco", "Puno");
        System.out.println("\nDespués de eliminar la carretera entre Cusco y Puno:");
        System.out.println(g);

        g.removeVertex("Moquegua");
        System.out.println("Después de eliminar la ciudad Moquegua:");
        System.out.println(g);
    }
}
