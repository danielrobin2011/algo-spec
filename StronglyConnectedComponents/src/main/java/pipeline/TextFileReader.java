package pipeline;

import model.Edge;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TextFileReader {

    public static List<Edge> readSmallTextFile(String fileName) throws FileNotFoundException {

        List<Edge> edges = new ArrayList<Edge>();
        Scanner in = new Scanner(new FileReader(fileName));
        while (in.hasNextLine()) {
            String line = in.nextLine();
            String[] nodes = line.split(" ");
            Edge edge = new Edge(Integer.parseInt(nodes[0]), Integer.parseInt(nodes[1]));
            edges.add(edge);
        }
        return edges;
    }

}
