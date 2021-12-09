import java.util.ArrayList;

public class Day9_Part2 {
    public static void main(String[] args) {
        ArrayList<String> input = new importFileLines().getInput("test.txt");
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

        System.out.println(sum);

        for (String point : lowPoints) {

            ArrayList<String> pointsToExclude = new ArrayList<>();
            pointsToExclude.add(point);

            while (true) {

                boolean lastLoop = true;

                for (int i = 0; i < pointsToExclude.size(); i++) {
                    String point2 = pointsToExclude.get(i);
                    int row = Integer.parseInt(point2.split(";")[0]);
                    int col = Integer.parseInt(point2.split(";")[0]);
                    int curr = map[row][col];

                    //check if ones around him got one around that is higher too
                    if (row - 1 >= 0 && curr <= map[row - 1][col] && pointsToExclude.contains((row - 1) + ";" + col) == false) {
                        String thisPoint = (row - 1) + ";" + col;
                        String answer = checkPoint(map, thisPoint, pointsToExclude);
                        if (answer.split(",")[1].equals("true")) {
                            if (pointsToExclude.contains(answer.split(",")[0]) == false) {
                                pointsToExclude.add(answer.split(",")[0]);
                                lastLoop = false;
                            }
                        }
                    }

                    if (row + 1 < map.length && curr <= map[row + 1][col] && pointsToExclude.contains((row + 1) + ";" + col) == false) {
                        String thisPoint = (row + 1) + ";" + col;
                        String answer = checkPoint(map, thisPoint, pointsToExclude);
                        if (answer.split(",")[1].equals("true")) {
                            if (pointsToExclude.contains(answer.split(",")[0]) == false) {
                                pointsToExclude.add(answer.split(",")[0]);
                                lastLoop = false;
                            }
                        }
                    }

                    if (col - 1 >= 0 && curr <= map[row][col - 1] && pointsToExclude.contains(row + ";" + (col - 1)) == false) {
                        String thisPoint = row + ";" + (col - 1);
                        String answer = checkPoint(map, thisPoint, pointsToExclude);
                        if (answer.split(",")[1].equals("true")) {
                            if (pointsToExclude.contains(answer.split(",")[0]) == false) {
                                pointsToExclude.add(answer.split(",")[0]);
                                lastLoop = false;
                            }
                        }
                    }

                    if (col + 1 < map[row].length && curr <= map[row][col + 1] && pointsToExclude.contains(row + ";" + (col + 1)) == false) {
                        String thisPoint = row + ";" + (col + 1);
                        String answer = checkPoint(map, thisPoint, pointsToExclude);
                        if (answer.split(",")[1].equals("true")) {
                            if (pointsToExclude.contains(answer.split(",")[0]) == false) {
                                pointsToExclude.add(answer.split(",")[0]);
                                lastLoop = false;
                            }
                        }
                    }

                }

                if (lastLoop == true) {
                    break;
                }

            }

            basins.add(pointsToExclude.size());

        }

        System.out.println(basins);

    }

    public static String checkPoint(int[][] map, String point, ArrayList<String> pointsToExlude1) {
        boolean bool = false;

        ArrayList<String> pointsToExclude = new ArrayList<>(pointsToExlude1);
        int row = Integer.parseInt(point.split(";")[0]);
        int col = Integer.parseInt(point.split(";")[1]);
        int curr = map[row][col];

        if (row - 1 >= 0 && curr <= map[row - 1][col] && pointsToExclude.contains((row - 1) + ";" + col) == false) {
            bool = true;
        }
        if (row + 1 < map.length && curr >= map[row + 1][col] && pointsToExclude.contains((row + 1) + ";" + col) == false) {
            bool = true;
        }
        if (col - 1 >= 0 && curr >= map[row][col - 1] && pointsToExclude.contains(row + ";" + (col - 1)) == false) {
            bool = true;
        }
        if (col + 1 < map[row].length && curr >= map[row][col + 1] && pointsToExclude.contains(row + ";" + (col + 1)) == false) {
            bool = true;
        }

        if (true) {
            return point + ",true";
        } else {
            return point + ",false";
        }
    }
}