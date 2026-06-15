package ejercicio2;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import java.util.List;

public class CityNetwork {

    private Graph<String, DefaultWeightedEdge> grafoCiudades;

    public CityNetwork() {
        grafoCiudades = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
    }

    // Agrega una ciudad al grafo
    public void addCity(String ciudad) {
        grafoCiudades.addVertex(ciudad);
        System.out.println("Se agregó la ciudad: " + ciudad);
    }

    // Agrega una carretera entre dos ciudades con distancia en km
    public void addRoad(String ciudadOrigen, String ciudadDestino, double km) {
        if (!grafoCiudades.containsVertex(ciudadOrigen) ||
            !grafoCiudades.containsVertex(ciudadDestino)) {
            System.out.println("No se pudo agregar la carretera: ciudad no encontrada.");
            return;
        }
        DefaultWeightedEdge carretera = grafoCiudades.addEdge(ciudadOrigen, ciudadDestino);
        if (carretera != null) {
            grafoCiudades.setEdgeWeight(carretera, km);
            System.out.println("Carretera agregada entre " + ciudadOrigen +
                               " y " + ciudadDestino + " (" + km + " km)");
        }
    }

    // Muestra las ciudades registradas
    public void showCities() {
        System.out.println("\nLista de ciudades:");
        for (String ciudad : grafoCiudades.vertexSet()) {
            System.out.println("  - " + ciudad);
        }
    }

    // Muestra las carreteras registradas
    public void showRoads() {
        System.out.println("\nLista de carreteras:");
        for (DefaultWeightedEdge carretera : grafoCiudades.edgeSet()) {
            System.out.println("  " + grafoCiudades.getEdgeSource(carretera) +
                               " <--> " + grafoCiudades.getEdgeTarget(carretera) +
                               " = " + grafoCiudades.getEdgeWeight(carretera) + " km");
        }
    }

    // Calcula la ruta más corta entre dos ciudades usando Dijkstra
    public void shortestPath(String ciudadOrigen, String ciudadDestino) {
        System.out.println("\nRuta más corta de " + ciudadOrigen + " a " + ciudadDestino + ":");

        DijkstraShortestPath<String, DefaultWeightedEdge> rutaCorta =
                new DijkstraShortestPath<>(grafoCiudades);

        GraphPath<String, DefaultWeightedEdge> ruta =
                rutaCorta.getPath(ciudadOrigen, ciudadDestino);

        if (ruta == null) {
            System.out.println("  No existe una ruta disponible.");
            return;
        }

        List<String> ciudadesRuta = ruta.getVertexList();
        System.out.print("  Recorrido: ");
        for (int i = 0; i < ciudadesRuta.size(); i++) {
            System.out.print(ciudadesRuta.get(i));
            if (i < ciudadesRuta.size() - 1)
                System.out.print(" -> ");
        }
        System.out.println("\n  Distancia total: " + ruta.getWeight() + " km");
    }
}
