import java.util.ArrayList;

public class Day3_Part1 {
    public static void main(String[] args) {
        System.out.println(getOxygenRating());
        System.out.println(getCarbonDioxideRating());

        int decimal1 = Integer.parseInt(getOxygenRating(), 2);
        int decimal2 = Integer.parseInt(getCarbonDioxideRating(), 2);

        System.out.println(decimal1 * decimal2);
    }

    public static String getOxygenRating() {
        ArrayList<String> list = new importFileLines().getInput("Day3.txt");
        int i = 0;
        while (list.size() != 1 && i < list.get(0).length()) {
            int count0 = 0;
            int count1 = 0;
            for (String string : list) {
                if (string.charAt(i) == '0') {
                    count0++;
                } else {
                    count1++;
                }
            }
            if (count0 > count1) {
                for (int e = list.size() - 1; e >= 0; e -= 1) {
                    String item = list.get(e);
                    if (item.charAt(i) == '1') {
                        System.out.println("removed: " + item);
                        list.remove(e);
                    }
                }
            } else if (count1 > count0) {
                for (int e = list.size() - 1; e >= 0; e -= 1) {
                    String item = list.get(e);
                    if (item.charAt(i) == '0') {
                        System.out.println("removed: " + item);
                        list.remove(e);
                    }
                }
            } else {
                for (int e = list.size() - 1; e >= 0; e -= 1) {
                    String item = list.get(e);
                    if (item.charAt(i) == '0') {
                        System.out.println("removed: " + item);
                        list.remove(e);
                    }
                }
            }
            System.out.println();
            i++;
        }
        return list.get(0);
    }

    public static String getCarbonDioxideRating() {
        ArrayList<String> list = new importFileLines().getInput("Day3.txt");
        int i = 0;
        while (list.size() != 1 && i < list.get(0).length()) {
            int count0 = 0;
            int count1 = 0;
            for (String string : list) {
                if (string.charAt(i) == '0') {
                    count0++;
                } else {
                    count1++;
                }
            }
            if (count0 < count1) {
                for (int e = list.size() - 1; e >= 0; e -= 1) {
                    String item = list.get(e);
                    if (item.charAt(i) == '1') {
                        System.out.println("removed: " + item);
                        list.remove(e);
                    }
                }
            } else if (count1 < count0) {
                for (int e = list.size() - 1; e >= 0; e -= 1) {
                    String item = list.get(e);
                    if (item.charAt(i) == '0') {
                        System.out.println("removed: " + item);
                        list.remove(e);
                    }
                }
            } else {
                for (int e = list.size() - 1; e >= 0; e -= 1) {
                    String item = list.get(e);
                    if (item.charAt(i) == '1') {
                        System.out.println("removed: " + item);
                        list.remove(e);
                    }
                }
            }
            System.out.println();
            i++;
        }
        return list.get(0);
    }
}