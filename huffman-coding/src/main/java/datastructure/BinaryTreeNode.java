package datastructure;

public class BinaryTreeNode implements TreePrinter.PrintableNode{

    public Integer value;
    public BinaryTreeNode left;
    public BinaryTreeNode right;

    public BinaryTreeNode() {

    }

    public BinaryTreeNode(Integer value) {
        this.value = value;
    }

    @Override
    public TreePrinter.PrintableNode getLeft() {
        return left;
    }

    @Override
    public TreePrinter.PrintableNode getRight() {
        return right;
    }

    @Override
    public String getText() {
        return String.valueOf(value);
    }
}
