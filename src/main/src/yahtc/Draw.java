package yahtc;

import java.util.ArrayList;

public class Draw {
    private static final int NUMBER_OF_DICE = 5;
    private final Die[] dice = new Die[NUMBER_OF_DICE];
    private final ArrayList<Integer> dieValuesAsInt = new ArrayList<>();
    private final IOHandler ioHandler;

    public Draw(IOHandler ioHandler) {
        for (int i = 0; i < dice.length; i++) {
            dice[i] = new Die();
        }
        this.ioHandler = ioHandler;
    }

    /**
     * Draw's 'main' method. Rolls the dice, gets the possible results.
     * @return ArrayList of possible results
     */
    public ArrayList<Result> doDraw() {
        rollDice();
        translateDiceValues();
        ArrayList<Result> results = getPossibleResults();
        ioHandler.printDice(dieValuesAsInt);
        ioHandler.printResults(results);
        return results;
    }

    /**
     * version of doDraw with th possibility to not roll all dice.
     * @param fixedDice Die[] with the dice which are not rolled
     * @return possible results
     */
    public ArrayList<Result> doFurtherDraws(Die[] fixedDice) {
        rollDice();
        replaceFixedDice(fixedDice);
        translateDiceValues();
        ArrayList<Result> results = getPossibleResults();
        ioHandler.printDice(dieValuesAsInt);
        ioHandler.printResults(results);
        return results;
    }

    /**
     * rolls all dice in this draw.
     */
    private void rollDice() {
        ioHandler.rollDice();
        for (Die die : dice) {
            die.rollDie();
        }
    }

    /**
     * fills Draw.dieValues with each die's valueAsInt.
     */
    private void translateDiceValues() {
        dieValuesAsInt.clear();
        for (Die die : dice) {
            dieValuesAsInt.add(die.getValueAsInt());
        }
    }

    /**
     * calls a ResultCalculator, fills Draw.results with the possible results.
     * @return ArrayList of possible results
     */
    private ArrayList<Result> getPossibleResults() {
        ResultCalculator resultCalculator = new ResultCalculator(dieValuesAsInt);
        resultCalculator.calculateResults();
        return resultCalculator.getResults();
    }

    /**
     * replaces those die in Die.dice, which are also in fixedDice.
     * @param fixedDice Die[] with the dice which are not rolled
     */
    private void replaceFixedDice(Die[] fixedDice) {
        for (int i = 0; i < dice.length; i++) {
            if (fixedDice[i] != null) {
                dice[i] = fixedDice[i];
            }
        }
    }

    public Die[] getDice() {
        return dice;
    }
}
