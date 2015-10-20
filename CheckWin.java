package lines.of.action;

/**
 *
 * @author Kashaan Ali
 */


public class CheckWin {

    // More rules can be added.
    public Rule[] rules = new Rule[3];

    public CheckWin() {
        rules[0] = new Rule(Rule.Checklist.movementRule);
        rules[1] = new Rule(Rule.Checklist.KillCondition);
        rules[2] = new Rule(Rule.Checklist.WinCondition);
    }

    // Rules can be changed here.
    public LinesOfAction.Message validateRules(Point origin, Point destination, Player activePlayer, Player inactivePlayer) {
        boolean isCapture = false;
        //Checker[] activeCheckers = activePlayer.getActiveCheckers();
        //Checker[] inactiveCheckers = inactivePlayer.getActiveCheckers();

        // Determine if a piece is capturing another piece or not.
        for (int i = 0; i < inactiveCheckers.length; i++) {
            if (origin.equals(inactiveCheckers[i].getPosition())) {
                isCapture = true;
                break;
            }
        }

        // Depending if a piece is capturing or not, determine if the move is valid
        if (isCapture) {
            if (!rule[1].validateRule(origin, destination, activePlayer, inactivePlayer))
                return LinesOfAction.Message.MOVEILLEGAL;

            // TODO: Capturing logic goes here.
        }
        else {
            if (!rule[0].validateRule(origin, destination, activePlayer, inactivePlayer))
                return LinesOfAction.Message.MOVEILLEGAL;
        }

        // If the move is valid, then determine if it is a winning move
        if (rule[2].validateRule(origin, destination, activePlayer, inactivePlayer)) {
            // Determine which player is moving 
            if (activePlayer.getPlayerID() == LinesOfAction.PlayerID.PLAYER1)
                return LinesOfAction.Message.PLAYER1WIN;
            else if (activePlayer.getPlayerID() == LinesOfAction.PlayerID.PLAYER2)
                return LinesOfAction.Message.PLAYER2WIN;
        }
        else {
            return LinesOfAction.Message.MOVELEGAL;
        }
    }
}
