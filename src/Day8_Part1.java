import java.util.ArrayList;

public class Day8_Part1 {
    public static void main(String[] args) {
        ArrayList<String> input = new importFileLines().getInput("Day8.txt");
        ArrayList<String> part1 = new ArrayList<>();
        ArrayList<String> part2 = new ArrayList<>();
        int output = 0;
        for (String line : input) {
            part1.add(line.substring(0, 58));
            part2.add(line.substring(61, line.length()));
        }
        for (int i = 0; i < part1.size(); i++) {
            ArrayList<String> chars1 = new ArrayList<>();
            ArrayList<String> chars2 = new ArrayList<>();
            ArrayList<String> chars3 = new ArrayList<>();
            ArrayList<String> chars4 = new ArrayList<>();
            ArrayList<String> chars5 = new ArrayList<>();
            ArrayList<String> chars6 = new ArrayList<>();
            ArrayList<String> chars7 = new ArrayList<>();
            ArrayList<String> chars8 = new ArrayList<>();
            ArrayList<String> chars9 = new ArrayList<>();
            ArrayList<String> chars0 = new ArrayList<>();
            for (String part : part1.get(i).split(" ")) {
                if (part.length() == 2) {
                    for (String item : part.split("")) {
                        chars1.add(item);
                    }
                }
                if (part.length() == 3) {
                    for (String item : part.split("")) {
                        chars7.add(item);
                    }
                }
                if (part.length() == 4) {
                    for (String item : part.split("")) {
                        chars4.add(item);
                    }
                }
                if (part.length() == 7) {
                    for (String item : part.split("")) {
                        chars8.add(item);
                    }
                }
            }
            for (String part : part1.get(i).split(" ")) {
                if (part.length() == 5) {
                    if (part.contains(chars1.get(0)) && part.contains(chars1.get(1))) {
                        for (String item : part.split("")) {
                            chars3.add(item);
                        }
                    }
                    if (howManyOfOther(part.split(""), chars4) == 2) {
                        for (String item : part.split("")) {
                            chars2.add(item);
                        }
                    }
                    if (howManyOfOther(part.split(""), chars4) == 3 && howManyOfOther(part.split(""), chars1) == 1) {
                        for (String item : part.split("")) {
                            chars5.add(item);
                        }
                    }
                }
            }
            for (String part : part1.get(i).split(" ")) {
                if (part.length() == 6) {
                    if (howManyOfOther(part.split(""), chars1) == 1) {
                        for (String item : part.split("")) {
                            chars6.add(item);
                        }
                    }
                    if (howManyOfOther(part.split(""), chars4) == 4) {
                        for (String item : part.split("")) {
                            chars9.add(item);
                        }
                    }
                    if (howManyOfOther(part.split(""), chars4) == 3 && howManyOfOther(part.split(""), chars1) != 1) {
                        for (String item : part.split("")) {
                            chars0.add(item);
                        }
                    }
                }
            }
            String valueInLetters = "";
            for (String numberInLetters : part2.get(i).split(" ")) {
                if (howManyOfOther(numberInLetters.split(""), chars1) == chars1.size()
                        && howManyOfOther(chars1, numberInLetters.split("")) == chars1.size()) {
                    valueInLetters += "1";
                } else if (howManyOfOther(numberInLetters.split(""), chars2) == chars2.size()
                        && howManyOfOther(chars2, numberInLetters.split("")) == chars2.size()) {
                    valueInLetters += "2";
                } else if (howManyOfOther(numberInLetters.split(""), chars3) == chars3.size()
                        && howManyOfOther(chars3, numberInLetters.split("")) == chars3.size()) {
                    valueInLetters += "3";
                } else if (howManyOfOther(numberInLetters.split(""), chars4) == chars4.size()
                        && howManyOfOther(chars4, numberInLetters.split("")) == chars4.size()) {
                    valueInLetters += "4";
                } else if (howManyOfOther(numberInLetters.split(""), chars5) == chars5.size()
                        && howManyOfOther(chars5, numberInLetters.split("")) == chars5.size()) {
                    valueInLetters += "5";
                } else if (howManyOfOther(numberInLetters.split(""), chars6) == chars6.size()
                        && howManyOfOther(chars6, numberInLetters.split("")) == chars6.size()) {
                    valueInLetters += "6";
                } else if (howManyOfOther(numberInLetters.split(""), chars7) == chars7.size()
                        && howManyOfOther(chars7, numberInLetters.split("")) == chars7.size()) {
                    valueInLetters += "7";
                } else if (howManyOfOther(numberInLetters.split(""), chars8) == chars8.size()
                        && howManyOfOther(chars8, numberInLetters.split("")) == chars8.size()) {
                    valueInLetters += "8";
                } else if (howManyOfOther(numberInLetters.split(""), chars9) == chars9.size()
                        && howManyOfOther(chars9, numberInLetters.split("")) == chars9.size()) {
                    valueInLetters += "9";
                } else if (howManyOfOther(numberInLetters.split(""), chars0) == chars0.size()
                        && howManyOfOther(chars0, numberInLetters.split("")) == chars0.size()) {
                    valueInLetters += "0";
                }
            }
            output += Integer.valueOf(valueInLetters);
        }
        System.out.println("\n\nFinal answer: " + output);
    }

    public static int howManyOfOther(String[] oneToCheck, ArrayList<String> oneToCheckOff) {
        ArrayList<String> tempList = new ArrayList<>();
        for (String string : oneToCheck) {
            tempList.add(string);}
        tempList.retainAll(oneToCheckOff);
        return tempList.size();
    }

    public static int howManyOfOther(ArrayList<String> oneToCheck, String[] oneToCheckOf) {
        ArrayList<String> tempList = new ArrayList<>();
        for (String string : oneToCheckOf) {
            tempList.add(string);}
        oneToCheck.retainAll(tempList);
        return tempList.size();
    }
}
