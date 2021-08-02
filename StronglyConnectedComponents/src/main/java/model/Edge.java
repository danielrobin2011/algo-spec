package model;

public class Edge {

    private Integer source;
    private Integer sink;

    public Edge() {
    }

    public Edge(Integer source, Integer sink) {
        this.source = source;
        this.sink = sink;
    }

    public Integer getSource() {
        return source;
    }

    public Integer getSink() {
        return sink;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "source=" + source +
                ", sink=" + sink +
                '}';
    }
}
