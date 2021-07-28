package model;

public class Edge {

    private Integer source;
    private Integer sink;

    public Edge(Integer source, Integer sink) {
        this.source = source;
        this.sink = sink;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer getSink() {
        return sink;
    }

    public void setSink(Integer sink) {
        this.sink = sink;
    }
}
