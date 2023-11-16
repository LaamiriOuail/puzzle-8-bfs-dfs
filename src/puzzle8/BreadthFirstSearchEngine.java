package puzzle8;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * The BreadthFirstSearchEngine class extends AbstractSearchEngine and implements the Breadth-First Search algorithm.
 */
public class BreadthFirstSearchEngine extends AbstractSearchEngine {

    private Queue<Case> queue = new LinkedList<>();

    /**
     * Constructs a BreadthFirstSearchEngine with the initial and target nodes for the puzzle.
     *
     * @param initialNode The initial state of the puzzle.
     * @param targetNode  The target state of the puzzle.
     */
    public BreadthFirstSearchEngine(Case initialNode, Case targetNode) {
        super(initialNode, targetNode);
        doBFS();
    }

    /**
     * Performs the Breadth-First Search algorithm to find the path from the initial state to the target state.
     */
    private void doBFS() {
        queue.add(startCase);
        visited.add(startCase);

        while (!queue.isEmpty()) {
            Case currentNode = queue.poll();
            path.add(currentNode);

            if (currentNode.equals(goalCase)) {
                return;
            }

            neighbors = currentNode.getChildNodes(new ArrayList<>()); // You may need to pass the proper states list
            for (Case neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
            maxDepth++;
        }
    }
}
