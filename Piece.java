package LinesOfAction;

/**
 *
 * @author kashaan
 * @author Andrew Welton
 */
public class Piece {
    int owner;
    String id;
    
    /*andrew welton*/
    public Piece(int owner, String id) {
        this.owner = owner;
        this.id = id;
    }
    
    public int GetOwner() {
        return this.owner;
    }
    
    /*andrew welton
    not in the original design*/
    public String GetID() {
        return this.id;
    }
    
}