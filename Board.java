package LinesOfAction;

/*
 * @author Michelle MacKenzie
 */
public class Board {

    private Tile tiles[][];
    
    public Board() {}
    
    public void InitializeBoard() {
        
        //This will call PopulateTiles() from InitialLayout in order to create the tiles, which
        //will then be assigned to tiles[][].
        Initial_Layout layoutGenerator = new Initial_Layout();
        tiles = layoutGenerator.PopulateTiles();
    }
    
    public Piece GetPiece(String ID, int Player) {
        
        //Given the ID of a piece and its player, this function will loop through all tiles to 
        //search for a piece. It will then return that piece.        
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                
                Piece toCheck = tiles[x][y].LookAtPiece();
                
                if (toCheck != null && (toCheck.GetOwner() == Player && toCheck.GetID().equals(ID))) {
                    return toCheck;
                }
            }
        }
        return null;
    }
    
    public void SetAvailableMoves(int[] x, int[] y) {
        
        //Given sets of x and y coordinates, the board then updates its tiles to set the
        //specified ones as being possible destinations.
        for (int i = 0; i < 8; i ++) {
            for (int j = 0; j < 8; j++) {
                tiles[i][j].SetIsDestination(false);
            }
        }
        
        if (x != null) {
            for (int i = 0; i < x.length; i++) {
                tiles[x[i]][y[i]].SetIsDestination(true);
            }
        }
    }
    
    //this had to be changed from the design (and was discussed with the designers) in order to
    //be able to determine the coordinates for destination
    public void MovePiece(Piece piece, char destination) {
        
        int destIndex = Character.getNumericValue(destination);
        int count = 0;
        
        Piece toMove = null;
        int x=0, y=0;
        
        //Given a piece, the board will search its tiles in search for the piece, and once it is
        //found, the tile is cleared of that piece and marked as empty. The piece is then
        //insert into the destination tile, as marked by the coordinates from x and y.
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (tiles[i][j].GetIsDestination()) {
                    count++;
                    
                    if (count == destIndex) {
                        x=i;
                        y=j;
                    }
                }
                
                Piece toCheck = tiles[i][j].LookAtPiece();
                
                if (toCheck != null && (toCheck.GetOwner() == piece.GetOwner() && toCheck.GetID().equals(piece.GetID()))) {
                    toMove = tiles[i][j].GetPiece();
                }
                
                if (count >= destIndex && toMove != null) break;
            }
            if (count >= destIndex && toMove != null) break;
        }
        
        tiles[x][y].SetPiece(toMove);
    }
    
    //this had to be added to the design in order to be able to display the board
    public Tile[][] GetTiles() {
        return tiles;
    }
}