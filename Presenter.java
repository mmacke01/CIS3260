package lines.of.action;

/*
 * @author Michelle MacKenzie
 */
public class Presenter {

    private int activePlayer;
    private Piece selectedPiece;
    
    public Presenter(int turn, Piece selectedPiece) {
        activePlayer = turn;
        this.selectedPiece = selectedPiece;
    }
    
    void main() {
        
        Board board = new Board();
        Display display = new Display();
        Input input = new Input();
        Rules rules = new Rules();
        
        //Contains the control loop through which the game is played. The loop will run
        //until the win condition is met, or the player chooses to exit the game.
        
        boolean hasWon = false;
        activePlayer = 0;
        
        while (!hasWon) {
            //display board with current player's pieces
            display.DisplayPieceSelectionOptions(board, activePlayer);
            
            //prompt player to choose a piece
            display.PromptPiece();
            char pieceID = input.GetInput();
            
            //get chosen piece
            selectedPiece = board.GetPiece(pieceID, activePlayer);
            
            while (toMove == null) {
                display.PromptPiece();
                char pieceID = input.GetInput();
                selectedPiece = board.GetPiece(pieceID, activePlayer);
            }
            
            //find valid moves
            rules.FindValidMove(board, selectedPiece);
            
            //display board with valid moves
            display.DisplayMoveOptions(board, selectedPiece);
            
            //prompt player to choose a move
            display.PromptDestination();
            char desination = input.GetInput();
            
            //TODO any validation possible?
            
            //move piece (including capture if possible)
            board.MovePiece(selectedPiece, destination);
            
            //check for win condition
            hasWon = rules.CheckWin(board);
            
            //toogle player
            activePlayer = (activePlayer + 1) % 2;
            board.SetAvailableMoves(null, null);
        }
    }
}
