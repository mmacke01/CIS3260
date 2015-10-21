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
    public void FindValidMove(Board board, Piece piece) {
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
                if(tilePiece.GetID().equals(piece.GetID()) && tilePiece.GetOwner() == piece.GetOwner()) {
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
                    counter++;
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
                    counter++;
                }
            }
        }
        return blocked;
    }

  //kashaanAli
    public int CheckPiecesInLine(Board board, Piece piece, int dir) {
        int x = 0, y = 0, count = 0;
        Tile tiles[][] = board.GetTiles();
        boolean isfound = false;

        //find piece destination
        for (int i = 0; i < 8; i++) {
            if (isfound) break;
            for (int j = 0; j < 8; j++) {
                Piece toCheck = tiles[i][j].LookAtPiece();
                if (toCheck.GetOwner() == piece.GetOwner() && toCheck.GetID().equals(piece.GetID())) {
                    x = i;
                    y = j;
                    isfound = true;
                    break;
                }
            }
        }

        if (dir == 0) { //horizontally
            for (int j = 0; j < 8; j++) {
                if (tiles[x][j].LookAtPiece() != null) count++;
            }
            return count;
        }
        if (dir == 1) {//vertically
            for (int j = 0; j < 8; j++) {
                if (tiles[j][y].LookAtPiece() != null) count++;
            }
            return count;
        }

        if (dir == 2) {//diagonally-left
            int tmpX = x - Math.min(x, y);
            int tmpY = y - Math.min(x, y);
            while (tmpX < 8 && tmpY < 8) {
                if (tiles[tmpX][tmpY].LookAtPiece() != null) count++;
                tmpX++;
                tmpY++;
            }
            return count;
        }

        if (dir == 3) {//diagonally-right
            int tmpX = Math.min(8, x + y);
            int tmpY = Math.abs(y - tmpX + x);
            while (tmpX > -1 && tmpY < 8) {
                if (tiles[tmpX][tmpY].LookAtPiece() != null) count++;
                tmpX++;
                tmpY++;
            }
            return count;
        }
    }

    public boolean CheckWin(Board board) {
        boolean win = false;
        win = CheckWinPlayer(board,1);
        if(win) return true;
        win = CheckWinPlayer(board,2);
        if(win) return true;
        return false;
    }

    public boolean CheckWinPlayer(Board board, int player) {
        Tile tiles[][] = board.GetTiles();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (tiles[i][j].LookAtPiece().GetOwner() == player) {
                    if(!connected(i,j,player)) return false;
                }
            }
        }
        return true;
    }

    public boolean connected(int i, int j, int player) {
        int index = 0;
        boolean[] connect = new boolean[8];
        connect[index] = checkTile(i - 1, j - 1);
        index++;
        connect[index] = checkTile(i - 1, j);
        index++;
        connect[index] = checkTile(i - 1, j + 1);
        index++;
        connect[index] = checkTile(i, j - 1);
        index++;
        connect[index] = checkTile(i, j + 1);
        index++;
        connect[index] = checkTile(i + 1, j - 1);
        index++;
        connect[index] = checkTile(i + 1, j1);
        index++;
        connect[index] = checkTile(i + 1, j + 1);
        index++;
        for (int t = 0; t < 8; t++) {
            if (connect[t]) return true;
        }
        return false;
    }

    public boolean checkTile(int i, int j, int player, Piece piece) {
        if (insideArr(i - 1) && insideArr(j - 1)) {
            return piece.GetOwner() == player;
        }
        return false;
    }

    public boolean insideArr(int tmp) {
        return (tmp > 0 && tmp < 8);
    }
}