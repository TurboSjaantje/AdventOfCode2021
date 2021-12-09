import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Day2_Part1 {
    public static void main(String[] args) {

        ArrayList<String> input = getInput("Day2.txt");
        int x = 0;
        int depth = 0;
        int aim = 0;

        for (String line : input) {
            if (line.contains("forward")) {
                x += Integer.valueOf(line.substring(line.length()-1,line.length()));
                depth += (aim * Integer.valueOf(line.substring(line.length()-1,line.length())));
            } else if (line.contains("down")) {
                aim += Integer.valueOf(line.substring(line.length()-1,line.length()));
            } else {
                aim -= Integer.valueOf(line.substring(line.length()-1,line.length()));
            }
        }

        System.out.println("The depth is: " + depth + "\nThe horizontal position is: " + x);
        System.out.println("Multiplied this is: " + (depth * x));
    }

    public static ArrayList<String> getInput(String file) {
        ArrayList<String> tempList = new ArrayList<>();
        try (Scanner scanner = new Scanner(Paths.get(file))) {
            while (scanner.hasNextLine()) {
                tempList.add(scanner.nextLine());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return tempList;
    }

}
