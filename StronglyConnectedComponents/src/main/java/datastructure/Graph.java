package datastructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {

    private List<Node> nodes;

    public List<Node> getNodes() {
        return nodes;
    }

    public Graph() {
        nodes = new ArrayList<Node>();
    }

    private Map<Integer, Node> map = new HashMap<Integer, Node>();

    public void add(Node node) {
        map.put((Integer) node.value, node);
        nodes.add(node);
    }

    public Node find(Node node) {
        if (map.containsKey((Integer)node.value)) {
            return map.get((Integer)node.value);
        }
        return null;
    }

    public void printGraph() {
        for (Node node : this.nodes) {
            System.out.print(node.value + " : [ ");
            for (Node innerNode : node.adjacent) {
                System.out.print(innerNode.value + " ");
            }
            System.out.print(" ]");
            System.out.println();
        }
    }

}
