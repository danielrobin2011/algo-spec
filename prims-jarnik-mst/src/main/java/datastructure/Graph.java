package datastructure;

import java.util.*;

public class Graph{

    private Map<String, Vertex> vertices; //collection of all verices

    public Graph() {
        vertices = new HashMap<>();
    }

    public Vertex findVertex(String label) {
        if (vertices.containsKey(label)) {
            return vertices.get(label);
        }
        return new Vertex(label);
    }

    public List<Vertex> getVertices() {
        return new ArrayList<>(vertices.values());
    }

    public void addVertex(Vertex vertex){
        vertices.put(vertex.getLabel(), vertex);
    }
}
