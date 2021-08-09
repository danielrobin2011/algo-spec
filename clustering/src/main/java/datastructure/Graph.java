package datastructure;

import java.util.*;

public class Graph {

    private Map<String, Vertex> vertices = new HashMap<>();
    private PriorityQueue<Edge> edges;
    public int clusterSize;

    public Graph(int clusterSize) {
        this.clusterSize = clusterSize;
        this.edges = new PriorityQueue<Edge>(Comparator.comparing(Edge::getWeight));
    }

    public void addEdge(Edge edge) {
        Vertex from = findVertex(edge.from.label);
        Vertex to = findVertex(edge.to.label);
        edge = new Edge(from, to, edge.weight);
        this.edges.add(edge);
    }

    public List<Edge> getSortedEdges() {

        List<Edge> edges = new ArrayList<>();
        while (!this.edges.isEmpty()) {
            edges.add(this.edges.poll());
        }
        return edges;
    }

    public void union(Edge edge) {

        Vertex fromParent = find(edge.from);
        Vertex toParent = find(edge.to);
        if (fromParent.equals(toParent)) {
            return;
        }

        this.clusterSize--;
        edge.to.parent = edge.from;
    }

    public Vertex find(Vertex vertex) {
        if(vertex.parent == null) {
            return vertex;
        }
        return find(vertex.parent);
    }

    public Vertex findVertex(int label) {
        if (vertices.containsKey(label)) {
            return vertices.get(label);
        }
        return new Vertex(label);
    }

}
