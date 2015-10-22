package LinesOfAction;

/**
 *
 * @author andrewwelton
 */
public class Initial_Layout {
    Tile tiles[][];
    
    public Initial_Layout() {
        
    }
    
    public Tile[][] PopulateTiles() {
        // x, y
        Tile[][] tiles = new Tile[8][8];
        int p1count = 0;
        int p2count = 0;
        
        //i = x
        //j = y
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if((i == 0 || i == 7) && (j >= 1 && j <= 6)) {
                    char id = (char) ('a' + p1count);
                    Piece newPiece = new Piece(0, Character.toString(id));
                    Tile tile = new Tile(newPiece, true, false);
                    tiles[i][j] = tile;
                    p1count++;
                }else if((j == 0 || j == 7) && (i >= 1 && i <= 6)) {
                    char id = (char) ('a' + p2count);
                    Piece newPiece = new Piece(1, Character.toString(id));
                    Tile tile = new Tile(newPiece, true, false);
                    tiles[i][j] = tile;
                    p2count++;
                } else {
                    tiles[i][j] = new Tile(null, false, false);
                }
            }
        }
        return tiles;
    }
}