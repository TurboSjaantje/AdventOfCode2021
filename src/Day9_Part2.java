import java.util.ArrayList;

public class Day9_Part2 {
    public static void main(String[] args) {

        ArrayList<String> input = new importFileLines().getInput("test.txt");
        ArrayList<int[]> numberLines = new ArrayList<>();

        for (String line : input) {
            String[] numberStrs = line.split("");
            int[] numbers = new int[numberStrs.length];
            for (int i = 0; i < numberStrs.length; i++) {
                numbers[i] = Integer.parseInt(numberStrs[i]);
            }
            numberLines.add(numbers);
        }

    }

}
