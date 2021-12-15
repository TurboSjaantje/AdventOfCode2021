import java.util.ArrayList;
import java.util.HashMap;

public class Day15_Part1 {
    public static void main(String[] args) {

        ArrayList<String> input = new importFileLines().getInput("test.txt");
        ArrayList<String> unvisitedNodes = new ArrayList<>();
        ArrayList<String> distances = new ArrayList<>();

        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).split("").length; j++) {
                if (i == 0 && j == 0) {
                    unvisitedNodes.add(i + "," + j + ":" + 0);
                } else {
                    unvisitedNodes.add(i + "," + j + ":" + input.get(0).charAt(j));
                }
            }
        }

        distances.add(unvisitedNodes.get(0));
        unvisitedNodes.remove(0);

        for (String node : unvisitedNodes) {
            distances.add(node.split(":")[0] + ":" + 999999999);
        }

        String node = distances.get(0);

        while (true) {

            if (unvisitedNodes.size() == 0) break;

            System.out.println(node + "\n");

            //get nodes around the node
            int y = Integer.parseInt(node.split(":")[0].split(",")[0]);
            int x = Integer.parseInt(node.split(":")[0].split(",")[1]);

            String above = "";
            String below = "";
            String left = "";
            String right = "";

            for (String nodeAround : unvisitedNodes) {
                if (y - 1 >= 0) if (nodeAround.split(":")[0].equals((y - 1) + "," + x)) above = nodeAround;
                if (y + 1 < input.size())
                    if (nodeAround.split(":")[0].equals((y + 1) + "," + x)) below = nodeAround;
                if (x - 1 >= 0) if (nodeAround.split(":")[0].equals(y + "," + (x - 1))) left = nodeAround;
                if (x + 1 < input.get(0).length())
                    if (nodeAround.split(":")[0].equals(y + "," + (x + 1))) right = nodeAround;
            }

            //calculate their distance with (lastNode + cost)
            int aboveDistance = 0;
            int belowDistance = 0;
            int leftDistance = 0;
            int rightDistance = 0;

            if (!above.isEmpty())
                aboveDistance = Integer.parseInt(node.split(":")[1]) + Integer.parseInt(above.split(":")[1]);
            if (!below.isEmpty())
                belowDistance = Integer.parseInt(node.split(":")[1]) + Integer.parseInt(below.split(":")[1]);
            if (!left.isEmpty())
                leftDistance = Integer.parseInt(node.split(":")[1]) + Integer.parseInt(left.split(":")[1]);
            if (!right.isEmpty())
                rightDistance = Integer.parseInt(node.split(":")[1]) + Integer.parseInt(right.split(":")[1]);

            //update their distance if it is smaller than the previous one
            for (String distanceNode : distances) {
                if (distanceNode.split(":")[0].equals(above.split(":")[0]) && Integer.parseInt(distanceNode.split(":")[1]) > aboveDistance)
                    distances.set(distances.indexOf(distanceNode), distanceNode.split(":")[0] + ":" + aboveDistance);
                if (distanceNode.split(":")[0].equals(below.split(":")[0]) && Integer.parseInt(distanceNode.split(":")[1]) > belowDistance)
                    distances.set(distances.indexOf(distanceNode), distanceNode.split(":")[0] + ":" + belowDistance);
                if (distanceNode.split(":")[0].equals(left.split(":")[0]) && Integer.parseInt(distanceNode.split(":")[1]) > leftDistance)
                    distances.set(distances.indexOf(distanceNode), distanceNode.split(":")[0] + ":" + leftDistance);
                if (distanceNode.split(":")[0].equals(right.split(":")[0]) && Integer.parseInt(distanceNode.split(":")[1]) > rightDistance)
                    distances.set(distances.indexOf(distanceNode), distanceNode.split(":")[0] + ":" + rightDistance);
            }

            //print the lastCheckedNode and ones around
            System.out.println(node + ":\n-" + above + " -> " + aboveDistance + "\n-" + below + " -> " + belowDistance + "\n-" + left + " -> " + leftDistance + "\n-" + right + " -> " + rightDistance + "\n");

            //remove last checked node
            if (!node.equals("0,0:0")) {
                for (int i = 0; i < unvisitedNodes.size(); i++) {
                    String removeNode = unvisitedNodes.get(i);
                    if (removeNode.split(":")[0].equals(node.split(":")[0]))
                        unvisitedNodes.remove(unvisitedNodes.indexOf(removeNode));
                }
            }

            //set nextNode
            HashMap<String, Integer> costs = new HashMap<>();
            if (!above.isEmpty()) costs.put(above, Integer.parseInt(above.split(":")[1]));
            if (!below.isEmpty()) costs.put(below, Integer.parseInt(below.split(":")[1]));
            if (!left.isEmpty()) costs.put(left, Integer.parseInt(left.split(":")[1]));
            if (!right.isEmpty()) costs.put(right, Integer.parseInt(right.split(":")[1]));

            node = "";
            for (String key : costs.keySet()) {
                node = key;
                break;
            }

            for (String key : costs.keySet()) {
                if (costs.get(key) < costs.get(node)) node = key;
            }

            for (String getCorrectDistance : distances) {
                if (node.split(":")[0].equals(getCorrectDistance.split(":")[0])) node = getCorrectDistance;
            }
        }

    }

}
