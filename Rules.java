package LinesOfAction;

/**
 *
 * @author Kashaan Ali
 */

import java.lang.Math;

public class Rules {

 	public Rules() {
    }
    
    
    //@author Michelle MacKenzie
    public void FindValidMoves(Board board, Piece piece) {
        //x = [0][0], y = [1][0]
        // 0 = horizontal - 1 = down/right - 2 = vertical - 3 = down/left
        
        int xCoord=0, yCoord=0;
        boolean found=false;
        
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                
                Piece toCheck = tiles[x][y].LookAtPiece();
                
                if (toCheck.GetOwner() == Player && toCheck.GetID().equals(ID)) {
                    xCoord = x;
                    yCoord = y;
                    found = true;
                    break;
                }
            }
            if (found) break;
        }
        
        int[] xDests;
        int[] yDests;
        
        //horizontal+right, down/right+down, vertical+down, down/left+down
        //horizontal+left, down/right+up, vertical+up, down/left+up
        for (int i = 0; i < 8; i++) {
            
            int dir = i % 4;
            int nSpaces = CheckPiecesInLine(board, piece, dir);
            int[1][1] destination = [0][0];
            boolean isOnBoard = true;
            
            switch(i) {
                case 0:
                    //
                    break;
                case 1:
                    //
                    break;
                case 2:
                    //
                    break;
                case 3:
                    //
                    break;
                case 4:
                    //
                    break;
                case 5:
                    //
                    break;
                case 6:
                    //
                    break;
                case 7:
                    //
                    break;
            }
            
            bool isBlocked = CheckBlocked(board, piece, destination);
            
            if (isOnBoard && !isBlocked) {
                //add to xDests/yDests
            }
        }
        
        board.SetAvailableMoves(xDests, yDests);
    }
    

    /*andrewwelton*/
    public boolean CheckBlocked(Board board, Piece piece, int[][]dest) {
        int destX = dest[0][0];
        int destY = dest[1][0];
        
        boolean blocked = false;
        
        Tile[][] boardTiles = board.getTiles();
        
        int srcX = 0;
        int srcY = 0;
        for(int i = 0; i < 7; i++) {
            for(int j = 0; j < 7; j++) {
                Piece tilePiece = boardTiles[i][j].LookAtPiece();
                if(tilePiece.GetId() == piece.GetId() && tilePiece.GetOwner() == piece.GetOwner()) {
                    srcX = i;
                    srcY = j;
                }
            }
        }
        //Vertical only
        if(srcX == destX) {
            //moving down the board
            if(srcY < destY) {
                for(int i = srcY; i < destY; i++) {
                    Piece tilePiece = boardTiles[srcX][i].LookAtPiece();
                    if(tilePiece.GetOwner() != piece.GetOwner()) {
                        blocked = true;
                        break;
                    }
                }
            } else { //moving up the board
               for(int i = srcY; i >= destY; i--) {
                   Piece tilePiece = boardTiles[srcX][i].LookAtPiece();
                   if(tilePiece.GetOwner() != piece.GetOwner()) {
                       blocked = true;
                       break;
                   }
               } 
            }
        } else if (srcY == destY) { //Horizontal only
            //moving down the board
            if(srcX < destX) {
                for(int i = srcX; i < destX; i++) {
                    Piece tilePiece = boardTiles[i][srcY].LookAtPiece();
                    if(tilePiece.GetOwner() != piece.GetOwner()) {
                        blocked = true;
                        break;
                    }
                }
            } else { //moving up the board
                for(int i = srcX; i >= destX; i--) {
                    Piece tilePiece = boardTiles[i][srcY].LookAtPiece();
                    if(tilePiece.GetOwner() != piece.GetOwner()) {
                        blocked = true;
                        break;
                    }
                }
            }
        } else { //MAGIC
            //up-right movement
            if(srcX < destX && srcY > destY) {
                int counter = 1;
                for(int i = srcX + 1; i < destX; i++) {
                    if(srcY - counter < 0) { 
                        break;
                    }
                    Piece tilePiece = boardTiles[i][srcY - counter].LookAtPiece();
                    if(tilePiece.GetOwner() != piece.GetOwner()) {
                        blocked = true;
                        break;
                    }
                    counter ++;
                }
            } else if (srcX > destX && srcY > destY) { //up-left movement
                int counter = 1;
                for(int i = srcX - 1; i >= destX; i--) {
                    if(srcY - counter < 0) { 
                        break;
                    }
                    Piece tilePiece = boardTiles[i][srcY - counter].LookAtPiece();
                    if(tilePiece.GetOwner() != piece.GetOwner()) {
                        blocked = true;
                        break;
                    }
                }
            } else if (srcX < destX && srcY < destY) { //down-right movement
                int counter = 1;
                for(int i = srcX + 1; i < destX; i++) {
                    if(srcY + counter > 7) { 
                        break;
                    }
                    Piece tilePiece = boardTiles[i][srcY + counter].LookAtPiece();
                    if(tilePiece.GetOwner() != piece.GetOwner()) {
                        blocked = true;
                        break;
                    }
                    counter ++;
                }
            } else if (srcX > destX && srcY > destY) {
                int counter = 1;
                for(int i = srcX - 1; i >= destX; i--) {
                    if(srcY + counter > 7) {
                        break;
                    }
                    Piece tilePiece = boardTiles[i][srcY + counter].LookAtPiece();
                    if(tilePiece.GetOwner() != piece.GetOwner()) {
                        blocked = true;
                        break;
                    }
                }
            }
        }
        return blocked;
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
