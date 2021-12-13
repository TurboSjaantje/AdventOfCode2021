import java.util.ArrayList;

public class Day11_Part1 {

    public static void main(String[] args) {
        long start = System.nanoTime();
        ArrayList<String> input = new importFileLines().getInput("Day11.txt");
        ArrayList<Integer> seperateValues = new ArrayList<>();
        for (String line : input) {
            for (String character : line.split("")) {
                seperateValues.add(Integer.valueOf(character));
            }
        }

        long flashes = 0;
        int[][] octos = new int[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int value = seperateValues.get((i * 10) + j);
                octos[i][j] = value;
            }
        }

        for (int i = 0; i < 999; i++) {

            int count = 0;

            for (int row = 0; row < 10; row++) {
                for (int col = 0; col < 10; col++) {
                    if (octos[row][col] == -1) {
                        octos[row][col] = 1;
                    } else {
                        octos[row][col]++;
                    }
                }
            }

            while (true) {

                boolean thereIsA10 = false;
                for (int row = 0; row < 10; row++) {
                    for (int col = 0; col < 10; col++) {
                        if (octos[row][col] > 9) {
                            flashes++;
                            count++;
                            octos[row][col] = -1;
                            if (row - 1 >= 0 && octos[row-1][col] != -1) octos[row - 1][col]++;
                            if (row - 1 >= 0 && col + 1 < 10 && octos[row - 1][col + 1] != -1) octos[row - 1][col + 1]++;
                            if (col + 1 < 10 && octos[row][col + 1] != -1) octos[row][col + 1]++;
                            if (row + 1 < 10 && col + 1 < 10 && octos[row + 1][col + 1] != -1) octos[row + 1][col + 1]++;
                            if (row + 1 < 10 && octos[row + 1][col] != -1) octos[row + 1][col]++;
                            if (row + 1 < 10 && col - 1 >= 0 && octos[row + 1][col - 1] != -1) octos[row + 1][col - 1]++;
                            if (col - 1 >= 0 && octos[row][col - 1] != -1) octos[row][col - 1]++;
                            if (row - 1 >= 0 && col - 1 >= 0 && octos[row - 1][col - 1] != -1) octos[row - 1][col - 1]++;
                            thereIsA10 = true;
                        }
                    }
                }

                if (!thereIsA10) {
                    break;
                }

            }

            if (count == 100) {
                System.out.println(i + 1);
                break;
            }

            for (int row = 0; row < 10; row++) {
                for (int col = 0; col < 10; col++) {
                    if (octos[row][col] == -1) {
                        octos[row][col] = 0;
                    }
                }
            }

        }

        System.out.println("Flashes: " + flashes);
        long end = System.nanoTime();
        System.out.println("\nTime: " + (end - start) / (double) 1000000);
    }

}
