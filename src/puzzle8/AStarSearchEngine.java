package puzzle8;

import java.util.*;

public class AStarSearchEngine extends AbstractSearchEngine {

    public AStarSearchEngine(Case initialNode, Case targetNode) {
        super(initialNode, targetNode);
        doAStar();
    }


    public void doAStar() {
        PriorityQueue<Case> openSet = new PriorityQueue<>(Comparator.comparingInt(this::calculateCost));
        openSet.add(startCase);

        Map<Case, Integer> gScore = new HashMap<>();
        gScore.put(startCase, 0);

        Map<Case, Case> cameFrom = new HashMap<>();

        while (!openSet.isEmpty()) {
            Case current = openSet.poll();

            if (current.equals(goalCase)) {
                reconstructPath(cameFrom, current);
                return;
            }

            visited.add(current);

            List<Case> neighbors = current.getChildNodes(new ArrayList<>());
            for (Case neighbor : neighbors) {
                int tentativeGScore = gScore.getOrDefault(current, 0) + 1; // Assuming uniform cost

                if (!gScore.containsKey(neighbor) || tentativeGScore < gScore.get(neighbor)) {
                    gScore.put(neighbor, tentativeGScore);
                    int fScore = tentativeGScore + calculateCost(neighbor);
                    openSet.add(neighbor);

                    cameFrom.put(neighbor, current);
                }
            }
        }

        System.out.println("No path found.");
    }

    private int calculateCost(Case node) {
        int cost = 0;
        int[][] currentState = node.getState().getData();
        int[][] goalState = goalCase.getState().getData();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (currentState[i][j] != goalState[i][j]) {
                    cost++;
                }
            }
        }

        return cost;
    }


    private void reconstructPath(Map<Case, Case> cameFrom, Case current) {
        List<Case> totalPath = new ArrayList<>();
        totalPath.add(current);

        while (cameFrom.containsKey(current)) {
            current = cameFrom.get(current);
            totalPath.add(current);
        }

        Collections.reverse(totalPath);
        path = totalPath;
    }
}
