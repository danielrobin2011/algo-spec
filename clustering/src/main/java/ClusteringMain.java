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

        ClusteringMain clusteringMain = new ClusteringMain();
        Graph graph = clusteringMain.readInputFile();
        int maxSpacing = clusteringMain.maxSpacing(graph);
        System.out.println(maxSpacing); // 106
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
        int i = 0;
        for (Edge edge : sortedEdges) {
            if (graph.disjointSet.getNumberOfDisjointSets() >= CLUSTER_SIZE - 1) {

                if (graph.disjointSet.find_set(edge.to.label) == (graph.disjointSet.find_set(edge.from.label))) {
                    continue;
                }
                graph.disjointSet.union(edge.from.label, edge.to.label);
                if (graph.disjointSet.getNumberOfDisjointSets() == CLUSTER_SIZE - 1) {
                    lastIteratedEdge = edge;
                }
            } else {
                break;
            }
        }
        sortedEdges.forEach(System.out::println);

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
