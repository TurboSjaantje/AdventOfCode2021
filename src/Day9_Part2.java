import java.util.ArrayList;
import java.util.Collections;

public class Day9_Part2 {
    public static void main(String[] args) {
        long start = System.nanoTime();
        ArrayList<String> input = new importFileLines().getInput("Day9.txt");
        ArrayList<String> lowPoints = new ArrayList<>();
        ArrayList<Integer> basins = new ArrayList<>();
        int[][] map = new int[input.size()][input.get(0).length()];
        for (int row = 0; row < map.length; row++) {
            String s = input.get(row).strip();
            for (int col = 0; col < map[row].length; col++)
                map[row][col] = Integer.parseInt(s.substring(col, col + 1));
        }
        int sum = 0;
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                int curr = map[row][col];
                if (row - 1 >= 0 && curr >= map[row - 1][col]) continue;
                if (row + 1 < map.length && curr >= map[row + 1][col]) continue;
                if (col - 1 >= 0 && curr >= map[row][col - 1]) continue;
                if (col + 1 < map[row].length && curr >= map[row][col + 1]) continue;
                lowPoints.add(row + ";" + col);
                sum += curr + 1;
            }
        }
        for (String point : lowPoints) {
            ArrayList<String> pointsToExclude = new ArrayList<>();
            pointsToExclude.add(point);
            while (true) {
                boolean lastLoop = true;
                for (int i = 0; i < pointsToExclude.size(); i++) {
                    String point2 = pointsToExclude.get(i);
                    int row = Integer.parseInt(point2.split(";")[0]);
                    int col = Integer.parseInt(point2.split(";")[1]);
                    int curr = map[row][col];
                    //check if ones around him got one around that is higher too
                    if (row - 1 >= 0 && curr <= map[row - 1][col] && !pointsToExclude.contains((row - 1) + ";" + col) && map[row - 1][col] != 9) {
                        pointsToExclude.add((row - 1) + ";" + col);
                        lastLoop = false;
                    }
                    if (row + 1 < map.length && curr <= map[row + 1][col] && !pointsToExclude.contains((row + 1) + ";" + col) && map[row + 1][col] != 9) {
                        pointsToExclude.add((row + 1) + ";" + col);
                        lastLoop = false;
                    }
                    if (col - 1 >= 0 && curr <= map[row][col - 1] && !pointsToExclude.contains(row + ";" + (col - 1)) && map[row][col - 1] != 9) {
                        pointsToExclude.add(row + ";" + (col - 1));
                        lastLoop = false;
                    }
                    if (col + 1 < map[row].length && curr <= map[row][col + 1] && !pointsToExclude.contains(row + ";" + (col + 1)) && map[row][col + 1] != 9) {
                        pointsToExclude.add(row + ";" + (col + 1));
                        lastLoop = false;
                    }
                }
                if (lastLoop) {
                    break;
                }
            }
            basins.add(pointsToExclude.size());
        }
        basins.sort(Collections.reverseOrder());
        System.out.println("Part 1: " + sum + "\nPart 2: " + (basins.get(0) * basins.get(1) * basins.get(2)));
        long end = System.nanoTime();
        System.out.println("Time: " + ((end - start) / (double) 1000000));
    }
}