package datastructure;

public class Vertex {

    public int label;
    public Vertex parent;

    public Vertex(int label){
        this.label = label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertex vertex = (Vertex) o;

        return label == vertex.label;
    }

    @Override
    public int hashCode() {
        return label;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "label=" + label +
                ", parent=" + parent +
                '}';
    }
}
