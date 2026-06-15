package ejercicio3;

public class EdgeG<V, E> {

    private VertexG<V> destination;
    private E weight;

    public EdgeG(VertexG<V> destination, E weight) {
        this.destination = destination;
        this.weight = weight;
    }

    public VertexG<V> getDestination() {
        return destination;
    }

    public E getWeight() {
        return weight;
    }

    public void setWeight(E weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return destination + "(" + weight + ")";
    }
}
