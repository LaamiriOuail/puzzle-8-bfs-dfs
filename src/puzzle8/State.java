package puzzle8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The State class represents the state of the 8-puzzle game.
 */
public class State {
    private int[][] data = null;

    /**
     * Constructs a new State object with the specified puzzle data.
     *
     * @param data The puzzle data, a 3x3 array of integers.
     * @throws IllegalArgumentException if the puzzle data is invalid.
     */
    public State(int[][] data) {
        if (isValidPuzzle(data)) {
            this.data = data;
        } else {
            throw new IllegalArgumentException("Invalid puzzle data.");
        }
    }

    /**
     * Checks if the given puzzle data is valid for an 8-puzzle.
     *
     * @param data The puzzle data to be validated.
     * @return True if the puzzle data is valid, false otherwise.
     */
    private boolean isValidPuzzle(int[][] data) {
        if (data.length != 3 || data[0].length != 3) {
            return false; // The puzzle should be 3x3
        }

        boolean[] used = new boolean[9]; // to track which numbers have been used

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int num = data[i][j];
                if (num < 0 || num > 8 || used[num]) {
                    return false; // Invalid number or repeated number
                }
                used[num] = true;
            }
        }

        return true; // Puzzle is valid
    }

    /**
     * Gets the puzzle data.
     *
     * @return The puzzle data.
     */
    public int[][] getData() {
        return data;
    }

    /**
     * Gets the position of the empty cell in the puzzle.
     *
     * @return The position of the empty cell.
     */
    private Position getEmptyCellPosition() {
        Position position = new Position(-1, -1);
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (data[x][y] == 0) {
                    position.setX(x); // Row
                    position.setY(y); // Column
                    return position;
                }
            }
        }
        // If 0 is not found, return an invalid position, e.g., (-1, -1)
        return position;
    }

    /**
     * Gets a list of possible positions for the empty cell to move.
     *
     * @return A list of possible move positions.
     */
    private List<Position> getPossibleMovePositions() {
        List<Position> possibleMovePositions = new ArrayList<>();
        Position emptyCellPosition = getEmptyCellPosition();
        Position tempPosition;
        if (emptyCellPosition.isValid()) {
            tempPosition = new Position(emptyCellPosition.getX() - 1, emptyCellPosition.getY()); // Left Move
            if (tempPosition.isValid()) possibleMovePositions.add(tempPosition);
            tempPosition = new Position(emptyCellPosition.getX() + 1, emptyCellPosition.getY()); // Right Move
            if (tempPosition.isValid()) possibleMovePositions.add(tempPosition);
            tempPosition = new Position(emptyCellPosition.getX(), emptyCellPosition.getY() - 1); // Bottom Move
            if (tempPosition.isValid()) possibleMovePositions.add(tempPosition);
            tempPosition = new Position(emptyCellPosition.getX(), emptyCellPosition.getY() + 1); // Top Move
            if (tempPosition.isValid()) possibleMovePositions.add(tempPosition);
        }
        return possibleMovePositions;
    }

    /**
     * Checks if this state is equal to another object.
     *
     * @param o The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.data[i][j] != state.data[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Generates a hash code for the state.
     *
     * @return The hash code for the state.
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(data);
    }

    /**
     * Checks if this state exists in a list of states.
     *
     * @param states The list of states to check against.
     * @return True if the state exists in the list, false otherwise.
     */
    public boolean existsIn(List<State> states) {
        for (State state : states) {
            if (this.equals(state)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Generates a new state by moving the empty cell to a specified position.
     *
     * @param position The position to move the empty cell to.
     * @return The new state after the move.
     */
    private State getStateByMove(Position position) {
        State newState = null;
        Position emptyCellPosition = getEmptyCellPosition();
        if (position.isValid() && !position.equals(emptyCellPosition) && isValidPuzzle(this.data)) {
            int[][] tempData = new int[3][3];
            for (int i = 0; i < data.length; i++) {
                System.arraycopy(data[i], 0, tempData[i], 0, data[i].length);
            }
            tempData[emptyCellPosition.getX()][emptyCellPosition.getY()] = tempData[position.getX()][position.getY()];
            tempData[position.getX()][position.getY()] = 0;
            newState = new State(tempData);
        }
        return newState;
    }

    /**
     * Generates a list of child states reachable from the current state.
     *
     * @param existingStates The list of existing states to avoid duplicates.
     * @return The list of child states.
     */
    public List<State> getChildStates(List<State> existingStates) {
        List<State> childStates = new ArrayList<>();
        List<Position> possibleMovePositions = new ArrayList<>(getPossibleMovePositions());
        State tempState;
        for (Position position : possibleMovePositions) {
            tempState = getStateByMove(position);
            if (tempState != null && !tempState.existsIn(existingStates)) {
                childStates.add(tempState);
            }
        }
        if (!childStates.isEmpty()) {
            existingStates.addAll(childStates);
        }
        return childStates;
    }

    /**
     * Prints the current state to the console.
     */
    public void printState() {
        for (int i = 0; i < 3; i++) {
            // Loop through the columns within each row
            for (int j = 0; j < 3; j++) {
                System.out.print(this.data[i][j] + " ");
            }
            System.out.println(); // Move to the next line after each row
        }
    }
}
