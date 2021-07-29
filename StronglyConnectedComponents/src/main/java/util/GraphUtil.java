package util;

import datastructure.Graph;
import datastructure.Node;
import model.Edge;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphUtil {

    public static Graph createGraphFromPayload(List<Edge> edgeList, boolean isReversed) {

        Graph graph = new Graph();
        Map<Integer, Node> nodeMap = new HashMap<Integer, Node>();

        if (edgeList != null) {
            for(Edge edge : edgeList) {
                Integer source = isReversed ? edge.getSink() : edge.getSource();
                Integer sink = isReversed ? edge.getSource() : edge.getSink();
                if (source.equals(sink)) {
                    continue;
                }
                Node sourceNode;
                Node sinkNode;
                // check if source present in Map
                if (!nodeMap.containsKey(source)) {
                    sourceNode = new Node(source);
                    nodeMap.put(source, sourceNode);
                    graph.add(sourceNode);
                } else {
                    sourceNode = nodeMap.get(source);
                }
                // check if sink present in Map
                if (!nodeMap.containsKey(sink)) {
                    sinkNode = new Node(sink);
                    nodeMap.put(sink, sinkNode);
                    graph.add(sinkNode);
                } else {
                    sinkNode = nodeMap.get(sink);
                }
                sourceNode.adjacent.add(sinkNode);
            }
        }
        return graph;
    }

}
