package graph;

public class Edge<E> {

    private Vertex<E> destination;
    private int weight;

    public Edge(Vertex<E> destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }

    public Vertex<E> getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return destination.getData() + "(" + weight + ")";
    }
}
