package puzzle8;

import java.util.ArrayList;
import java.util.List;

/**
 * The AbstractSearchEngine class is an abstract base class for puzzle-solving search engines.
 */
public abstract class AbstractSearchEngine {
    /**
     * Constructs an AbstractSearchEngine with the initial and target nodes for the puzzle.
     *
     * @param initialNode The initial state of the puzzle.
     * @param targetNode  The target state of the puzzle.
     */
    public AbstractSearchEngine(Case initialNode, Case targetNode) {
        puzzle8 = new Puzzle8(initialNode, targetNode);
        initSearch();
    }

    /**
     * Gets the Puzzle8 instance associated with the search engine.
     *
     * @return The Puzzle8 instance.
     */
    public Puzzle8 getPuzzle8() {
        return puzzle8;
    }

    protected Puzzle8 puzzle8;
    protected List<Case> visited = new ArrayList<>();
    protected List<Case> neighbors = null;
    protected List<Case> path = null;
    protected int maxDepth;
    protected List<Case> spaceState = null;
    protected Case startCase, goalCase;

    // protected boolean isSearching = true;

    /**
     * Initializes the search process.
     */
    private void initSearch() {
        if (path == null) {
            path = new ArrayList<Case>();
        }
        if (spaceState == null) {
            spaceState = new ArrayList<Case>(puzzle8.getSpaceState());
        }
        maxDepth = 0;
        startCase = puzzle8.getRootNode();
        goalCase = puzzle8.getTargetNode();
        path.add(startCase);
    }

    /**
     * Gets the maximum depth reached during the search.
     *
     * @return The maximum depth reached.
     */
    public int getMaxDepth() {
        return maxDepth;
    }

    /**
     * Displays the result of the search, printing the path if found.
     */
    protected void displayResult() {
        if (path.isEmpty()) {
            System.out.println("Path is Empty");
        } else {
            for (Case pathElement : path) {
                pathElement.printCase();
            }
        }
    }
}
