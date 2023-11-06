package puzzle8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class State {
    private int[][] data=null;

    public State(int[][] data) {
        if (isValidPuzzle(data)) {
            this.data = data;
        } else {
            throw new IllegalArgumentException("Invalid puzzle data.");
        }
    }

    // Method to check if the puzzle data is valid
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

    private int[][] getData() {
        return data;
    }
    private Position getVideCaePosition(){//Ok
        Position position=new Position(-1,-1);
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
    private List<Position> getPossibleMovePosition(){//OK
        List<Position> possibleMovePositions=new ArrayList<Position>();
        Position videCasePosition=getVideCaePosition();
        Position temporelPosition;
        if(videCasePosition.isValid()){
            temporelPosition=new Position(videCasePosition.getX() -1,videCasePosition.getY());//Left Move
            if(temporelPosition.isValid()) possibleMovePositions.add(temporelPosition);
            temporelPosition=new Position(videCasePosition.getX() +1,videCasePosition.getY());//Right Move
            if(temporelPosition.isValid()) possibleMovePositions.add(temporelPosition);
            temporelPosition=new Position(videCasePosition.getX() ,videCasePosition.getY() -1);//Buttom Move
            if(temporelPosition.isValid()) possibleMovePositions.add(temporelPosition);
            temporelPosition=new Position(videCasePosition.getX(),videCasePosition.getY() +1);//Top Move
            if(temporelPosition.isValid()) possibleMovePositions.add(temporelPosition);
        }
        return possibleMovePositions;
    }

    @Override
    public boolean equals(Object o) {//Ok
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if (this.data[i][j]!=state.data[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(data);
    }

    public boolean existIn(List<State> states){//Ok
        for (State state:states) {
            if(this.equals(state)){
                return true;
            }
        }
        return false;
    }
    private State getStateByMove(Position position){//Ok
        State newState=null;
        Position videCasePosition=getVideCaePosition();
        if(position.isValid() && !position.equals(videCasePosition)&& isValidPuzzle(this.data)){
            int[][] tomporelData=new int[3][3];
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[i].length; j++) {
                    tomporelData[i][j] = data[i][j];
                }
            }
            tomporelData[videCasePosition.getX()][videCasePosition.getY()]=tomporelData[position.getX()][position.getY()];
            tomporelData[position.getX()][position.getY()]=0;
            newState=new State(tomporelData);
        }
        return newState;
    }
    public List<State> getChildStates(List<State> existStates){//Ok
        List<State> childStates=new ArrayList<State>();
        List<Position> possibleMovePositions=new ArrayList<Position>(getPossibleMovePosition());
        State tomporelState=null;
        for (Position position:possibleMovePositions) {
            tomporelState=getStateByMove(position);
            if(tomporelState!=null && !tomporelState.existIn(existStates)){
                childStates.add(tomporelState);
            }
        }
        if(!childStates.isEmpty()){
            existStates.addAll(childStates);
        }
        return childStates;
    }

    public void printState(){
        for (int i = 0; i < 3; i++) {
            // Loop through the columns within each row
            for (int j = 0; j < 3; j++) {
                System.out.print(this.data[i][j] + " ");
            }
            System.out.println(); // Move to the next line after each row
        }
    }
}

