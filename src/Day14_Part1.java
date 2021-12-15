import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class Day14_Part1 {
    public static void main(String[] args) {
        ArrayList<String> input = new importFileLines().getInput("test.txt");
        ArrayList<String> steps = new ArrayList<>();

        HashMap<String, Long> letterCount = new HashMap<>();
        HashMap<String, Long> pairCount = new HashMap<>();

        char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

        for (String line : input) {
            if (line.contains(" -> ")) steps.add(line.split(" -> ")[0] + "-" + line.split(" -> ")[1]);
        }

        for (char c : alphabet) {
            letterCount.put(String.valueOf(c), 0L);
        }

        for (int i = 0; i < input.get(0).length() - 1; i++) {
            String first = input.get(0);
            pairCount.put(first.charAt(i) + "" + first.charAt(i + 1), 1L);
        }

        for (String charr : input.get(0).split("")) {
            letterCount.replace(charr, letterCount.get(charr) + 1);
        }

        for (int i = 0; i < 40; i++) {
            System.out.println(i);
            HashMap<String, Long> tempPairCount = new HashMap<>(pairCount);
            for (String pair : tempPairCount.keySet()) {
                if (tempPairCount.get(pair) != 0) {
                    for (int j = 0; j < tempPairCount.get(pair); j++) {
                        for (String step : steps) {
                            if (step.contains(pair)) {
                                if (pairCount.containsKey(pair.charAt(0) + "" + step.split("-")[1])) {
                                    pairCount.replace(pair.charAt(0) + "" + step.split("-")[1], pairCount.get(pair.charAt(0) + "" + step.split("-")[1]) + 1);
                                } else {
                                    pairCount.put(pair.charAt(0) + "" + step.split("-")[1], 1L);
                                }
                                if (pairCount.containsKey(step.split("-")[1] + "" + pair.charAt(1))) {
                                    pairCount.replace(step.split("-")[1] + "" + pair.charAt(1), pairCount.get(step.split("-")[1] + "" + pair.charAt(1)) + 1);
                                } else {
                                    pairCount.put(step.split("-")[1] + "" + pair.charAt(1), 1L);
                                }
                                letterCount.replace(step.split("-")[1], letterCount.get(step.split("-")[1]) + 1);
                                pairCount.replace(pair, pairCount.get(pair) - 1);
                            }
                        }
                    }
                }
            }
        }

        ArrayList<Long> list = new ArrayList<>();
        for (long value : letterCount.values()) {
            if (value != 0) list.add(value);
        }
        list.sort(Comparator.reverseOrder());
        System.out.println("Answer: " + (list.get(0) - list.get(list.size() -1) ) );
    }
}