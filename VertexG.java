package ejercicio3;

public class VertexG<V> {

    private V data;

    public VertexG(V data) {
        this.data = data;
    }

    public V getData() {
        return data;
    }

    public void setData(V data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof VertexG))
            return false;
        return data.equals(((VertexG<?>) obj).data);
    }

    @Override
    public int hashCode() {
        return data.hashCode();
    }
}
