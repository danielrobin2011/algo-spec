package datastructure;

import java.util.*;

public class Graph {

    private Map<String, Vertex> vertices = new HashMap<>();
    private List<Edge> edges;
    public int clusterSize;
    private Map<String, Edge> edgeMap = new HashMap<>();

    public Graph(int clusterSize) {
        this.clusterSize = clusterSize;
        this.edges = new ArrayList<>();
    }

    public void addEdge(Edge edge) {
        Vertex from = findVertex(edge.from.label);
        Vertex to = findVertex(edge.to.label);
        edge = new Edge(from, to, edge.weight);
        this.edges.add(edge);
        this.edgeMap.put(edge.from.label + "-" + edge.to.label, edge);
    }

    public Edge getEdgeByLabels(int from, int to) {
        return this.edgeMap.get(from + "-" + to);
    }

//    public List<Edge> getSortedEdges() {
//
//        List<Edge> edges = new ArrayList<>();
//        while (!this.edges.isEmpty()) {
//            edges.add(this.edges.poll());
//        }
//        return edges;
//    }

//    public void union(Vertex from, Vertex to) {
//
////        Vertex fromParent = find(edge.from);
////        Vertex toParent = find(edge.to);
//
//        if (find(from) == null && find(to) == null) {
//            to.parent = from;
//            from.parent = from;
//        }
//
//        if (find(from).equals(find(to))) {
//            return;
//        }
//
//        this.clusterSize--;
//        to.parent = find(from);
//    }
//
//    public Vertex find(Vertex vertex) {
//        if(vertex.parent == null) {
//            return null;
//        }
//        return find(vertex.parent);
//    }

    public Vertex findVertex(int label) {
        if (vertices.containsKey(label)) {
            return vertices.get(label);
        }
        return new Vertex(label);
    }

}
