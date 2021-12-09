import java.util.ArrayList;

public class test {
    public static void main(String[] args) {

        ArrayList<String> input = new importFileLines().getInput("test.txt");

        int[][] map = new int[input.size()][input.get(0).length()];

        for (int row = 0; row < map.length; row++) {
            String s = input.get(row).strip();
            for (int col = 0; col < map[row].length; col++)
                map[row][col] = Integer.parseInt(s.substring(col, col + 1));
        }

        long sum = 0;
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                int curr = map[row][col];
                if (row - 1 >= 0 && curr >= map[row - 1][col])
                    continue;
                if (row + 1 < map.length && curr >= map[row + 1][col])
                    continue;
                if (col - 1 >= 0 && curr >= map[row][col - 1])
                    continue;
                if (col + 1 < map[row].length && curr >= map[row][col + 1])
                    continue;

                sum += curr + 1;
            }
        }

        System.out.println(sum);
    }
}
