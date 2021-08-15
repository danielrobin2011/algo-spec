import datastructure.BinaryTreeNode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class HuffmanMain {

    List<String> codes = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {

        HuffmanMain huffmanMain = new HuffmanMain();
        Queue<BinaryTreeNode> minHeap = huffmanMain.readInputFile();
        BinaryTreeNode tree = huffmanMain.createHuffmanTree(minHeap);
        StringBuilder stringBuilder = new StringBuilder();
        huffmanMain.postOrderTraversal(tree, stringBuilder);
        System.out.println(huffmanMain.codes.size());

        Optional<String> max = huffmanMain.codes.stream().max(Comparator.comparing(String::length));
        Optional<String> min = huffmanMain.codes.stream().min(Comparator.comparing(String::length));
        System.out.println(max.get().length() + ", " + min.get().length()); // 19, 9

    }

    public BinaryTreeNode createHuffmanTree(Queue<BinaryTreeNode> minHeap) {

        if (minHeap.size() == 2) {
            BinaryTreeNode leftMin = minHeap.poll();
            BinaryTreeNode rightMin = minHeap.poll();
            return merge(leftMin, rightMin);
        }

        BinaryTreeNode leftMin = minHeap.poll();
        BinaryTreeNode rightMin = minHeap.poll();
        BinaryTreeNode mergedNode = merge(leftMin, rightMin);
        minHeap.add(mergedNode);
        return createHuffmanTree(minHeap);
    }

    private Queue<BinaryTreeNode> readInputFile() throws FileNotFoundException {

        Queue<BinaryTreeNode> minHeap = new PriorityQueue<BinaryTreeNode>(Comparator.comparing(x -> x.value));
        Scanner scanner = new Scanner(new File("src/main/resources/huffman.txt"));
        scanner.nextLine();
        while (scanner.hasNext()) {
            Integer nodeWeight = scanner.nextInt();
            BinaryTreeNode binaryTreeNode = new BinaryTreeNode(nodeWeight);
            minHeap.add(binaryTreeNode);
        }
        return minHeap;
    }

    private BinaryTreeNode merge(BinaryTreeNode left, BinaryTreeNode right) {

        BinaryTreeNode binaryTreeNode = new BinaryTreeNode();
        binaryTreeNode.left = left;
        binaryTreeNode.right = right;
        binaryTreeNode.value = left.value + right.value;
        return binaryTreeNode;
    }

    public void postOrderTraversal(BinaryTreeNode node, StringBuilder stringBuilder) {

        if (node == null) {
            return;
        }

        // left
        stringBuilder.append("0");
        postOrderTraversal(node.left, stringBuilder);
        stringBuilder.deleteCharAt(stringBuilder.length()-1);

        // right
        stringBuilder.append("1");
        postOrderTraversal(node.right, stringBuilder);
        stringBuilder.deleteCharAt(stringBuilder.length()-1);

        if (node.left == null && node.right == null) {
            codes.add(stringBuilder.toString());
        }
    }

    int countLeaves(BinaryTreeNode node){
        if( node == null )
            return 0;
        if( node.left == null && node.right == null ) {
            return 1;
        } else {
            return countLeaves(node.left) + countLeaves(node.right);
        }
    }

}
