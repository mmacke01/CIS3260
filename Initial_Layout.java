package lines.of.action;

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
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(j == 1 || j == 6) {
                    Piece newPiece = new Piece(0, "O");
                    Tile tile = new Tile(newPiece, true, false);
                    tiles[i][j] = tile;
                }else if(i == 1 || i == 6) {
                    Piece newPiece = new Piece(1, "X");
                    Tile tile = new Tile(newPiece, true, false);
                    tiles[i][j] = tile;
                } else {
                    tiles[i][j] = new Tile(null, false, false);
                }
            }
        }
        return tiles;
    }
}
