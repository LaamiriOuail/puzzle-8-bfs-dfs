package puzzle8;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearchEngine extends AbstractSearchEngine {

    private Queue<Case> queue = new LinkedList<>();

    public BreadthFirstSearchEngine(Case initialeNode, Case targetNode) {
        super(initialeNode, targetNode);
        doBFS();
    }

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
