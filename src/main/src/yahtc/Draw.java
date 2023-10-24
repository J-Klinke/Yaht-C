package yahtc;

import java.util.ArrayList;

public class Draw {
    private static final int NUMBER_OF_DICE = 5;
    private final Die[] dice = new Die[NUMBER_OF_DICE];
    private final ArrayList<Integer> dieValuesAsInt = new ArrayList<>();
    private ArrayList<Result> results = new ArrayList<>();

    public Draw() {
        for (int i = 0; i < dice.length; i++) {
            dice[i] = new Die();
        }
    }

    /**
     * Draw's 'main' method.
     * Rolls the dice, gets the possible results and prints both.
     */
    public ArrayList<Result> doDraw() {
        rollDice();
        translateDiceValues();
        ArrayList<Result> results = getPossibleResults();
        //System.out.println(dieValuesAsInt);
        //System.out.println(printResults());
        return results;
    }

    /**
     * fills Draw.dieValues with each die's valueAsInt.
     */
    private void translateDiceValues() {
        for (Die die : dice) {
            dieValuesAsInt.add(die.getValueAsInt());
        }
    }

    /**
     * calls a ResultCalculator, fills Draw.results with the possible results.
     */
    private ArrayList<Result> getPossibleResults() {
        ResultCalculator resultCalculator = new ResultCalculator(dieValuesAsInt);
        resultCalculator.calculateResults();
        results = resultCalculator.getResults();
        return results;
    }

    /**
     * rolls all dice in this draw.
     */
    private void rollDice() {
        for (Die die : dice) {
            die.rollDie();
        }
    }

    /**
     * creates printout String.
     * @return a String containing all possible valid results
     * accompanied by their scores
     */
    private String printResults() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Result result : results) {
            stringBuilder.append(result.toString());
        }
        return stringBuilder.toString();
    }
}
