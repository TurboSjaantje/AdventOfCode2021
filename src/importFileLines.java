import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class importFileLines {

    public ArrayList<String> getInput(String file) {
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
