import java.util.ArrayList;

public class Day13_Part1 {
    public static void main(String[] args) {

        ArrayList<String> input = new importFileLines().getInput("test.txt");
        ArrayList<String> instructions = new ArrayList<>();
        int maxX = 0;
        int maxY = 0;

        for (String line : input) {

            if (line.isEmpty() || line.isBlank()) continue;

            if (!(line.contains("fold"))) {
                if (Integer.parseInt(line.split(",")[0]) > maxX) maxX = Integer.parseInt(line.split(",")[0]);
                if (Integer.parseInt(line.split(",")[1]) > maxY) maxY = Integer.parseInt(line.split(",")[1]);
            } else {
                instructions.add(line);
            }

        }

        int[][] paper = new int[maxY + 1][maxX + 1];

        for (String line : input) {
            if (line.isEmpty() || line.isBlank()) continue;
            if (!line.contains("fold")) {
                paper[Integer.parseInt(line.split(",")[1])][Integer.parseInt(line.split(",")[0])] = 1;
            }
        }

        print2dArray(paper);

        for (String line : instructions) {

        }

    }

    public static void print2dArray(int[][] paper) {
        for (int i = 0; i < paper.length - 1; i++) {
            for (int j = 0; j < paper[i].length - 1; j++) {
                System.out.print(paper[i][j]);
            }
            System.out.println();
        }
    }
}
