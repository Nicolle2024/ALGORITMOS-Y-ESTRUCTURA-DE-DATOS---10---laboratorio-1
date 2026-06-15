package ejercicio4;

import java.util.ArrayList;

public class GraphListEdge<E> {

    private ArrayList<Vertex<E>> vertices;
    private ArrayList<EdgeDirected<E>> edges;

    public GraphListEdge() {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
    }

    // Clase interna que representa una arista dirigida
    public static class EdgeDirected<E> {

        private Vertex<E> origen;
        private Vertex<E> destino;

        public EdgeDirected(Vertex<E> origen, Vertex<E> destino) {
            this.origen = origen;
            this.destino = destino;
        }

        public Vertex<E> getOrigen() {
            return origen;
        }

        public Vertex<E> getDestino() {
            return destino;
        }

        @Override
        public String toString() {
            return origen.getData() + " -> " + destino.getData();
        }
    }

    public void insertVertex(E data) {
        if (searchVertex(data) != null)
            return;
        vertices.add(new Vertex<>(data));
    }

    // Inserta una arista dirigida
    public void insertEdge(E origin, E destination) {
        Vertex<E> origen = searchVertex(origin);
        Vertex<E> destino = searchVertex(destination);
        if (origen == null || destino == null)
            return;
        edges.add(new EdgeDirected<>(origen, destino));
    }

    public Vertex<E> searchVertex(E data) {
        for (Vertex<E> vertex : vertices) {
            if (vertex.getData().equals(data))
                return vertex;
        }
        return null;
    }

    public int vertexCount() {
        return vertices.size();
    }

    public int edgeCount() {
        return edges.size();
    }

    public ArrayList<Vertex<E>> getVertices() {
        return vertices;
    }

    public ArrayList<EdgeDirected<E>> getEdges() {
        return edges;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Lista de vértices:\n");
        for (Vertex<E> v : vertices)
            sb.append("  ").append(v.getData()).append("\n");
        sb.append("\nLista de conexiones:\n");
        for (EdgeDirected<E> e : edges)
            sb.append("  ").append(e).append("\n");
        return sb.toString();
    }
}
