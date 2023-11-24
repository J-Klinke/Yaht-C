package yahtc;

import java.util.ArrayList;

public abstract class IOHandler {

    /**
     * handles a player's boolean decision.
     * @return the decision
     */
    abstract boolean rollAgain();

    /**
     * allows a player to pick dice.
     * @param dice the Die[] to pick from
     * @return a Die[] of the picked dice
     */
    abstract Die[] dicePicker(Die[] dice);

    /**
     * lets the player choose a result.
     * @param results the ArrayList to choose from
     * @return the chosen result
     */
    abstract Result resultChooser(ArrayList<Result> results);


    abstract void printGameSheet(Player player);

    abstract void printRound(int round);

    abstract void printWinner(Player winner);

    abstract void printDice(ArrayList<Integer> dieValuesAsInt);

    abstract void printResults(ArrayList<Result> results);

    abstract void printEliminationMessage();

    abstract void rollDice();

    abstract void printBonusMessage();
}
