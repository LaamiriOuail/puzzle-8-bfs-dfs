package puzzle8;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * The DepthFirstSearchEngine class extends AbstractSearchEngine and implements the Depth-First Search algorithm.
 */
public class DepthFirstSearchEngine extends AbstractSearchEngine {

    private Stack<Case> stack = new Stack<>();

    /**
     * Constructs a DepthFirstSearchEngine with the initial and target nodes for the puzzle.
     *
     * @param initialNode The initial state of the puzzle.
     * @param targetNode  The target state of the puzzle.
     */
    public DepthFirstSearchEngine(Case initialNode, Case targetNode) {
        super(initialNode, targetNode);
        doDFS();
    }

    /**
     * Performs the Depth-First Search algorithm to find the path from the initial state to the target state.
     */
    private void doDFS() {
        stack.push(startCase);
        visited.add(startCase);

        while (!stack.isEmpty()) {
            Case currentNode = stack.pop();
            path.add(currentNode);

            if (currentNode.equals(goalCase)) {
                return;
            }

            neighbors = currentNode.getChildNodes(new ArrayList<>()); // You may need to pass the proper states list
            for (Case neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    stack.push(neighbor);
                }
            }
        }
    }
}
