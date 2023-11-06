package puzzle8;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Puzzle8 {
    private Case rootNode;
    private Case targetNode;

    public Puzzle8(Case rootNode, Case targetNode) {
        this.rootNode = rootNode;
        this.targetNode = targetNode;
    }
    //getSpaceState
    public List<Case> getSpaceState(){
        List<Case> nodes=new ArrayList<Case>();
        List<Case> currentNodes=null;
        List<Case> temporelNodes=null;
        List<State> states = new ArrayList<>();
        nodes.add(rootNode);
        states.add(rootNode.getState());
        if(!rootNode.equals(targetNode)){
            boolean targetExist=false;
            while(!targetExist){
                currentNodes=new ArrayList<Case>(nodes);
                for (Case node:currentNodes) {
                    if(!node.isChildrenExtrait()){
                        temporelNodes=node.getChildNodes(states);//Probleme here
                        if(temporelNodes!=null){
                            nodes.addAll(temporelNodes);
                            for (Case nodei:nodes) {
                                if(nodei.equals(targetNode)){
                                    targetExist=true;
                                    break;
                                }
                            }
                            if(targetExist){
                                break;
                            }
                        }
                    }
                }
            }
        }
        return nodes;
    }

    public Case getRootNode() {
        return rootNode;
    }

    public Case getTargetNode() {
        return targetNode;
    }
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
