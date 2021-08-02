import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import datastructure.Graph;
import datastructure.Node;
import model.Edge;
import pipeline.TextFileReader;
import util.GraphUtil;

import javax.xml.bind.SchemaOutputResolver;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

public class KosarajusTwoPass {


    public static void main(String[] args) throws IOException {

        KosarajusTwoPass kosarajusTwoPass = new KosarajusTwoPass();
        System.out.println("started java process...");

        List<Edge> edgeList = TextFileReader.readSmallTextFile("F:/Practice/graph-algorithms/StronglyConnectedComponents/src/main/resources/SCC.txt");

//        ObjectMapper objectMapper = new ObjectMapper();
//        String payload = "[{\"source\": 1, \"sink\": 2}]";
//        String json = "";
//        if (args != null && args.length > 0) {
//            payload = args[0];
//            json = kosarajusTwoPass.decompressPayload(payload);
////            System.out.println(json);
//        }
//
//        List<Edge> edgeList = null;
//        try {
//            edgeList = objectMapper.readValue(json, new TypeReference<List<Edge>>(){});
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        System.out.println(edgeList);




//        Edge edge = new Edge(1, 4);
//        Edge edge1 = new Edge(4, 7);
//        Edge edge2 = new Edge(7, 1);
//        Edge edge3 = new Edge(9, 7);
//        Edge edge4 = new Edge(9, 3);
//        Edge edge5 = new Edge(3, 6);
//        Edge edge6 = new Edge(6, 9);
//        Edge edge7 = new Edge(8, 6);
//        Edge edge8 = new Edge(2, 8);
//        Edge edge9 = new Edge(5, 2);
//        Edge edge10 = new Edge(8, 5);
//        Edge edge11 = new Edge(5, 10);
//        Edge edge12 = new Edge(10, 2);
//        Edge edge13 = new Edge(10, 5);
//
//        List<Edge> edgeList = new ArrayList<Edge>();
//        edgeList.add(edge);
//        edgeList.add(edge1);
//        edgeList.add(edge2);
//        edgeList.add(edge3);
//        edgeList.add(edge4);
//        edgeList.add(edge5);
//        edgeList.add(edge6);
//        edgeList.add(edge7);
//        edgeList.add(edge8);
//        edgeList.add(edge9);
//        edgeList.add(edge10);
//        edgeList.add(edge11);
//        edgeList.add(edge12);
//        edgeList.add(edge13);

        Graph graph = GraphUtil.createGraphFromPayload(edgeList, false);
        Graph reversedGraph = GraphUtil.createGraphFromPayload(edgeList, true);

        kosarajusTwoPass.sccTwoPass(graph, reversedGraph);
    }

    public void sccTwoPass(Graph graph, Graph reversedGraph) {

        // first pass
        Stack<Node> finishingTimeStack = firstPass(graph);

        // second pass
        List<Integer> sccSizes = secondPass(reversedGraph, finishingTimeStack);
        Collections.sort(sccSizes, Collections.<Integer>reverseOrder());

        System.out.println(sccSizes.stream().limit(5).collect(Collectors.toList()));
    }

    public Stack<Node> firstPass(Graph graph) {

        Stack<Node> finishingTimeStack = new Stack<Node>();
        for (Node node : graph.getNodes()) {
            if (!node.marked) {
                dfsFirstPass(node, node, finishingTimeStack);
            }
        }
        return finishingTimeStack;
    }

    public List<Integer> secondPass(Graph graph, Stack<Node> finishingTimeStack) {

        List<Integer> sccSizes = new ArrayList<Integer>();
        Set<Node> explored = new HashSet<Node>();
        while (!finishingTimeStack.empty()) {
            Node node = finishingTimeStack.pop();
            Node actualNode = graph.find(node);
            if (!explored.contains(actualNode)) {
                int sccSize = dfsSecondPass(actualNode, explored, 0);
                sccSizes.add(sccSize);
            }
        }
        return sccSizes;
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

    public int dfsSecondPass(Node node, Set<Node> explored, int count) {

        explored.add(node);
        count++;
        for (Node child : node.adjacent) {
            if (!explored.contains(child)) {
                return dfsSecondPass(child, explored, count);
            }
        }
        return count;
    }

    @SuppressWarnings("Since15")
    public String decompressPayload(String compressedString) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DeflaterOutputStream dos = new DeflaterOutputStream(baos);
        dos.write(compressedString.getBytes());
        dos.flush();
        dos.close();

        // at this moment baos.toByteArray() holds the compressed data of "Hello World!"

        // will decompress compressed "Hello World!"
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        InflaterInputStream iis = new InflaterInputStream(bais);

        String result = "";
        byte[] buf = new byte[5];
        int rlen = -1;
        while ((rlen = iis.read(buf)) != -1) {
            result += new String(Arrays.copyOf(buf, rlen));
        }
        return result;
    }

}
