import datastructure.Edge;
import datastructure.Graph;
import datastructure.Vertex;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ClusteringMain {

    final int CLUSTER_SIZE = 4;

    public static void main(String[] args) throws FileNotFoundException {

//        Graph graph = new Graph(6);
//
//        Vertex vertex1 = new Vertex(1);
//        Vertex vertex2 = new Vertex(2);
//        Vertex vertex3 = new Vertex(3);
//        Vertex vertex4 = new Vertex(4);
//        Vertex vertex5 = new Vertex(5);
//        Vertex vertex6 = new Vertex(6);
//
//        Edge edge1 = new Edge(vertex1, vertex2, 5);
//        Edge edge2 = new Edge(vertex2, vertex3, 2);
//        Edge edge3 = new Edge(vertex3, vertex4, 1);
//        Edge edge4 = new Edge(vertex4, vertex5, 3);
//        Edge edge5 = new Edge(vertex5, vertex6, 4);
//
//        graph.addEdge(edge1);
//        graph.addEdge(edge2);
//        graph.addEdge(edge3);
//        graph.addEdge(edge4);
//        graph.addEdge(edge5);

//        graph.union(edge1);
//        graph.union(edge2);
//        graph.union(edge3);

//        System.out.println(graph.find(edge3.to).label);
//        System.out.println(graph.clusterSize);

        ClusteringMain clusteringMain = new ClusteringMain();
        Graph graph = clusteringMain.readInputFile();
        int maxSpacing = clusteringMain.maxSpacing(graph);
        System.out.println(maxSpacing);
    }

    public Graph readInputFile() throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("src/main/resources/clustering1.txt"));
        int totalVertices = Integer.parseInt(scanner.nextLine());
        Graph graph = new Graph(totalVertices);
        Map<Integer, Vertex> mapVertices = new HashMap<>();
        while (scanner.hasNext()) {
            String nextLine = scanner.nextLine();
            String[] values = nextLine.split(" ");
            Vertex vertex1 = getVertex(mapVertices, Integer.parseInt(values[0]));
            Vertex vertex2 = getVertex(mapVertices, Integer.parseInt(values[1]));
            int weight = Integer.parseInt(values[2]);
            Edge edge = new Edge(vertex1, vertex2, weight);
            graph.addEdge(edge);
        }
        return graph;
    }

    public int maxSpacing(Graph graph) {

        List<Edge> sortedEdges = graph.getSortedEdges();
        Edge lastIteratedEdge = null;
        for (Edge edge : sortedEdges) {
            if (graph.clusterSize >= CLUSTER_SIZE) {
                if (graph.find(edge.to).equals(graph.find(edge.from))) {
                    continue;
                }
                System.out.println(graph.clusterSize);
                graph.union(edge);
                lastIteratedEdge = edge;
            } else {
                break;
            }
        }
        return lastIteratedEdge.getWeight();
    }

    public Vertex getVertex(Map<Integer, Vertex> map, int label) {
        if (!map.containsKey(label)) {
            Vertex vertex = new Vertex(label);
            map.put(label, vertex);
            return vertex;
        }
        return map.get(label);
    }

}
