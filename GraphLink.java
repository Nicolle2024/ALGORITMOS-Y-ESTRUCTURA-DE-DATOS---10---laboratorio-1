package graph;

import listlinked.ListLinked;
import java.util.ArrayList;
import java.util.Stack;

public class GraphLink<E> {

    private ListLinked<AdjList<E>> graph;

    public GraphLink() {
        graph = new ListLinked<>();
    }

    // Busca un vértice dentro del grafo
    private AdjList<E> findVertex(E data) {
        for (int i = 0; i < graph.size(); i++) {
            AdjList<E> adj = graph.get(i);
            if (adj.getVertex().getData().equals(data))
                return adj;
        }
        return null;
    }

    // Inserta un nuevo vértice si no existe
    public void insertVertex(E data) {
        if (findVertex(data) != null)
            return;
        Vertex<E> vertex = new Vertex<>(data);
        graph.addLast(new AdjList<>(vertex));
    }

    // Inserta una arista sin peso (peso por defecto = 1)
    public void insertEdge(E origin, E destination) {
        insertEdgeWeight(origin, destination, 1);
    }

    // Inserta una arista con peso en ambos sentidos (grafo no dirigido)
    public void insertEdgeWeight(E origin, E destination, int weight) {
        AdjList<E> v1 = findVertex(origin);
        AdjList<E> v2 = findVertex(destination);
        if (v1 == null || v2 == null)
            return;
        v1.getEdges().addLast(new Edge<>(v2.getVertex(), weight));
        v2.getEdges().addLast(new Edge<>(v1.getVertex(), weight));
    }

    // Elimina un vértice y todas sus conexiones
    public void removeVertex(E data) {
        for (int i = 0; i < graph.size(); i++) {
            ListLinked<Edge<E>> edges = graph.get(i).getEdges();
            for (int j = 0; j < edges.size(); j++) {
                if (edges.get(j).getDestination().getData().equals(data)) {
                    edges.remove(edges.get(j));
                    break;
                }
            }
        }
        AdjList<E> remove = findVertex(data);
        if (remove != null)
            graph.remove(remove);
    }

    // Elimina una arista en ambos sentidos
    public void removeEdge(E origin, E destination) {
        removeDirectedEdge(origin, destination);
        removeDirectedEdge(destination, origin);
    }

    // Elimina una arista en una sola dirección
    private void removeDirectedEdge(E from, E to) {
        AdjList<E> adj = findVertex(from);
        if (adj == null)
            return;
        ListLinked<Edge<E>> edges = adj.getEdges();
        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).getDestination().getData().equals(to)) {
                edges.remove(edges.get(i));
                return;
            }
        }
    }

    // Busca un vértice
    public Vertex<E> searchVertex(E data) {
        AdjList<E> adj = findVertex(data);
        return (adj != null) ? adj.getVertex() : null;
    }

    // Busca una arista
    public boolean searchEdge(E origin, E destination) {
        AdjList<E> adj = findVertex(origin);
        if (adj == null)
            return false;
        ListLinked<Edge<E>> edges = adj.getEdges();
        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).getDestination().getData().equals(destination))
                return true;
        }
        return false;
    }

    // Devuelve los vértices adyacentes
    public ArrayList<Vertex<E>> adjacentVertices(E data) {
        ArrayList<Vertex<E>> result = new ArrayList<>();
        AdjList<E> adj = findVertex(data);
        if (adj == null)
            return result;
        ListLinked<Edge<E>> edges = adj.getEdges();
        for (int i = 0; i < edges.size(); i++) {
            result.add(edges.get(i).getDestination());
        }
        return result;
    }

    // Obtiene el índice de un vértice
    private int indexOf(E data) {
        for (int i = 0; i < graph.size(); i++) {
            if (graph.get(i).getVertex().getData().equals(data))
                return i;
        }
        return -1;
    }

    // Verifica si el grafo es conexo usando DFS
    public boolean isConexo() {
        if (graph.isEmpty())
            return true;
        int n = graph.size();
        boolean[] visited = new boolean[n];
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        visited[0] = true;
        int count = 1;
        while (!stack.isEmpty()) {
            int idx = stack.pop();
            ListLinked<Edge<E>> edges = graph.get(idx).getEdges();
            for (int j = 0; j < edges.size(); j++) {
                int ni = indexOf(edges.get(j).getDestination().getData());
                if (ni != -1 && !visited[ni]) {
                    visited[ni] = true;
                    count++;
                    stack.push(ni);
                }
            }
        }
        return count == n;
    }

    // Calcula la ruta más corta usando Dijkstra, retorna ArrayList
    public ArrayList<E> shortPath(E origin, E destination) {
        int n = graph.size();
        int[] dist = new int[n];
        int[] prev = new int[n];
        boolean[] vis = new boolean[n];
        for (int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
            prev[i] = -1;
        }
        int start = indexOf(origin);
        int end = indexOf(destination);
        if (start == -1 || end == -1)
            return new ArrayList<>();
        dist[start] = 0;
        for (int iter = 0; iter < n; iter++) {
            int u = -1;
            for (int i = 0; i < n; i++) {
                if (!vis[i] && (u == -1 || dist[i] < dist[u]))
                    u = i;
            }
            if (u == -1 || dist[u] == Integer.MAX_VALUE)
                break;
            vis[u] = true;
            ListLinked<Edge<E>> edges = graph.get(u).getEdges();
            for (int j = 0; j < edges.size(); j++) {
                int v = indexOf(edges.get(j).getDestination().getData());
                int newDist = dist[u] + edges.get(j).getWeight();
                if (v != -1 && !vis[v] && newDist < dist[v]) {
                    dist[v] = newDist;
                    prev[v] = u;
                }
            }
        }
        ArrayList<E> path = new ArrayList<>();
        if (dist[end] == Integer.MAX_VALUE)
            return path;
        for (int at = end; at != -1; at = prev[at]) {
            path.add(0, graph.get(at).getVertex().getData());
        }
        return path;
    }

    
    public Stack<E> dijkstra(E origin, E destination) {
        ArrayList<E> path = shortPath(origin, destination);
        Stack<E> stack = new Stack<>();
        // Se agrega en orden inverso para que el tope sea el origen
        for (int i = path.size() - 1; i >= 0; i--) {
            stack.push(path.get(i));
        }
        return stack;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < graph.size(); i++) {
            AdjList<E> adj = graph.get(i);
            sb.append("Vértice ").append(adj.getVertex().getData()).append(" conectado con: ");
            ListLinked<Edge<E>> edges = adj.getEdges();
            for (int j = 0; j < edges.size(); j++) {
                sb.append(edges.get(j).getDestination().getData());
                if (j < edges.size() - 1)
                    sb.append(", ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
