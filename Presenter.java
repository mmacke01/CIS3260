package lines.of.action;

public class Presenter {

    private int activePlayer;
    private Piece selectedPiece;
    
    public Presenter(int turn, Piece selectedPiece) {
        activePlayer = turn;
        this.selectedPiece = selectedPiece;
    }
    
    void main() {
        
        /*Contains the control loop through which the game is played. The loop will run
        until the win condition is met, or the player chooses to exit the game. Each 
        iteration of the loop is a turn. The loop will run as follows:
        - Call to GetInput() to get which piece the player is selecting.
        - Call to FindValidMoves() to find all valid moves the player can make with the piece and update that on the board.
        - Call to GetInput to find which tile the player wishes to move his or her piece to.
        - Call to MovePiece() to move the selected piece to the selected destination.
        - Call to CheckWin() to check if the player has won the game with that move.
        - Toggle player
        - Display updated board from new player’s perspective.
         */
        
        boolean hasWon = false;
        
        while (!hasWon) {
            
        }
    }
}
