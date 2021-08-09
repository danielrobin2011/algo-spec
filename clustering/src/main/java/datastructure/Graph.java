package datastructure;

import java.util.*;

public class Graph {

    private Map<String, Vertex> vertices = new HashMap<>();
    private PriorityQueue<Edge> edges;
    public int clusterSize;
    public DisjointSet disjointSet;

    public Graph(int clusterSize) {
        this.clusterSize = clusterSize;
        this.edges = new PriorityQueue<Edge>(Comparator.comparing(Edge::getWeight));
        this.disjointSet = new DisjointSet();
        for (int i = 1; i <= clusterSize ; i++) {
            disjointSet.create_set(i);
        }
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
