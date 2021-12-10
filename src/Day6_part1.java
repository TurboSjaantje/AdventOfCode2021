import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Day6_part1 {
    public static void main(String[] args) {
        long start = System.nanoTime();
        int counter = 0;
        ArrayList<String> input = getInput("Day6.txt");
        HashMap<Integer, Double> fish = new HashMap<>();
        fish.put(0, 0.0);
        fish.put(1, 0.0);
        fish.put(2, 0.0);
        fish.put(3, 0.0);
        fish.put(4, 0.0);
        fish.put(5, 0.0);
        fish.put(6, 0.0);
        fish.put(7, 0.0);
        fish.put(8, 0.0);
        for (String string : input) {
            fish.replace(Integer.valueOf(string), fish.get(Integer.valueOf(string)) + 1);
        }
        for (int i = 0; i < 256; i++) {
            double amountOf0 = fish.get(0);
            for (int j = 1; j < 9; j++) {
                double amount = fish.get(j);
                fish.replace(j - 1, amount);
                fish.replace(j, 0.0);
            }
            fish.replace(6, fish.get(6) + amountOf0);
            fish.replace(8, amountOf0);
            counter = 0;
            for (int item : fish.keySet()) {
                counter += fish.get(item);
            }
        }
        long answer = 0;
        for (Integer item : fish.keySet()) {
            answer += fish.get(item);
        }
        System.out.println("answer: " + answer);
        long end = System.nanoTime();
        System.out.println("Time: " + ((end - start) / (double) 1000000));
    }

    public static ArrayList<String> getInput(String file) {
        ArrayList<String> tempList = new ArrayList<>();
        try (Scanner scanner = new Scanner(Paths.get(file))) {
            while (scanner.hasNextLine()) {
                String[] numbers = scanner.nextLine().split(",");
                tempList.addAll(Arrays.asList(numbers));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return tempList;
    }
}
