import java.nio.file.Paths;
import java.util.*;

public class Day4_Part1 {
    public static void main(String[] args) {
        List<String> input = getInput("Day4.txt");
        List<int[][]> boards = getBoards(input);
        String[] randNumbers = input.get(0).split(",");
        for (String strNum : randNumbers) {
            int lastNum = Integer.valueOf(strNum);
            checkNumbers(lastNum, boards);
            for (int i = 0; i < boards.size(); i++) {
                if (hasBordWon(boards.get(i)) == true) {
                    System.out.println(getScore(boards.get(i), lastNum));
                    boards.remove(i);
                    System.out.println("\b " + boards.size() + "\n");
                }
            }
        }
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

    public static List<int[][]> getBoards(List<String> input) {
        List<int[][]> tempList = new ArrayList<>();
        // put board in to boards
        int startIndex = 2;
        int currentIndex = startIndex;
        for (int i = 0; i < 5; i++) {
            currentIndex = startIndex + i;
            while (currentIndex < input.size()) {
                int[][] board;
                if (i == 0) {
                    board = new int[5][5];
                    tempList.add(board);
                } else {
                    board = tempList.get((currentIndex - i - startIndex) / 6);
                }
                String line = input.get(currentIndex);
                for (int j = 0; j < 5; j++) {
                    int num = Integer.parseInt(line.substring(j * 3, j * 3 + 2).strip());
                    board[i][j] = num;
                }
                currentIndex += 6;
            }
        }
        return tempList;
    }

    public static void printBoards(List<int[][]> boards) {
        for (int[][] board : boards) {
            for (int row = 0; row < 5; row++) {
                for (int col = 0; col < 5; col++) {
                    System.out.print(board[row][col] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public static void checkNumbers(int lastNum, List<int[][]> boards) {
        for (int[][] board : boards) {
            for (int row = 0; row < board.length; row++) {
                for (int col = 0; col < board[row].length; col++) {
                    if (board[row][col] == lastNum)
                        board[row][col] = -1;
                }
            }
        }
    }

    public static String getScore(int[][] board, int lastNum) {
        long score = 0;
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                if (board[row][col] != -1) {
                    score += board[row][col];
                }
            }
        }
        return "Sum of remaining numbers: " + score + "\n Last number: " + lastNum + "\n Calculated score: "
                + (score * lastNum) + "\n";
    }

    public static boolean hasBordWon(int[][] board) {
        boolean boardHasWon = true;
        // Check for horizontal win
        for (int row = 0; row < 5; row++) {
            boardHasWon = true;
            for (int col = 0; col < 5; col++) {
                if (board[row][col] != -1) {
                    boardHasWon = false;
                }
            }
            if (boardHasWon == true) {
                break;
            }
        }
        if (boardHasWon == true) {
            return true;
        }
        // Check for vertical win
        for (int col = 0; col < 5; col++) {
            boardHasWon = true;
            for (int row = 0; row < 5; row++) {
                if (board[row][col] != -1) {
                    boardHasWon = false;
                }
            }
            if (boardHasWon == true) {
                break;
            }
        }
        if (boardHasWon == true) {
            return true;
        } else {
            return false;
        }
    }
}