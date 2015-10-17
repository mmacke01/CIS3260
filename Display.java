
package lines.of.action;

/**
 *
 * @author Mark WIlson mwilso14
 */
public class Display {
    
    /*
    public void DisplayPieceSelectionOptions(Board, Player){
    
        Prints out the board from the current player’s perspective to give them their
available pieces to select. The current player’s pieces are printed out as their
identifier. Ex: If a piece had the identifier of A, it would appear as an A upon the
board. If not current player, Player 1’s pieces are displayed as “O”, while Player
2’s pieces would be displayed as “X”. Empty tiles are displayed as ‘-’.
    }
    public void DisplayMoveOptions(Board, SelectedPiece){
    
         Prints the board from the current player’s perspective to give them the options of
where they can move their currently selected pieces. The available destinations
are displayed as a number, beginning with 1. Player 1’s pieces are displayed as
“O”, while Player 2’s pieces are displayed as “X”. The selected piece is displayed
as usual, except wrapped in parentheses. Empty tiles are displayed as ‘-’.
    }*/
    
    public void DisplayRules(){
        System.out.println(" the rules of the game:"
                + "don't let THE MAN get you down");
    }
    
    public void DisplayWin(String message){
        System.out.println("Winner!");
        System.out.println(message);
    }
    
    public void PromptReplay(){
        System.out.println("would you like to play again? Y (yes) N (no)");
    }
    public void PromptPiece(){
        System.out.print("Select a peice: ");
    }
    public void PromptDestination(){
        System.out.print("Select a destination: ");
    }
}
