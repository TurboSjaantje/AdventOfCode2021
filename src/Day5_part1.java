import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Day5_part1 {
    public static void main(String[] args) {

        ArrayList<String> input = getInput("Day5.txt");
        ArrayList<Line> lines = addInputToList(input);
        HashMap<String,Integer> points = new HashMap<>();

        for (Line line : lines) {
            if (line.isLineHOrV()) {
                for (String point : line.allPointsHorOrVer()) {
                    if (points.keySet().contains(point) == false) {
                        points.put(point, 1);
                    } else {
                        points.replace(point, points.get(point) + 1);
                    }
                }
            } else if(line.isLineDia()) {
                for (String point : line.allPointsDia()) {
                    if (points.keySet().contains(point) == false) {
                        points.put(point, 1);
                    } else {
                        points.replace(point, points.get(point) + 1);
                    }
                }
            }
        }

        int counter = 0;
        for (String point : points.keySet()) {
            if (points.get(point) > 1) {
                counter++;
            }
        }

        System.out.println("Answer: " + counter);

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

    public static ArrayList<Line> addInputToList(ArrayList<String> input) {
        ArrayList<Line> tempList = new ArrayList<>();

        for (String set : input) {
            String[] Coords = set.split(" -> ");
            String[] beginCoords = Coords[0].split(",");
            String[] endCoords = Coords[1].split(",");

            tempList.add(new Line(Integer.valueOf(beginCoords[0]), Integer.valueOf(beginCoords[1]),
                    Integer.valueOf(endCoords[0]), Integer.valueOf(endCoords[1])));
        }

        return tempList;
    }
}