import java.util.ArrayList;

public class Day12_Part1 {
    public static void main(String[] args) {
        long start = System.nanoTime();
        ArrayList<String> input = new importFileLines().getInput("Day12.txt");
        ArrayList<String> paths = new ArrayList<>();

        for (String line : input) {
            if (line.split("-")[0].contains("start")) {
                paths.add("start," + line.split("-")[1]);
            } else if (line.split("-")[1].contains("start")) {
                paths.add("start," + line.split("-")[0]);
            }
        }

        for (String path : paths) {
            System.out.println(path);
        }

        System.out.println();
        while (true) {
            boolean hasEnd = true;
            for (int i = 0; i < paths.size(); i++) {
                String path = paths.get(i);
                if (path.substring(path.length() - 3, path.length()).equals("end")) continue;
                hasEnd = false;
                boolean smallCaveTwice = false;
                String[] chars = path.split(",");
                String lastChar = chars[chars.length - 1];
                for (int j = 0; j < chars.length; j++) {
                    if (chars[j].equals(chars[j].toLowerCase())) {
                        for (int k = 0; k < chars.length; k++) {
                            if (chars[j].equals(chars[k]) && j != k) {
                                smallCaveTwice = true;
                            }
                        }
                    }
                }
                for (String line : input) {
                    if (line.split("-")[0].equals(String.valueOf(lastChar)) || line.split("-")[1].equals(String.valueOf(lastChar))) {
                        String connectingCave;
                        if (line.split("-")[1].equals(String.valueOf(lastChar))) {
                            connectingCave = line.split("-")[0];
                        } else {
                            connectingCave = line.split("-")[1];
                        }

                        if (!connectingCave.equals("start")) {
                            if (connectingCave.toLowerCase().equals(connectingCave)) {
                                if (!smallCaveTwice) {
                                    paths.add(path + "," + connectingCave);
                                } else {
                                    if (path.contains(connectingCave)) continue;
                                    else paths.add(path + "," + connectingCave);
                                }
                            } else {
                                paths.add(path + "," + connectingCave);
                            }
                        }
                    }
                }
                paths.remove(i);
            }
            if (hasEnd) break;
        }
        for (int i = paths.size() - 1; i >= 0; i--) {
            String path = paths.get(i);
            if (!(path.substring(path.length() - 3, path.length()).equals("end"))) {
                paths.remove(i);
            }
        }
        for (String line : paths) {
            System.out.println(line);
        }
        System.out.println("Amount of paths: " + paths.size());
        long end = System.nanoTime();
        System.out.println("\nTime: " + (end - start) / (double) 1000000);
    }
}
