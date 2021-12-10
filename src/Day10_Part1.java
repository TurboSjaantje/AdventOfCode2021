import java.util.*;

public class Day10_Part1 {
    public static void main(String[] args) {
        long start = System.nanoTime();
        ArrayList<String> input = new importFileLines().getInput("Day10.txt");
        ArrayList<String> corruptedLines = new ArrayList<>();
        ArrayList<String> incompleteLines = new ArrayList<>();
        ArrayList<Character> openingChars = new ArrayList<>(Arrays.asList('(', '[', '{', '<'));
        ArrayList<Character> closingChars = new ArrayList<>(Arrays.asList(')', ']', '}', '>'));
        long score = 0;
        for (String line : input) {
            StringBuilder builder = new StringBuilder(line);
            int i = 0;
            boolean broke = false;
            while (!broke) {
                if (i >= builder.length()) {
                    incompleteLines.add(builder.toString());
                    break;
                }
                if (openingChars.contains(builder.charAt(i))) i++;
                else if (closingChars.contains(builder.charAt(i))) {
                    if (openingChars.indexOf(builder.charAt(i - 1)) != closingChars.indexOf(builder.charAt(i))) {
                        corruptedLines.add(line);
                        broke = true;
                        if (builder.charAt(i) == ')') score += 3;
                        else if (builder.charAt(i) == ']') score += 57;
                        else if (builder.charAt(i) == '}') score += 1197;
                        else if (builder.charAt(i) == '>') score += 25137;
                    } else {
                        builder.deleteCharAt(i);
                        builder.deleteCharAt(i - 1);
                        i--;
                    }
                }
            }
        }
        ArrayList<Long> scores = new ArrayList<>();
        for (String incompleteLine : incompleteLines) {
            long score2 = 0;
            String[] characters = incompleteLine.split("");
            for (int i = incompleteLine.length() - 1; i >= 0; i--) {
                score2 = score2 * 5;
                if (characters[i].equals("(")) score2 += 1;
                if (characters[i].equals("[")) score2 += 2;
                if (characters[i].equals("{")) score2 += 3;
                if (characters[i].equals("<")) score2 += 4;
            }
            scores.add(score2);
        }
        scores.sort(Collections.reverseOrder());
        System.out.println("\nAmount of corrupted lines: " + corruptedLines.size());
        System.out.println("Score of corrupt lines: " + score);
        System.out.println("\nAmount of incomplete lines: " + incompleteLines.size());
        System.out.println("Score of incomplete lines: " + scores.get(scores.size() / 2));
        long end = System.nanoTime();
        System.out.println("\nTime: " + (end - start) / (double) 1000000);
    }
}
