import java.nio.file.Paths;
import java.util.*;

public class Day1_Part1 {
    public static void main(String[] args) {
        List<String> input = new importFileLines().getInput("Day1.txt");
        List<Integer> sums = new ArrayList<>();

        int counter = 0;
        int counter2 = 0;
        int lastVal = 0;
        int lastSum = 0;
        for (int i = 0; i < input.size(); i++) {
            if (i < input.size() - 2) {
                sums.add(Integer.valueOf(input.get(i)) + Integer.valueOf(input.get(i + 1)) + Integer.valueOf(input.get(i + 2)));
            }
            if (Integer.valueOf(input.get(i)) > lastVal) {
                counter++;
            }
            lastVal = Integer.valueOf(input.get(i));
        }
        for (Integer sum : sums) {
            if (sum > lastSum) {
                counter2++;
            }
            lastSum = sum;
        }
        System.out.println((counter - 1) + " ; " + (counter2 - 1));
    }

}
