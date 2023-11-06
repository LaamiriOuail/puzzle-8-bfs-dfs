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
    /*
    protected boolean equals(Dimension d1, Dimension d2) {
        return d1.getWidth() == d2.getWidth() && d1.getHeight() == d2.getHeight();
    }

    public Dimension [] getPath() {
        Dimension [] ret = new Dimension[maxDepth];
        for (int i=0; i<maxDepth; i++) {
            ret[i] = searchPath[i];
        }
        return ret;
    }

    protected Dimension [] getPossibleMoves(Dimension loc) {
        Dimension tempMoves [] = new Dimension[4];
        tempMoves[0] = tempMoves[1] = tempMoves[2] = tempMoves[3] = null;
        int x = loc.width;
        int y = loc.height;
        int num = 0;
        if (maze.getValue(x - 1, y) == 0 || maze.getValue(x - 1, y) == Maze.GOAL_LOC_VALUE) {
            tempMoves[num++] = new Dimension(x - 1, y);
        }
        if (maze.getValue(x + 1, y) == 0 || maze.getValue(x + 1, y) == Maze.GOAL_LOC_VALUE) {
            tempMoves[num++] = new Dimension(x + 1, y);
        }
        if (maze.getValue(x, y - 1) == 0 || maze.getValue(x, y - 1) == Maze.GOAL_LOC_VALUE) {
            tempMoves[num++] = new Dimension(x, y - 1);
        }
        if (maze.getValue(x, y + 1) == 0 || maze.getValue(x, y + 1) == Maze.GOAL_LOC_VALUE) {
            tempMoves[num++] = new Dimension(x, y + 1);
        }
        return tempMoves;
    }
    */
}
