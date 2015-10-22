package LinesOfAction;

/**
 *
 * @author Kashaan Ali
 * @author Michelle MacKenzie
 * @author Andrew Welton
 */

import java.util.ArrayList;
import java.awt.Point;

public class Rules {

    public Rules() {
    }
    
    
    //@author Michelle MacKenzie
    public void FindValidMove(Board board, Piece piece) {
        //x = [0][0], y = [1][0]
        // 0 = horizontal - 1 = down/right - 2 = vertical - 3 = down/left
        
        int xCoord = -1, yCoord = -1;
        boolean found = false;
        Tile[][] tiles = board.GetTiles();
        
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                
                Piece toCheck = tiles[x][y].LookAtPiece();
                
                if (toCheck != null && (toCheck.GetOwner() == piece.GetOwner() && toCheck.GetID().equals(piece.GetID()))) {
                    //TODO
                    //due to unknown reasons, we need to flip x and y
                    xCoord = y;
                    yCoord = x;
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
        
        board.SetAvailableMoves(yDestinations, xDestinations);
    }
    

    /*andrewwelton*/
    public boolean CheckBlocked(Board board, Piece piece, int[][]dest) {
        int destX = dest[0][0];
        int destY = dest[1][0];
        
        boolean blocked = false;
        
        Tile[][] boardTiles = board.GetTiles();
        
        Piece destinationPiece = boardTiles[destY][destX].LookAtPiece();
        if (destinationPiece != null && destinationPiece.GetOwner() == piece.GetOwner()) return true;
        
        int srcX = 0;
        int srcY = 0;
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                Piece tilePiece = boardTiles[i][j].LookAtPiece();
                if(tilePiece != null && (tilePiece.GetID().equals(piece.GetID()) && tilePiece.GetOwner() == piece.GetOwner())) {
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
                    if(tilePiece != null && (tilePiece.GetOwner() != piece.GetOwner())) {
                        blocked = true;
                        break;
                    }
                }
            } else { //moving up the board
               for(int i = srcY; i >= destY; i--) {
                   Piece tilePiece = boardTiles[srcX][i].LookAtPiece();
                   if(tilePiece != null && (tilePiece.GetOwner() != piece.GetOwner())) {
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
                    if(tilePiece != null && (tilePiece.GetOwner() != piece.GetOwner())) {
                        blocked = true;
                        break;
                    }
                }
            } else { //moving up the board
                for(int i = srcX; i >= destX; i--) {
                    Piece tilePiece = boardTiles[i][srcY].LookAtPiece();
                    if(tilePiece != null && (tilePiece.GetOwner() != piece.GetOwner())) {
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
                    if(tilePiece != null && (tilePiece.GetOwner() != piece.GetOwner())) {
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
                    if(tilePiece != null && (tilePiece.GetOwner() != piece.GetOwner())) {
                        blocked = true;
                        break;
                    }
                    counter++;
                }
            } else if (srcX < destX && srcY < destY) { //down-right movement
                int counter = 1;
                for(int i = srcX + 1; i < destX; i++) {
                    if(srcY + counter > 7) { 
                        break;
                    }
                    Piece tilePiece = boardTiles[i][srcY + counter].LookAtPiece();
                    if(tilePiece != null && (tilePiece.GetOwner() != piece.GetOwner())) {
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
                    if(tilePiece != null && (tilePiece.GetOwner() != piece.GetOwner())) {
                        blocked = true;
                        break;
                    }
                    counter++;
                }
            }
        }
        return blocked;
    }

    /**
     * @author Kashaan
     * @author Michelle MacKenzie
     */
    public int CheckPiecesInLine(Board board, Piece piece, int dir) {
        int x = 0, y = 0, count = 0;
        Tile tiles[][] = board.GetTiles();
        boolean isFound = false;

        //find piece destination
        for (int i = 0; i < 8; i++) {
            if (isFound) break;
            for (int j = 0; j < 8; j++) {
                Piece toCheck = tiles[i][j].LookAtPiece();
                if (toCheck != null && (toCheck.GetOwner() == piece.GetOwner() && toCheck.GetID().equals(piece.GetID()))) {
                    x = i;
                    y = j;
                    isFound = true;
                    break;
                }
            }
        }
 
        if (dir == 0) { //horizontal
            
            for (int j = 0; j < 8; j++) {
                if (tiles[x][j].LookAtPiece() != null) count++;
            }
            
        } else if (dir == 1) { //diagonally-right
            
            int xStart = x, yStart = y;
            
            while (xStart > 0 && yStart > 0) {
                xStart--;
                yStart--;
            }
            
            while (xStart <= 7 && yStart <= 7) {
                if (tiles[xStart][yStart].LookAtPiece() != null) count++;
                xStart++;
                yStart++;
            }
            
        } else if (dir == 2) { //vertically
            
            for (int j = 0; j < 8; j++) {
                if (tiles[j][y].LookAtPiece() != null) count++;
            }
            
        } else if (dir == 3) { //diagonally-left
            
            int xStart = x, yStart = y;
            
            while (xStart > 0 && yStart < 7) {
                xStart--;
                yStart++;
            }
            
            while (xStart <= 7 && yStart >= 0) {
                if (tiles[xStart][yStart].LookAtPiece() != null) count++;
                xStart++;
                yStart--;
            }
        }
        
        return count;
    }

    /**
     * @author Michelle MacKenzie
     */
    public boolean CheckWin(Board board) {
        
        Tile tiles[][] = board.GetTiles();
        Piece toCheck;
        ArrayList<Point> player1all = new ArrayList<Point>();
        ArrayList<Point> player2all = new ArrayList<Point>();
        ArrayList<Point> player1block = new ArrayList<Point>();
        ArrayList<Point> player2block = new ArrayList<Point>();
        boolean canContinue = true;
        
        //get total number of pieces for each player
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                
                toCheck = tiles[x][y].LookAtPiece();
                if (toCheck != null) {
                    if (toCheck.GetOwner() == 0) {
                        Point pt = new Point(x, y);
                        player1all.add(pt);
                    } else {
                        Point pt = new Point(x, y);
                        player2all.add(pt);
                    }
                }
            }
        }
        
        //get block of touching pieces for player 1
        player1block.add(player1all.remove(0));
        while (!player1all.isEmpty() && canContinue) {
            
            int startSize = player1all.size();
            
            for (int i = startSize - 1; i >= 0; i--) {
                
                Point toTest = player1all.get(i);
                
                for (int j = 0; j < player1block.size(); j++) {
                    
                    if (toTest.distance(player1block.get(j)) < 2) {
                        player1block.add(player1all.remove(i));
                        break;
                    }
                }
            }
            
            if (player1all.size() == startSize) { //nothing else was added to the block
                canContinue = false;
            }
        }
        if (player1all.isEmpty()) return true;
        
        
        canContinue = true;
        //get block of touching pieces for player 2
        player2block.add(player2all.remove(0));
        while (!player2all.isEmpty() && canContinue) {
            
            int startSize = player2all.size();
            
            for (int i = startSize - 1; i >= 0; i--) {
                
                Point toTest = player2all.get(i);
                
                for (int j = 0; j < player2block.size(); j++) {
                    
                    if (toTest.distance(player2block.get(j)) < 2) {
                        player2block.add(player2all.remove(i));
                        break;
                    }
                }
            }
            
            if (player2all.size() == startSize) { //nothing else was added to the block
                canContinue = false;
            }
        }
        if (player2all.isEmpty()) return true;
        
        return false;
    }
}