import model.Item;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class KnapsackBigMain {

    static Integer KNAPSACK_SIZE = 0;

    public static void main(String[] args) throws FileNotFoundException {

        KnapsackBigMain knapsackBigMain = new KnapsackBigMain();
        List<Item> items = knapsackBigMain.readInputFile();
        Integer[] weights = items.stream().map(o -> o.getSize().intValue()).toArray(Integer[]::new);
        Integer[] values = items.stream().map(o -> o.getSize().intValue()).toArray(Integer[]::new);
        int[] dp = new int[KNAPSACK_SIZE+1];
        Arrays.fill(dp, -1);
        Integer result = knapsackBigMain.knapsack(weights, values, KNAPSACK_SIZE, items.size());
        System.out.println(result);
    }

    private List<Item> readInputFile() throws FileNotFoundException {

        List<Item> items = new ArrayList<>();
        Scanner scanner = new Scanner(new File("src/main/resources/knapsack_big.txt"));
        KNAPSACK_SIZE = Integer.parseInt(scanner.nextLine().split(" ")[0]);
        while (scanner.hasNext()) {
            String[] line = scanner.nextLine().split(" ");
            Item item = new Item(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
            items.add(item);
        }
        return items;
    }

    private static int knapsack(Integer[] weight, Integer[] val, int w, int itemNum) {
        if (w == 0 || itemNum == weight.length) {
            return 0;
        }
        if (weight[itemNum] > w)
            return knapsack(weight, val, w, itemNum + 1);

        int rMax = val[itemNum] + knapsack(weight, val, w - weight[itemNum], itemNum + 1);
        int lMax = knapsack(weight, val, w, itemNum + 1);
        return Math.max(rMax, lMax);
    }

}
