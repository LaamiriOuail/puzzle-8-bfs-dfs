package puzzle8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Case {
    private static int Id=1;
    private State state;
    private int id;
    private int parentId;// Parent node Id
    private int[] childIds=null;//childNodeId
    private boolean isChildrenExtrait=false;

    public Case(State state, int parentId) {
        this.state = state;
        this.id = Id++;
        this.parentId = parentId;
    }

    public Case(State state) {
        this.state = state;
        this.id = Id++;
        this.parentId = 0;//Not have a parent => root node
    }


    public  int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }


    public boolean isChildrenExtrait() {
        return isChildrenExtrait;
    }

    public void setChildrenExtrait(boolean childrenExtrait) {
        isChildrenExtrait = childrenExtrait;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
    public List<Case> getChildNodes(List<State> states){//Ok
        List<Case> nodes=null;
        List<State> childStates=this.state.getChildStates(states);
        if(!childStates.isEmpty()){
            nodes = new ArrayList<Case>();
            for (State state:childStates) {
                nodes.add(new Case(state,id));
            }
        }
        if(nodes!=null){
            int numberOfChild=nodes.size();
            childIds=new int[numberOfChild];
            for(int i=0;i<numberOfChild;i++){
                childIds[i]=nodes.get(i).id;
            }
        }
        isChildrenExtrait=true;
        return nodes;
    }

    @Override
    public String toString() {
        return "Node{" +
                "state=" + state +
                ", id=" + id +
                ", parentId=" + parentId +
                ", childIds=" + Arrays.toString(childIds) +
                ", isChildrenExtrait=" + isChildrenExtrait +
                '}';
    }

    @Override
    public boolean equals(Object o) {//Compare by state
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Case node = (Case) o;
        return state.equals(node.state);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(state, id, parentId, isChildrenExtrait);
        result = 31 * result + Arrays.hashCode(childIds);
        return result;
    }
    public void printCase(){
        System.out.println("--------- Case id = "+id+" ---------");
        System.out.println("--------- Parent id =  "+parentId+" ---------");
        state.printState();
        System.out.println("-------------------------------------");
        System.out.println("-------------------------------------");

    }

    public int[] getChildIds() {
        return childIds;
    }

}

