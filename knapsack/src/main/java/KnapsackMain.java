import model.Item;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class KnapsackMain {

    static Integer KNAPSACK_SIZE = 0;

    public static void main(String[] args) throws FileNotFoundException {

        KnapsackMain knapsackMain = new KnapsackMain();
        List<Item> items = knapsackMain.readInputFile();
        System.out.println(items.size());
        System.out.println(KNAPSACK_SIZE);

//        Item item1 = new Item(3,4);
//        Item item2 = new Item(2,3);
//        Item item3 = new Item(4,2);
//        Item item4 = new Item(4,3);
//        List<Item> itemsTest = Arrays.asList(item1, item2, item3, item4);
//        KNAPSACK_SIZE = 6;

        Integer optimalResult = knapsackMain.runKnapsackAlgorithm(items, KNAPSACK_SIZE);
        System.out.println(optimalResult);


        // problem1: 2493893
        // problem2:
    }

    private List<Item> readInputFile() throws FileNotFoundException {

        List<Item> items = new ArrayList<>();
        Scanner scanner = new Scanner(new File("src/main/resources/knapsack1.txt"));
        KNAPSACK_SIZE = Integer.parseInt(scanner.nextLine().split(" ")[0]);
        while (scanner.hasNext()) {
            String[] line = scanner.nextLine().split(" ");
            Item item = new Item(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
            items.add(item);
        }
        return items;
    }

    public Integer runKnapsackAlgorithm(List<Item> items, Integer totalWeight) {

        Integer lookup2DArray[][] = new Integer[items.size()+1][totalWeight+1];
        Arrays.fill(lookup2DArray[0], 0);
        for (int i = 1; i < items.size()+1; i++) {
            for (int x = 0; x < totalWeight+1; x++) {
                Item item = items.get(i - 1);
                Integer vi = item.value;
                Integer wi = item.size;
                if (wi > x) {
                    lookup2DArray[i][x] = lookup2DArray[i - 1][x];
                    continue;
                }
                lookup2DArray[i][x] = Math.max(lookup2DArray[i - 1][x], lookup2DArray[i - 1][x - wi] + vi);
            }
        }
        return lookup2DArray[items.size()][totalWeight];
    }

}
