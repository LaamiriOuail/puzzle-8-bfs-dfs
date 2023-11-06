package puzzle8;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DepthFirstSearchEngine extends AbstractSearchEngine {

    private Stack<Case> stack = new Stack<>();
    public DepthFirstSearchEngine(Case initialNode, Case targetNode) {
        super(initialNode, targetNode);
        doDFS();
    }

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
