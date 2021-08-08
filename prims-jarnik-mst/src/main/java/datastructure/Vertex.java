package datastructure;

import java.util.*;

public class Vertex {

    private String label;
    private Set<Edge> edges; //collection of edges to neighbors

    public Vertex(String pageObject) {
        this.label = pageObject;
        edges = new HashSet<>();
    }

    public String getLabel() {
        return label;
    }

    public boolean addEdge(Edge edge){
        return edges.add(edge);
    }

    public List<Edge> getEdges() {
        return new ArrayList<>(edges);
    }

    public Set<Edge> getEdgesAsSet() {
        return edges;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return Objects.equals(label, vertex.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label);
    }
}
