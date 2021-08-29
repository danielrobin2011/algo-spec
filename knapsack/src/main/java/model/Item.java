package model;

public class Item {

    public Item(Integer value, Integer size) {
        this.value = value;
        this.size = size;
    }

    public Integer value;
    public Integer size;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Item{" +
                "value=" + value +
                ", size=" + size +
                '}';
    }
}
