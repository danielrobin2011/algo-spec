import datastructure.Graph;
import datastructure.Node;
import model.Edge;
import util.GraphUtil;

import java.util.*;

public class KosarajusTwoPass {


    public static void main(String[] args) {

        KosarajusTwoPass kosarajusTwoPass = new KosarajusTwoPass();

        Edge edge = new Edge(1, 4);
        Edge edge1 = new Edge(4, 7);
        Edge edge2 = new Edge(7, 1);
        Edge edge3 = new Edge(9, 7);
        Edge edge4 = new Edge(9, 3);
        Edge edge5 = new Edge(3, 6);
        Edge edge6 = new Edge(6, 9);
        Edge edge7 = new Edge(8, 6);
        Edge edge8 = new Edge(2, 8);
        Edge edge9 = new Edge(5, 2);
        Edge edge10 = new Edge(8, 5);

        List<Edge> edgeList = new ArrayList<Edge>();
        edgeList.add(edge);
        edgeList.add(edge1);
        edgeList.add(edge2);
        edgeList.add(edge3);
        edgeList.add(edge4);
        edgeList.add(edge5);
        edgeList.add(edge6);
        edgeList.add(edge7);
        edgeList.add(edge8);
        edgeList.add(edge9);
        edgeList.add(edge10);

        Graph graph = GraphUtil.createGraphFromPayload(edgeList, false);
        Graph reversedGraph = GraphUtil.createGraphFromPayload(edgeList, true);



//        for (Node node : graph.nodes) {
//            System.out.print(node.value + " ");
//            for (Node innerNode : node.adjacent) {
//                System.out.print(innerNode.value + " ");
//            }
//            System.out.println();
//        }

        kosarajusTwoPass.sccTwoPass(graph, reversedGraph);

    }

    public void sccTwoPass(Graph graph, Graph reversedGraph) {

        // first pass
        Stack<Node> finishingTimeStack = firstPass(reversedGraph);

        // second pass

    }

    public Stack<Node> firstPass(Graph graph) {

        Stack<Node> finishingTimeStack = new Stack<Node>();
        for (Node node : graph.nodes) {
            if (!node.marked) {
                dfsFirstPass(node, node, finishingTimeStack);
            }
        }
        return finishingTimeStack;
    }

    public void dfsFirstPass(Node node, Node leader, Stack<Node> finishingTimeStack) {

        node.marked = true;
        node.leader = leader;
        for (Node child : node.adjacent) {
            if (!child.marked) {
                dfsFirstPass(child, leader, finishingTimeStack);
            }
        }
        finishingTimeStack.push(node);
    }

}
