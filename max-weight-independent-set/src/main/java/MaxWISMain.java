import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MaxWISMain {

    public static void main(String[] args) throws FileNotFoundException {

        MaxWISMain maxWISMain = new MaxWISMain();
        List<Integer> list = maxWISMain.readInputFile();
        long[] weightedArray = maxWISMain.weightedArray(list);
        System.out.println(Arrays.toString(weightedArray));
        System.out.println(weightedArray.length);
        Map<Integer, Integer> map = maxWISMain.reconstruction(weightedArray, list);
        System.out.println(map);
        // 10100110
    }

    public long[] weightedArray(List<Integer> pathGraphWeights) {

        long[] weightedArray = new long[pathGraphWeights.size()+1];
        weightedArray[0] = 0;
        weightedArray[1] = pathGraphWeights.get(0);
        for (int i = 2; i <= pathGraphWeights.size(); i++) {
            weightedArray[i] = Math.max(weightedArray[i - 1], weightedArray[i - 2] + pathGraphWeights.get(i - 1));
        }
        return weightedArray;
    }

    public Map<Integer,Integer> reconstruction(long[] weightedArray, List<Integer> pathGraphWeights) {

        Map<Integer,Integer> map = new HashMap<>();
        int i = weightedArray.length - 1;
        System.out.println(i);
        while (i > 0) {
            long weighted2 = 0;
            if (i - 2 > 0) {
                weighted2 = weightedArray[i - 2];
            }
            if (weightedArray[i - 1] >= (weighted2 + pathGraphWeights.get(i - 1))) {
                i--;
            } else {
                map.put(i, pathGraphWeights.get(i - 1));
                i -= 2;
            }
        }
        return map;
    }

    private List<Integer> readInputFile() throws FileNotFoundException {

        List<Integer> list = new ArrayList<>();
        Scanner scanner = new Scanner(new File("src/main/resources/mwis.txt"));
        scanner.nextLine();
        while (scanner.hasNext()) {
            list.add(scanner.nextInt());
        }
        return list;
    }


}
