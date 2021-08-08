import datastructure.Edge;
import datastructure.Graph;
import datastructure.Vertex;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MstMain {

    public static void main(String[] args) throws FileNotFoundException {
        MstMain mstMain = new MstMain();
        Graph graph = mstMain.readInputFile();
        long cost = mstMain.findTotalCostMstPrims(graph);
        System.out.println(cost);
    }

    public long findTotalCostMstPrims(Graph graph) {

        long cost = 0;

        Set<Vertex> x = new HashSet<>();
        x.add(graph.getVertices().get(0));
        while (x.size() != graph.getVertices().size()) {
            List<Edge> edges = new ArrayList<>();
            for (Vertex vertex : x) {
                for (Edge edge : vertex.getEdges()) {
                    if (!x.contains(edge.getTo())) {
                        edges.add(edge);
                    }
                }
            }
            Edge edge = edges.stream().min(Comparator.comparing(Edge::getWeight)).orElseThrow(NoSuchElementException::new);
            x.add(edge.getTo());
            cost += edge.getWeight();
        }


//        for (Vertex vertex : graph.getVertices()) {
////            if (vertex.getEdges().isEmpty()) {
////                continue;
////            }
////            Edge minEdge = vertex.getEdges().stream().min(Comparator.comparing(Edge::getWeight)).orElseThrow(NoSuchElementException::new);
////            cost += minEdge.getWeight();
////        }



        return cost;
    }

    public Graph readInputFile() throws FileNotFoundException {

        Graph graph = new Graph();
        Scanner scanner = new Scanner(new File("src/main/resources/prim_edges.txt"));
        scanner.nextLine();
        while (scanner.hasNext()) {
            String nextLine = scanner.nextLine();
            String[] values = nextLine.split(" ");
            Vertex vertex1 = graph.findVertex(values[0]);
            Vertex vertex2 = graph.findVertex(values[1]);
            int weight = Integer.parseInt(values[2]);
            graph.addVertex(vertex1);
            graph.addVertex(vertex2);
            vertex1.addEdge(new Edge(vertex2, weight));
            vertex2.addEdge(new Edge(vertex1, weight));
        }
        return graph;
    }



}
