
package LinesOfAction;

/**
 *
 * @author Mark WIlson mwilso14
 */
public class Display {
    
    
    public void DisplayPieceSelectionOptions(Board board, int Player){
    
        char[] names = {'a','b','c','d','e','f','g','h','i'}; 
        
        Tile[][] myTiles; 
        myTiles = board.GetTiles();
        Piece currentPiece;
        Tile currentTile;
        int owner = 0;
        int name = 0;
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                
                currentTile = myTiles[x][y];
                currentPiece = currentTile.lookAtPiece();
                   
                if (currentTile.lookAtPiece() != null){
                    //found a Piece
                    owner = currentTile.lookAtPiece().GetOwner();
                            if(owner == Player){
                                System.out.print("("+currentTile.lookAtPiece().GetId()+")");
                                name++;
                            }else{
                                if(Player == 0){
                                    System.out.print(" x ");
                                }else{
                                    System.out.print(" o ");
                                }
                            }   
                }else{
                    System.out.print(" - ");
                }
            }
            System.out.print("\n");
	}

        
        
/*
        
-(a((b)(c)(d)(e)(f) -
O - - - - - - O
O - - - - - - O
O - - - - - - O
O - - - - - - O
O - - - - - - O
O - - - - - - O
-(g)(h)(i)(j)(k)(l) -

        */
        
        
//        Prints out the board from the current player’s perspective to give them their
//available pieces to select. The current player’s pieces are printed out as their
//identifier. Ex: If a piece had the identifier of A, it would appear as an A upon the
//board. If not current player, Player 1’s pieces are displayed as “O”, while Player
//2’s pieces would be displayed as “X”. Empty tiles are displayed as ‘-’.
        
        
    }

    public void DisplayMoveOptions(Board board , Piece SelectedPiece ){
    
        int destNum = 1;
        Piece currentPiece;
        Tile[][] myTiles; 
        myTiles = board.GetTiles();
        
        //these for loops populate a char array with the current board state
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                
                currentTile = myTiles[x][y];
                currentPiece = currentTile.lookAtPiece();
    
                if(currentTile.GetIsDestination == true){ // if its a destination
                    System.out.print("["+destNum+"]");
                    destNum++;
                    
                }else{ //not a destination
                    if(currentPiece!= null){// has a piece
                        if(currentPiece.GetID() == SelectedPiece.GetID()&& currentPiece.GetOwner() == SelectedPiece.GetOwner()){
                            System.out.print("("+SelectedPiece.GetOwner()+")");
                        }else{
                             System.out.print("("+currentPiece.GetOwner()+")");
                        }
                    }else{// no piece 
                        System.out.print(" - ");
                    }
                }

            }
            System.out.print("\n");
	}
        
        /* Prints the board from the current player’s perspective to give them the options of
where they can move their currently selected pieces. The available destinations
are displayed as a number, beginning with 1. Player 1’s pieces are displayed as
“O”, while Player 2’s pieces are displayed as “X”. The selected piece is displayed
as usual, except wrapped in parentheses. Empty tiles are displayed as ‘-’.*/
    }
    
    public void DisplayRules(){
        System.out.println(
"------------------------------------------------------------------------------\n"+
" ,----.     ,---.  ,--.   ,--.,------.    ,------. ,--. ,--.,--.   ,------. ,---.   \n" +
"'  .-./    /  O  \\ |   `.'   ||  .---'    |  .--. '|  | |  ||  |   |  .---''   .-'  \n" +
"|  | .---.|  .-.  ||  |'.'|  ||  `--,     |  '--'.'|  | |  ||  |   |  `--, `.  `-.  \n" +
"'  '--'  ||  | |  ||  |   |  ||  `---.    |  |\\  \\ '  '-'  '|  '--.|  `---..-'    | \n" +
" `------' `--' `--'`--'   `--'`------'    `--' '--' `-----' `-----'`------'`-----' "+
"------------------------------------------------------------------------------\n"+
"Board Setup\n"+
"------------------------------------------------------------------------------\n"+
"The board is a standard chess/checkers board. Each player begins with 12 \n"+
"pieces, with6 on either side of the board. For example, Player 1 would have \n"+
"6 pieces on the north side of the board, and 6 on the south side of the board.\n"+
"Player 2 would have 6 on the west and 6 on the east side. The players take\n "+
"turns making a move.\n"+
 "-----------------------------------------------------------------------------\n"+
"\nObjective\n"+
"------------------------------------------------------------------------------\n"+
"To win, a player must connect all of his or her pieces in one group. The first\n"+
"player to do so wins. A piece is considered connected if it is directly or \n"+
"diagonally adjacent to another piece. Additionally, a player automatically \n"+
"definition.\n"+
"------------------------------------------------------------------------------\n"+
"Movement\n"+

"Pieces can move in any direction. A player may move a piece to a tile that \n"+
"holds anopponent’s piece, and doing so ‘destroys’ the opponents piece. A player’s\n"+
"piece can jump over their own piece, but they cannot jump over an opponent’s \n"+
"piece. A player can move their piece to a tile in the distance according to the\n"+
"amount of pieces residing on that movement’s direction across the board \n"+
"(including the selected piece). A piece can be moved diagonally, and the same \n"+
"rule applies. For example, if a piece is being moved horizontally, and there\n"+
"is one additional piece in the line that the piece is moving upon,\n"+
"that piece can move two tiles.\n");
    }
    
    public void DisplayWin(String message){
        System.out.println("Winner!");
        System.out.println(message);
    }
    
    public void PromptReplay(){
        System.out.println("would you like to play again? Y (yes) N (no)");
    }
    public void PromptPiece(){
        System.out.print("Select a piece: ");
    }
    public void PromptDestination(){
        System.out.print("Select a destination: ");
    }
}
