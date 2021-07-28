package datastructure;

import java.util.ArrayList;
import java.util.List;

public class Node {

    public boolean marked;
    public Object value;
    public List<Node> adjacent;
    State state;
    public Node leader;

    public Node(Object o) {
        this.value = o;
        this.adjacent = new ArrayList<Node>();
    }

    @Override
    public String toString(){
        return String.valueOf(this.value);
    }

}
