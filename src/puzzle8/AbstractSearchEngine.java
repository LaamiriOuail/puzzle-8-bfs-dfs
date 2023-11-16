package puzzle8;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSearchEngine {
    public AbstractSearchEngine(Case initialeNode, Case targetNode) {
        puzzle8 = new Puzzle8(initialeNode, targetNode);
        initSearch();
    }
    public Puzzle8 getPuzzle8() { return puzzle8; }
    protected Puzzle8 puzzle8;
    protected List<Case> visited = new ArrayList<>();
    protected List<Case> neighbors= null;
    protected List<Case> path = null;
    protected int maxDepth;
    protected List<Case> spaceState=null;
    protected Case startCase, goalCase;
    //protected boolean isSearching = true;

    private void initSearch() {
        if (path == null) {
            path = new ArrayList<Case>();
        }
        if(spaceState==null){
            spaceState=new ArrayList<Case>(puzzle8.getSpaceState());
        }
        maxDepth=0;
        startCase=puzzle8.getRootNode();
        goalCase=puzzle8.getTargetNode();
        path.add(startCase);
    }
    public int getMaxDepth() {
        return maxDepth;
    }
    protected void displayResult() {
        if (path.isEmpty()) {
            System.out.println("Path is Empty");
        } else {
            for (Case dfsPathElement : path) {
                dfsPathElement.printCase();
            }
        }
    }

}
