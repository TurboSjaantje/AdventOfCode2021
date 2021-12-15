import java.util.*;

public class test {
    public static void main(String[] args) {

        ArrayList<String> input = new importFileLines().getInput("test.txt");
        int[][] field = new int[input.size()][input.get(0).length()];
        StringBuilder path = new StringBuilder("0,0");

        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).length(); j++) {
                int value = Integer.parseInt(String.valueOf(input.get(i).charAt(j)));
                field[i][j] = value;
            }
        }

        while (true) {
            String pathh = path.toString();
            String[] coords = pathh.split(";");
            String coordd = coords[coords.length - 1];
            int y = Integer.parseInt(coordd.split(",")[0]);
            int x = Integer.parseInt(coordd.split(",")[1]);
            if (y >= field.length - 1 && x >= field[0].length - 1) break;
            HashMap<String, Double> coordCosts = new HashMap<>();

            if ((y + 1) < field.length)
                coordCosts.put(((y + 1) + "," + x), distanceToEnd(y + 1, x, field) + field[y + 1][x]);
            if ((y - 1) >= 0)
                coordCosts.put(((y - 1) + "," + x), distanceToEnd(y - 1, x, field) + field[y - 1][x]);
            if ((x + 1) < field[0].length)
                coordCosts.put((y + "," + (x + 1)), distanceToEnd(y, x + 1, field) + field[y][x + 1]);
            if ((x - 1) >= 0)
                coordCosts.put((y + "," + (x - 1)), distanceToEnd(y, x - 1, field) + field[y][x - 1]);

            List<Double> costs = new ArrayList<>(coordCosts.values());
            costs.sort(Comparator.naturalOrder());
            if (costs.size() > 1) {
                HashMap<String, Double> coordCosts3 = new HashMap<>();
                for (String coord : coordCosts.keySet()) {
                    HashMap<String, Double> coordCosts2 = new HashMap<>();
                    int y2 = Integer.parseInt(coord.split(",")[0]);
                    int x2 = Integer.parseInt(coord.split(",")[1]);

                    if ((y2 + 1) < field.length)
                        coordCosts2.put(((y2 + 1) + "," + x2), distanceToEnd(y2 + 1, x2, field) + field[y2 + 1][x2]);
                    if ((y2 - 1) >= 0)
                        coordCosts2.put(((y2 - 1) + "," + x2), distanceToEnd(y2 - 1, x2, field) + field[y2 - 1][x2]);
                    if ((x2 + 1) < field[0].length)
                        coordCosts2.put((y2 + "," + (x2 + 1)), distanceToEnd(y2, x2 + 1, field) + field[y2][x2 + 1]);
                    if ((x2 - 1) >= 0)
                        coordCosts2.put((y2 + "," + (x2 - 1)), distanceToEnd(y2, x2 - 1, field) + field[y2][x2 - 1]);

                    List<Double> costs2 = new ArrayList<>(coordCosts2.values());
                    costs2.sort(Comparator.naturalOrder());
                    coordCosts3.put(coord, costs2.get(0));
                }
                for (String key : coordCosts3.keySet()) {
                    coordCosts.replace(key, coordCosts.get(key) + coordCosts3.get(key));
                }
                String cheapestCoord = "";
                for (String key : coordCosts.keySet()) {
                    cheapestCoord = key;
                    break;
                }
                for (String key : coordCosts.keySet()) {
                    if (coordCosts.get(key) < coordCosts.get(cheapestCoord)) cheapestCoord = key;
                }
                path.append(";").append(cheapestCoord);
            } else {
                for (String key : coordCosts.keySet()) {
                    if (Objects.equals(coordCosts.get(key), costs.get(0))) {
                        path.append(";").append(key);
                        break;
                    }
                }
            }

            int cost = 0;
            for (String position : path.toString().split(";")) {
                cost += field[Integer.parseInt(position.split(",")[0])][Integer.parseInt(position.split(",")[1])];
            }

            System.out.println("Cost: " + (cost - field[0][0]));

        }

        for (String item : path.toString().split(";")) {
            System.out.println(item);
        }

        int cost = 0;
        for (String position : path.toString().split(";")) {
            cost += field[Integer.parseInt(position.split(",")[0])][Integer.parseInt(position.split(",")[1])];
        }

        System.out.println("Cost: " + (cost - field[0][0]));

    }

    public static double distanceToEnd(int y, int x, int[][] field) {
        int distance = 0;

        int dY = (field.length - 1) - y;
        int dX = (field[0].length - 1) - x;

        return java.lang.Math.sqrt((double) ((dY * dY) + (dX * dX)));
    }

}
