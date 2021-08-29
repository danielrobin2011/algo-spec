import datastructure.Edge;
import datastructure.Graph;
import datastructure.Vertex;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FloydWarshallMain {

    public static void main(String[] args) throws FileNotFoundException {

        FloydWarshallMain floydWarshallMain = new FloydWarshallMain();
        Integer result = floydWarshallMain.findShortestShortestPath("g3");
        System.out.println("Result: "+result);

    }

    public Integer findShortestShortestPath(String inputFile) throws FileNotFoundException {

        Graph graph = readInputFile(inputFile);
        Integer[][] array3D = prep3DArray(graph);
        int n = graph.clusterSize;

        // Floyd-Warshall
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (array3D[i][k]==Integer.MAX_VALUE || array3D[k][j]==Integer.MAX_VALUE) {
                        continue;
                    }
                    array3D[i][j] = Math.min(array3D[i][j], array3D[i][k] + array3D[k][j]);
                }
            }
        }

        // get the minimum optimal solution for all i's and j's
        int minValue = array3D[0][0];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
//                System.out.println(array3D[j][i]);
                if ((i == j) && array3D[j][i] < 0) {
                    System.out.println("Negative edge cycle found!");
                    return null;
                }
                if (array3D[j][i] < minValue ) {
                    minValue = array3D[j][i];
                }
            }
        }

        return minValue;
    }

    public Integer[][] prep3DArray(Graph graph) {

        int n = graph.clusterSize;
        Integer[][] array3D = new Integer[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Edge edge = graph.getEdgeByLabels(i+1, j+1);
                if (i == j) {
                    array3D[i][j] = 0;
                } else if (edge != null) {
                    array3D[i][j] = edge.getWeight();
                } else {
                    array3D[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        return array3D;
    }

    public Graph readInputFile(String file) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("src/main/resources/" + file + ".txt"));
        int totalVertices = Integer.parseInt(scanner.nextLine().split(" ")[0]);
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

    public Vertex getVertex(Map<Integer, Vertex> map, int label) {
        if (!map.containsKey(label)) {
            Vertex vertex = new Vertex(label);
            map.put(label, vertex);
            return vertex;
        }
        return map.get(label);
    }

}
