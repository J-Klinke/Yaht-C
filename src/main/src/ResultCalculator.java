import java.util.ArrayList;

public class ResultCalculator {

    private final ArrayList<Result> results = new ArrayList<>();
    private final Die[] dice;
    private final ArrayList<Integer> dieValues;

    public ResultCalculator(Die[] dice, ArrayList<Integer> dieValues) {
        this.dice = dice;
        this.dieValues = dieValues;
    }

    /**
     * ResultCalculator's 'main' method: checks for possible valid results.
     */
    public void calculateResults() {
        checkForNumbers();
        checkForThreeOfAKind();
        checkForFourOfAKind();
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
        if (dieValues.contains(1)) {
            results.add(new Result.Ones(calculateUpperPartScores(1)));
        }
        if (dieValues.contains(2)) {
            results.add(new Result.Twos(calculateUpperPartScores(2)));
        }
        if (dieValues.contains(3)) {
            results.add(new Result.Threes(calculateUpperPartScores(3)));
        }
        if (dieValues.contains(4)) {
            results.add(new Result.Fours(calculateUpperPartScores(4)));
        }
        if (dieValues.contains(5)) {
            results.add(new Result.Fives(calculateUpperPartScores(5)));
        }
        if (dieValues.contains(6)) {
            results.add(new Result.Sixes(calculateUpperPartScores(6)));
        }
    }

    /**
     * checks whether if there are at least three dice with the same value.
     */
    private void checkForThreeOfAKind() {
        for (int i = 1; i < 7; i++) {
            int count = 0;
            for (int diceValue : dieValues) {
                if (diceValue == i) {
                    count++;
                }
            }
            if (count >= 3) {
                results.add(new Result.ThreeOfAKind(countAllDice()));
                return;
            }
        }
    }

    /**
     * checks whether if there are at least four dice with the same value.
     */
    private void checkForFourOfAKind() {
        for (int i = 1; i < 7; i++) {
            int count = 0;
            for (int diceValue : dieValues) {
                if (diceValue == i) {
                    count++;
                }
            }
            if (count >= 4) {
                results.add(new Result.FourOfAKind(countAllDice()));
                return;
            }
        }
    }

    /**
     * checks whether there are 2 dice of one value and three of another.
     */
    private void checkForFullHouse() {
        int firstDiceValue = dieValues.get(0);
        int firstDiceValueCount = countInstancesOfValue(firstDiceValue);
        if (firstDiceValueCount == 3 || firstDiceValueCount == 2) {
            int secondDiceValue = 0;
            for (int diceValue : dieValues) {
                if (diceValue != firstDiceValue) {
                    secondDiceValue = diceValue;
                    break;
                }
            }

            int secondDiceValueCount = countInstancesOfValue(secondDiceValue);
            if (firstDiceValueCount == 2) {
                if (secondDiceValueCount == 3) {
                    results.add(new Result.FullHouse());
                }
            } else {
                if (secondDiceValueCount == 2) {
                    results.add(new Result.FullHouse());
                }
            }
        }
    }

    /**
     * looks for a combination of 1-2-3-4 or 2-3-4-5 or 3,4,5,6.
     */
    private void checkForSmallStraight() {
        if (dieValues.contains(1) && dieValues.contains(2) && dieValues.contains(3) && dieValues.contains(4)
                || dieValues.contains(2) && dieValues.contains(3) && dieValues.contains(4) && dieValues.contains(5)
                || dieValues.contains(3) && dieValues.contains(4) && dieValues.contains(5) && dieValues.contains(6)) {
            results.add(new Result.SmallStraight());
        }
    }

    /**
     * looks for a combination of 1-2-3-4-5 or 2-3-4-5-6.
     */
    private void checkForLargeStraight() {
        if (dieValues.contains(1) && dieValues.contains(2) && dieValues.contains(3) && dieValues.contains(4)
                && dieValues.contains(5)
                || dieValues.contains(2) && dieValues.contains(3) && dieValues.contains(4)
                && dieValues.contains(5) && dieValues.contains(6)) {
            results.add(new Result.LargeStraight());
        }
    }

    /**
     * checks if five dice values of a kind exist.
     */
    private void checkForYahtzee() {
        if (dice[0].getValueAsInt() == dice[1].getValueAsInt()
                && dice[0].getValueAsInt() == dice[2].getValueAsInt()
                && dice[0].getValueAsInt() == dice[3].getValueAsInt()
                && dice[0].getValueAsInt() == dice[4].getValueAsInt()) {
            results.add(new Result.Yahtzee());
        }
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
