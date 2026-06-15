package ejercicio4;

import java.util.List;

public interface Graph<E> {
    void insertVertex(E data);
    void insertEdge(E origin, E destination);
    void removeVertex(E data);
    void removeEdge(E origin, E destination);
    Vertex<E> searchVertex(E data);
    boolean searchEdge(E origin, E destination);
    List<Vertex<E>> adjacentVertices(E data);
    boolean isEmpty();
    int vertexCount();
    int edgeCount();
}
