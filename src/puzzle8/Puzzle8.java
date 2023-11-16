package puzzle8;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * The Puzzle8 class represents the 8-puzzle problem and provides methods for solving it.
 */
public class Puzzle8 {
    private Case rootNode;
    private Case targetNode;

    /**
     * Constructs a Puzzle8 instance with the specified root and target nodes.
     *
     * @param rootNode   The initial state of the puzzle.
     * @param targetNode The target state of the puzzle.
     */
    public Puzzle8(Case rootNode, Case targetNode) {
        this.rootNode = rootNode;
        this.targetNode = targetNode;
    }

    /**
     * Retrieves the space state by generating possible states using BFS until the target state is reached.
     *
     * @return A list of all states encountered during the BFS traversal.
     */
    public List<Case> getSpaceState() {
        List<Case> nodes = new ArrayList<>();
        List<Case> currentNodes = null;
        List<Case> temporelNodes = null;
        List<State> states = new ArrayList<>();
        nodes.add(rootNode);
        states.add(rootNode.getState());

        if (!rootNode.equals(targetNode)) {
            boolean targetExist = false;

            while (!targetExist) {
                currentNodes = new ArrayList<>(nodes);

                for (Case node : currentNodes) {
                    if (!node.isChildrenExtrait()) {
                        temporelNodes = node.getChildNodes(states);

                        if (temporelNodes != null) {
                            nodes.addAll(temporelNodes);

                            for (Case nodei : nodes) {
                                if (nodei.equals(targetNode)) {
                                    targetExist = true;
                                    break;
                                }
                            }

                            if (targetExist) {
                                break;
                            }
                        }
                    }
                }
            }
        }
        return nodes;
    }

    /**
     * Retrieves the root node of the puzzle.
     *
     * @return The root node of the puzzle.
     */
    public Case getRootNode() {
        return rootNode;
    }

    /**
     * Retrieves the target node of the puzzle.
     *
     * @return The target node of the puzzle.
     */
    public Case getTargetNode() {
        return targetNode;
    }

    /**
     * Finds the path from the root node to the target node using Breadth-First Search.
     *
     * @return The list of nodes representing the path from the root to the target.
     */
    public List<Case> findPathBFS() {
        Queue<Case> queue = new LinkedList<>();
        List<Case> visited = new ArrayList<>();
        List<Case> path = new ArrayList<>();

        queue.add(rootNode);
        visited.add(rootNode);

        while (!queue.isEmpty()) {
            Case currentNode = queue.poll();
            path.add(currentNode);

            if (currentNode.equals(targetNode)) {
                return path;
            }

            List<Case> neighbors = currentNode.getChildNodes(new ArrayList<>()); // You may need to pass the proper states list
            for (Case neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        // If no path is found
        return new ArrayList<>();
    }
}
