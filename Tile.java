package lines.of.action;

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
    
    public Piece getPiece() {
        Piece copy = this.piece;
        this.piece = null;
        return copy;
    }
    
    public Piece lookAtPiece() {
        return this.piece;
    }
    
    public boolean setPiece(Piece newPiece) {
        this.piece = newPiece;
        return true;
    }
}
