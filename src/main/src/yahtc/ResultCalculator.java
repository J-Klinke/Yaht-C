package yahtc;

import java.util.ArrayList;

public class ResultCalculator {

    private static final int THREE_OF_A_KIND = 3;
    private static final int FOUR_OF_A_KIND = 4;
    private final ArrayList<Result> results = new ArrayList<>();
    private final ArrayList<Integer> dieValues;

    public ResultCalculator(ArrayList<Integer> dieValues) {
        this.dieValues = dieValues;
    }

    /**
     * ResultCalculator's 'main' method: checks for possible valid results.
     */
    public void calculateResults() {
        checkForNumbers();
        checkForXOfAKind(THREE_OF_A_KIND);
        checkForXOfAKind(FOUR_OF_A_KIND);
        checkForFullHouse();
        checkForSmallStraight();
        checkForLargeStraight();
        checkForYahtzee();
        results.add(new Result.Chance(countAllDice()));
    }

    /**
     * checks whether the 'numbers' results (ONES, TWOS, ...) are possible.
     */
    private void checkForNumbers() {
        if (dieValues.contains(Value.ONE.asInt())) {
            results.add(new Result.Ones(
                    calculateUpperPartScores(Value.ONE.asInt())));
        }
        if (dieValues.contains(Value.TWO.asInt())) {
            results.add(new Result.Twos(
                    calculateUpperPartScores(Value.TWO.asInt())));
        }
        if (dieValues.contains(Value.THREE.asInt())) {
            results.add(new Result.Threes(
                    calculateUpperPartScores(Value.THREE.asInt())));
        }
        if (dieValues.contains(Value.FOUR.asInt())) {
            results.add(new Result.Fours(
                    calculateUpperPartScores(Value.FOUR.asInt())));
        }
        if (dieValues.contains(Value.FIVE.asInt())) {
            results.add(new Result.Fives(
                    calculateUpperPartScores(Value.FIVE.asInt())));
        }
        if (dieValues.contains(Value.SIX.asInt())) {
            results.add(new Result.Sixes(
                    calculateUpperPartScores(Value.SIX.asInt())));
        }
    }

    /**
     * checks whether if there are at least x with the same value.
     * @param x the amount of necessary dice of a kind
     */
    private void checkForXOfAKind(int x) {
        for (int i = 1; i <= Value.values().length; i++) {
            int count = 0;
            for (int diceValue : dieValues) {
                if (diceValue == i) {
                    count++;
                }
            }
            if (count >= x) {
                switch (x) {
                    case THREE_OF_A_KIND -> results.add(
                            new Result.ThreeOfAKind(countAllDice()));
                    case FOUR_OF_A_KIND -> results.add(
                            new Result.FourOfAKind(countAllDice()));
                }
                return;
            }
        }
    }

    /**
     * checks whether there are 2 dice of one value and three of another.
     */
    private void checkForFullHouse() {
        final int firstFullHouseAmount = 2;
        final int secondFullHouseAmount = 3;

        int firstDiceValue = dieValues.get(0);
        int firstDiceValueCount = countInstancesOfValue(firstDiceValue);

        if (firstDiceValueCount == firstFullHouseAmount
                || firstDiceValueCount == secondFullHouseAmount) {
            int secondDiceValue = 0;
            for (int diceValue : dieValues) {
                if (diceValue != firstDiceValue) {
                    secondDiceValue = diceValue;
                    break;
                }
            }

            int secondDiceValueCount = countInstancesOfValue(secondDiceValue);
            if (firstDiceValueCount == firstFullHouseAmount) {
                if (secondDiceValueCount == secondFullHouseAmount) {
                    results.add(new Result.FullHouse());
                }
            } else {
                if (secondDiceValueCount == firstFullHouseAmount) {
                    results.add(new Result.FullHouse());
                }
            }
        }
    }

    /**
     * looks for a combination of 1-2-3-4 or 2-3-4-5 or 3,4,5,6.
     */
    private void checkForSmallStraight() {
        if (dieValues.contains(Value.ONE.asInt()) && dieValues.contains(Value.TWO.asInt())
                && dieValues.contains(Value.THREE.asInt()) && dieValues.contains(Value.FOUR.asInt())
                || dieValues.contains(Value.TWO.asInt()) && dieValues.contains(Value.THREE.asInt())
                && dieValues.contains(Value.FOUR.asInt()) && dieValues.contains(Value.FIVE.asInt())
                || dieValues.contains(Value.THREE.asInt()) && dieValues.contains(Value.FOUR.asInt())
                && dieValues.contains(Value.FIVE.asInt()) && dieValues.contains(Value.SIX.asInt())) {
            results.add(new Result.SmallStraight());
        }
    }

    /**
     * looks for a combination of 1-2-3-4-5 or 2-3-4-5-6.
     */
    private void checkForLargeStraight() {
        if (dieValues.contains(Value.ONE.asInt()) && dieValues.contains(Value.TWO.asInt())
                && dieValues.contains(Value.THREE.asInt()) && dieValues.contains(Value.FOUR.asInt())
                && dieValues.contains(Value.FIVE.asInt())
                || dieValues.contains(Value.TWO.asInt()) && dieValues.contains(Value.THREE.asInt())
                && dieValues.contains(Value.FOUR.asInt()) && dieValues.contains(Value.FIVE.asInt())
                && dieValues.contains(Value.SIX.asInt())) {
            results.add(new Result.LargeStraight());
        }
    }

    /**
     * checks if all five dice in ResultCalculator.dice have the same value.
     */
    private void checkForYahtzee() {
        for (int dieValue : dieValues) {
            if (dieValues.get(0) != dieValue) {
                return;
            }
        }
        results.add(new Result.YahtC());
    }

    /**
     * adds together the valuesAsInt of all dice.
     * @return this sum
     */
    private int countAllDice() {
        int score = 0;
        for (int dieValue : dieValues) {
            score += dieValue;
        }
        return score;
    }

    /**
     * adds together all instances of a specific value.
     * @param number the die's value to be counted.
     * @return value * number of instances of this value
     */
    private int calculateUpperPartScores(int number) {
        int amount = 0;
        for (int dieValue : dieValues) {
            if (dieValue == number) {
                amount++;
            }
        }
        return number * amount;
    }

    /**
     * counts instances of a specific value.
     * @param value the given value
     * @return number of instances
     */
    private int countInstancesOfValue(int value) {
        int amount = 0;
        for (int diceValue : dieValues) {
            if (diceValue == value) {
                amount++;
            }
        }
        return amount;
    }

    public ArrayList<Result> getResults() {
        return results;
    }
}
