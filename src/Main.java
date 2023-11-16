import puzzle8.*;

public class Main {
    public static void main(String[] args) {
        State initialState = new State(new int[][]{{1,2,0},{3,4,5},{6,7,8}});
        State targetState  = new State(new int[][]{{0,1,2},{3,4,5},{6,7,8}});
        Case initialNode = new Case(initialState);
        Case targetNode = new Case(targetState);
        //AbstractSearchEngine searchEngine=new AStarSearchEngine(initialNode,targetNode);
        AbstractSearchEngine searchEngine=new BreadthFirstSearchEngine(initialNode,targetNode);
        //AbstractSearchEngine searchEngine=new DepthFirstSearchEngine(initialNode,targetNode);
        searchEngine.displayResult();
    }
}