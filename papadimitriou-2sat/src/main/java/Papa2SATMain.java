import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Papa2SATMain {

    public static Map<Integer, Boolean> variableMap = new HashMap<>();

    public static void main(String[] args) throws FileNotFoundException {

        Papa2SATMain papa2SATMain = new Papa2SATMain();
        List<Pair<Integer, Integer>> expressionPairs = papa2SATMain.readInputFile();

        Integer expressionSize = expressionPairs.size();
        System.out.println("Number of expressions: "+expressionSize);

        Integer variableSize = variableMap.size();
        System.out.println("Variable size: " + variableSize);

        long outerLoopCount = (long) Math.floor(Math.log(variableSize)/Math.log(2));
        System.out.println("Outer loop count: " + outerLoopCount);
        long innerLoopCount =  2 * (long) Math.pow(variableSize, 2);
        System.out.println("Inner loop count: " + innerLoopCount);

        outerLoop:
        for (int i = 0; i < outerLoopCount; i++) {
            System.out.println("Iteration #"+(i+1));
            // randomize initial assignment
            randomizeVariableMap();
            for (int j = 0; j < innerLoopCount; j++) {
                System.out.print("\rInner loop index: "+j);
                Pair<Integer, Integer> resultPair = papa2SATMain.evaluateExpression(expressionPairs, variableMap);
                if (resultPair == null) {
                    System.out.println("Satisfiable!");
                    break outerLoop;
                } else {
                    if (j % 2 == 0) {
                        variableMap.put(Math.abs(resultPair.getKey()), !variableMap.get(Math.abs(resultPair.getKey())));
                    } else {
                        variableMap.put(Math.abs(resultPair.getValue()), !variableMap.get(Math.abs(resultPair.getValue())));
                    }
                }
            }
            if (i == outerLoopCount - 1)
                System.out.println("Not satisfiable.");
        }
    }

    public List<Pair<Integer, Integer>> readInputFile() throws FileNotFoundException {

        List<Pair<Integer, Integer>> expressionPairs = new ArrayList<>();
        Scanner scanner = new Scanner(new File("src/main/resources/2sat2.txt"));
        scanner.nextLine();

        Map<Integer, ArrayList<Integer>> reductionIndexMap = new HashMap<>();

        int i = 0;
        while (scanner.hasNext()) {
            String[] expressionArray = scanner.nextLine().split(" ");
            Integer left = Integer.parseInt(expressionArray[0]);
            variableMap.put(Math.abs(left), Boolean.TRUE);
            Integer right = Integer.parseInt(expressionArray[1]);
            variableMap.put(Math.abs(right), Boolean.TRUE);
            Pair<Integer, Integer> expressionPair = new Pair<>(left, right);
            expressionPairs.add(expressionPair);
            i++;
        }



        return expressionPairs;
    }

    /*
    If this method returns an expression, then the evaluation has failed,
    else if it returns null then it has passed
     */
    public Pair<Integer, Integer> evaluateExpression(List<Pair<Integer, Integer>> expressionPairs, Map<Integer, Boolean> variableMap) {

        for (Pair<Integer, Integer> expression : expressionPairs) {
            Integer leftVariable = expression.getKey();
            Boolean left = leftVariable < 0 ? !variableMap.get(Math.abs(leftVariable)) : variableMap.get(Math.abs(leftVariable));
            Integer rightVariable = expression.getValue();
            Boolean right = rightVariable < 0 ? !variableMap.get(Math.abs(rightVariable)) : variableMap.get(Math.abs(rightVariable));
            Boolean result = left || right;
            if (!result) {
                return expression;
            }
        }
        return null;
    }

    public static void randomizeVariableMap() {

        int randomNum = ThreadLocalRandom.current().nextInt(2, 5);
//        System.out.println("--------------------------");
//        System.out.println("Before: "+variableMap);
//        System.out.println("Random number: "+randomNum);
        int i = 1;
        for (Map.Entry<Integer, Boolean> entry : variableMap.entrySet()) {
            if (i % randomNum == 0) {
                variableMap.put(entry.getKey(), !entry.getValue());
            }
            i++;
        }
//        System.out.println("After: "+variableMap);
    }

//    public List<Pair<Integer, Integer>> booleanReduction(List<Pair<Integer, Integer>> actualPairs) {
//
//    }

}
