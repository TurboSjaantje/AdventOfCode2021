import java.util.ArrayList;

public class Day13_Part1 {
    public static void main(String[] args) {
        ArrayList<String> input = new importFileLines().getInput("Day13.txt");
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

        for (String instruction1 : instructions) {

            StringBuilder builder = new StringBuilder(instruction1);
            builder.delete(0, 11);
            String instruction = builder.toString();

            if (instruction.split("=")[0].equals("y")) {
                int index = Integer.parseInt(instruction.split("=")[1]);
                int[][] newGrid = new int[index][paper[0].length];
                for (int i = 0; i < paper.length; i++) {
                    for (int j = 0; j < paper[i].length; j++) {
                        if (i < index) {
                            if (paper[i][j] == 1) newGrid[i][j] = paper[i][j];
                        } else {
                            if (paper[i][j] == 1) newGrid[(paper.length - 1) - i][j] = paper[i][j];
                        }
                    }
                }
                paper = newGrid;
            } else {
                int index = Integer.parseInt(instruction.split("=")[1]);
                int[][] newGrid = new int[paper.length][index];
                for (int i = 0; i < paper.length; i++) {
                    for (int j = 0; j < paper[i].length; j++) {
                        if (j < index) {
                            if (paper[i][j] == 1) newGrid[i][j] = paper[i][j];
                        } else {
                            if (paper[i][j] == 1) newGrid[i][(paper[0].length - 1) - j] = paper[i][j];
                        }
                    }
                }
                paper = newGrid;
            }
        }
        print2dArray(paper);
    }

    public static void print2dArray(int[][] paper) {
        for (int i = 0; i < paper.length; i++) {
            for (int j = 0; j < paper[i].length; j++) {
                if (paper[i][j] == 1) {
                    System.out.print("[#]");
                } else {
                    System.out.print("   ");
                }
            }
            System.out.println();
        }
    }

    public static int getScore(int[][] paper) {
        int count = 0;

        for (int i = 0; i < paper.length; i++) {
            for (int j = 0; j < paper[i].length; j++) {
                if (paper[i][j] == 1) {
                    count++;
                }
            }
        }

        return count;
    }
}
