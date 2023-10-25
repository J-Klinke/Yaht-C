package yahtc;

import java.util.ArrayList;

public interface IOHandler {

    /**
     * handles a player's boolean decision.
     * @return the decision
     */
    boolean rollAgain();

    /**
     * allows a player to pick dice.
     * @param dice the Die[] to pick from
     * @return a Die[] of the picked dice
     */
    Die[] dicePicker(Die[] dice);

    /**
     * lets the player choose a result.
     * @param results the ArrayList to choose from
     * @return the chosen result
     */
    Result resultChooser(ArrayList<Result> results);


    void printGameSheet(Player player);

    void printRound(int round);

    void printWinner(Player winner);

    void printDice(ArrayList<Integer> dieValuesAsInt);

    void printResults(ArrayList<Result> results);

    void printEliminationMessage();

    void rollDice();
}
