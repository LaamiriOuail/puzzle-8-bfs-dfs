package puzzle8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * The Case class represents a node in the puzzle-solving tree.
 */
public class Case {
    private static int Id = 1;
    private State state;
    private int id;
    private int parentId; // Parent node Id
    private int[] childIds = null; // Child node Ids
    private boolean isChildrenExtrait = false;

    /**
     * Constructs a Case with the specified state and parent ID.
     *
     * @param state    The state of the puzzle.
     * @param parentId The ID of the parent node.
     */
    public Case(State state, int parentId) {
        this.state = state;
        this.id = Id++;
        this.parentId = parentId;
    }

    /**
     * Constructs a Case with the specified state and assigns a root node (no parent).
     *
     * @param state The state of the puzzle.
     */
    public Case(State state) {
        this.state = state;
        this.id = Id++;
        this.parentId = 0; // Not have a parent => root node
    }

    /**
     * Gets the ID of the case.
     *
     * @return The ID of the case.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Sets the ID of the case.
     *
     * @param id The new ID for the case.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the ID of the parent node.
     *
     * @return The ID of the parent node.
     */
    public int getParentId() {
        return parentId;
    }

    /**
     * Sets the ID of the parent node.
     *
     * @param parentId The new ID of the parent node.
     */
    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    /**
     * Checks if children nodes have been extracted.
     *
     * @return True if children nodes have been extracted, false otherwise.
     */
    public boolean isChildrenExtrait() {
        return isChildrenExtrait;
    }

    /**
     * Sets the flag indicating whether children nodes have been extracted.
     *
     * @param childrenExtrait The flag value.
     */
    public void setChildrenExtrait(boolean childrenExtrait) {
        isChildrenExtrait = childrenExtrait;
    }

    /**
     * Gets the state of the puzzle.
     *
     * @return The state of the puzzle.
     */
    public State getState() {
        return state;
    }

    /**
     * Sets the state of the puzzle.
     *
     * @param state The new state of the puzzle.
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * Generates child nodes for the current node based on valid puzzle moves.
     *
     * @param states The list of existing states to avoid duplicates.
     * @return The list of child nodes.
     */
    public List<Case> getChildNodes(List<State> states) {
        List<Case> nodes = null;
        List<State> childStates = this.state.getChildStates(states);
        if (!childStates.isEmpty()) {
            nodes = new ArrayList<>();
            for (State state : childStates) {
                nodes.add(new Case(state, id));
            }
        }
        if (nodes != null) {
            int numberOfChild = nodes.size();
            childIds = new int[numberOfChild];
            for (int i = 0; i < numberOfChild; i++) {
                childIds[i] = nodes.get(i).id;
            }
        }
        isChildrenExtrait = true;
        return nodes;
    }

    /**
     * Returns a string representation of the Case object.
     *
     * @return A string representation of the Case object.
     */
    @Override
    public String toString() {
        return "Case{" +
                "state=" + state +
                ", id=" + id +
                ", parentId=" + parentId +
                ", childIds=" + Arrays.toString(childIds) +
                ", isChildrenExtrait=" + isChildrenExtrait +
                '}';
    }

    /**
     * Checks if this Case is equal to another object (comparison based on state).
     *
     * @param o The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Case aCase = (Case) o;
        return state.equals(aCase.state);
    }

    /**
     * Generates a hash code for the Case object.
     *
     * @return The hash code for the Case object.
     */
    @Override
    public int hashCode() {
        int result = Objects.hash(state, id, parentId, isChildrenExtrait);
        result = 31 * result + Arrays.hashCode(childIds);
        return result;
    }

    /**
     * Prints information about the Case to the console.
     */
    public void printCase() {
        System.out.println("--------- Case id = " + id + " ---------");
        System.out.println("--------- Parent id =  " + parentId + " ---------");
        state.printState();
        System.out.println("-------------------------------------");
        System.out.println("-------------------------------------");
    }

    /**
     * Gets the array of child node IDs.
     *
     * @return The array of child node IDs.
     */
    public int[] getChildIds() {
        return childIds;
    }
}
