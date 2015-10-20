package lines.of.action;

/**
 *
 * @author Kashaan Ali
 */

import java.lang.Math;

public class Rules {

 	public Rules() {
    }
    
    
    
    public void FindValidMoves(Board, Piece) {
        
    }
    

    // Ruling bulk goes here
    public boolean CheckWin(Point origin, Point destination, Player activePlayer, Player inactivePlayer) {
    	int x_Old = origin.getX();
   		int y_Old = origin.getY();
    	int x_New = destination.getX();
    	int y_New = destination.getY();
    	//Checker[] activeCheckers = activePlayer.getActiveCheckers();
    	//Checker[] inactiveCheckers = inactivePlayer.getActiveCheckers();
     

    	// Check if the piece is going to move to a valid spot
    	else if (ruleType == FindValidMoves.movementRule || ruleType == FindValidMoves.KillCondition) {
    		int numOfSpaces;
    			
    		// If the move is vertical
    		if ((x_Old == x_New) && (y_Old != y_New)) {
    			numOfSpaces = 0;
    			// Count Horizontal Pieces
    			for (int i = 0; i < activeCheckers.length; i++) {
    				if (x_Old == activeCheckers[i].getPosition().getX())
    					numOfSpaces++;
    			}
    			for (int i = 0; i < inactiveCheckers.length; i++) {
    				if (x_Old == inactiveCheckers[i].getPosition().getX())
    					numOfSpaces++;
    			}
    			if (numOfSpaces == Math.abs(y_Old - y_New))
    				return true;
    		}

    		// If the move is horizontal
    		if ((x_Old != x_New) && (y_Old == y_New)) {
    			numOfSpaces = 0;
    			// Count Vertical Pieces
    			for (int i = 0; i < activeCheckers.length; i++) {
    				if (y_Old == activeCheckers[i].getPosition().getY())
    					numOfSpaces++;
    				}
    			for (int i = 0; i < inactiveCheckers.length; i++) {
    				if (y_Old == inactiveCheckers[i].getPosition().getY())
    					numOfSpaces++;
    				}
    			if (numOfSpaces == Math.abs(x_Old - x_New))
    				return true;
    		}

    		if ((x_Old == x_New) && (y_Old == y_New)) {
    			return false;
    		}

    		// If the move was diagonal 
    		if ((x_Old - y_Old) == (x_New - y_New)) {
				numOfSpaces = 0;
	    		// Count Diagonal (SW to NE) Pieces
    			for (int i = 0; i < activeCheckers.length; i++) {
    				if ((x_Old - y_Old) == (activeCheckers[i].getPosition().getX() - activeCheckers[i].getPosition().getY()))
    					numOfSpaces++;
    			}
    			for (int i = 0; i < inactiveCheckers.length; i++) {
    				if ((x_Old - y_Old) == (inactiveCheckers[i].getPosition().getX() - inactiveCheckers[i].getPosition().getY()))
    					numOfSpaces++;
    			}
    			System.out.print(" > " + numOfSpaces);
    			if ((numOfSpaces == Math.abs(x_Old - x_New)) && (numOfSpaces == Math.abs(y_Old - y_New)))
    				return true;
    		}

			if ((x_Old + y_Old) == (x_New + y_New)) {
				numOfSpaces = 0;
	    		// Count Diagonal (NW to SE) Pieces
   		 		for (int i = 0; i < activeCheckers.length; i++) {
	    			if ((x_Old + y_Old) == (activeCheckers[i].getPosition().getX() + activeCheckers[i].getPosition().getY()))
	    				numOfSpaces++;
	    		}
	    		for (int i = 0; i < inactiveCheckers.length; i++) {
	    			if ((x_Old + y_Old) == (inactiveCheckers[i].getPosition().getX() + inactiveCheckers[i].getPosition().getY()))
	    				numOfSpaces++;
	    		}
    			System.out.print(" > " + numOfSpaces);
    			if ((numOfSpaces == Math.abs(x_Old - x_New)) && (numOfSpaces == Math.abs(y_Old - y_New)))
    				return true;
			}
    		return false;
    	}
        // Different approach when rule is checking the win condition
    	else if (ruleType = FindValidMoves.WinCondition) {
            return false;
        }
        return false;
    }
}
