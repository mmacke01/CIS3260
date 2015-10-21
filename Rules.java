package LinesOfAction;

/**
 *
 * @author Kashaan Ali
 */

import java.lang.Math;
import java.util.ArrayList;

public class Rules {

    public Rules() {
    }
    
    
    //@author Michelle MacKenzie
    public void FindValidMoves(Board board, Piece piece) {
        //x = [0][0], y = [1][0]
        // 0 = horizontal - 1 = down/right - 2 = vertical - 3 = down/left
        
        int xCoord = -1, yCoord = -1;
        boolean found = false;
        Tile[][] tiles = board.GetTiles();
        
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                
                Piece toCheck = tiles[x][y].LookAtPiece();
                
                if (toCheck.GetOwner() == piece.GetOwner() && toCheck.GetID().equals(piece.GetID())) {
                    xCoord = x;
                    yCoord = y;
                    found = true;
                    break;
                }
            }
            if (found) break;
        }
        
        ArrayList<Integer> xDests = new ArrayList<Integer>();
        ArrayList<Integer> yDests = new ArrayList<Integer>();
        
        //horizontal+right, down/right+down, vertical+down, down/left+down
        //horizontal+left, down/right+up, vertical+up, down/left+up
        for (int i = 0; i < 8; i++) {
            
            int dir = i % 4;
            int nSpaces = CheckPiecesInLine(board, piece, dir);
            int[][] destination = new int[2][1];
            int newX = -1, newY = -1;
            
            switch(i) {
                case 0:
                    newX = xCoord + nSpaces;
                    newY = yCoord;
                    break;
                case 1:
                    newX = xCoord + nSpaces;
                    newY = yCoord + nSpaces;
                    break;
                case 2:
                    newX = xCoord;
                    newY = yCoord + nSpaces;
                    break;
                case 3:
                    newX = xCoord - nSpaces;
                    newY = yCoord + nSpaces;
                    break;
                case 4:
                    newX = xCoord - nSpaces;
                    newY = yCoord;
                    break;
                case 5:
                    newX = xCoord - nSpaces;
                    newY = yCoord - nSpaces;
                    break;
                case 6:
                    newX = xCoord;
                    newY = yCoord - nSpaces;
                    break;
                case 7:
                    newX = xCoord + nSpaces;
                    newY = yCoord - nSpaces;
                    break;
            }
            
            boolean isOnBoard = (newX >= 0 && newX < 8) && (newY >= 0 && newY < 8);
            
            if (isOnBoard) {
                
                destination[0][0] = newX;
                destination[1][0] = newY;
                
                boolean isBlocked = CheckBlocked(board, piece, destination);
                
                if (!isBlocked) {
                    xDests.add(newX);
                    yDests.add(newY);
                }
            }
        }
        
        int[] xDestinations = new int[xDests.size()];
        int[] yDestinations = new int[yDests.size()];
        for (int i=0; i < xDestinations.length; i++)
        {
            xDestinations[i] = xDests.get(i).intValue();
            yDestinations[i] = yDests.get(i).intValue();
        }
        
        board.SetAvailableMoves(xDestinations, yDestinations);
    }
    

    /*andrewwelton*/
    public boolean CheckBlocked(Board board, Piece piece, int[][]dest) {
        int destX = dest[0][0];
        int destY = dest[1][0];
        
        boolean blocked = false;
        
        Tile[][] boardTiles = board.GetTiles();
        
        int srcX = 0;
        int srcY = 0;
        for(int i = 0; i < 7; i++) {
            for(int j = 0; j < 7; j++) {
                Piece tilePiece = boardTiles[i][j].LookAtPiece();
                if(tilePiece.GetID() == piece.GetID() && tilePiece.GetOwner() == piece.GetOwner()) {
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