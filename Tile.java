package LinesOfAction;

/**
 *
 * @author andrewwelton
 */
public class Tile {
    Piece piece;
    boolean isOccupied;
    boolean isDestination;
       
    public Tile(Piece piece, boolean isOccupied, boolean isDestination) {
        this.piece = piece;
        this.isOccupied = isOccupied;
        this.isDestination = isDestination;
    }
    
    public Piece GetPiece() {
        Piece copy = this.piece;
        this.piece = null;
        return copy;
    }
    
    public Piece LookAtPiece() {
        return this.piece;
    }
    
    public boolean SetPiece(Piece newPiece) {
        this.piece = newPiece;
        return true;
    }
    
    /*new from the original design*/
    public boolean GetIsDestination() {
        return this.isDestination;
    }
    
    /*new from the original design*/
    public void SetIsDestination(boolean newDestination) {
        this.isDestination = newDestination;
    }
}
