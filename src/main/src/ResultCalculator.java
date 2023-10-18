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
     * ResultCalculator's 'main' method: checks for possible valid results
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
        if (dieValues.contains(1)) {results.add(new Result.Ones(calculateUpperPartScores(1)));}
        if (dieValues.contains(2)) {results.add(new Result.Twos(calculateUpperPartScores(2)));}
        if (dieValues.contains(3)) {results.add(new Result.Threes(calculateUpperPartScores(3)));}
        if (dieValues.contains(4)) {results.add(new Result.Fours(calculateUpperPartScores(4)));}
        if (dieValues.contains(5)) {results.add(new Result.Fives(calculateUpperPartScores(5)));}
        if (dieValues.contains(6)) {results.add(new Result.Sixes(calculateUpperPartScores(6)));}
    }

    private void checkForThreeOfAKind(){
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

    private void checkForFourOfAKind(){
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

            if (firstDiceValueCount == 2) {
                int secondDiceValueCount = countInstancesOfValue(secondDiceValue);
                if (secondDiceValueCount == 3) {
                    results.add(new Result.FullHouse());
                }
            } else {
                int secondDiceValueCount = countInstancesOfValue(secondDiceValue);
                if (secondDiceValueCount == 2) {
                    results.add(new Result.FullHouse());
                }
            }
        }
    }

    /**
     * looks for a combination of 1-2-3-4 or 2-3-4-5 or 3,4,5,6 and adds SMALL_STRAIGHT to the possible results.
     */
    private void checkForSmallStraight() {
        if (dieValues.contains(1) && dieValues.contains(2) && dieValues.contains(3) && dieValues.contains(4)
                || dieValues.contains(2) && dieValues.contains(3) && dieValues.contains(4) && dieValues.contains(5)
                || dieValues.contains(3) && dieValues.contains(4) && dieValues.contains(5) && dieValues.contains(6)) {
            results.add(new Result.SmallStraight());
        }
    }

    /**
     * looks for a combination of 1-2-3-4-5 or 2-3-4-5-6 and adds LARGE_STRAIGHT to the possible results.
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
     * checks if five dice value of a kind exist and adds YAHTZEE to the possible results.
     */
    private void checkForYahtzee() {
        if (dice[0].valueAsInt == dice[1].valueAsInt
                && dice[0].valueAsInt == dice[2].valueAsInt
                && dice[0].valueAsInt == dice[3].valueAsInt
                && dice[0].valueAsInt == dice[4].valueAsInt) {
            results.add(new Result.Yahtzee());
        }
    }

    private int countAllDice() {
        int score = 0;
        for (int dieValue : dieValues) {
            score += dieValue;
        }
        return score;
    }

    private int calculateUpperPartScores(int number) {
        int amount = 0;
        for (int dieValue : dieValues) {
            if (dieValue == number) {
                amount++;
            }
        }
        return number * amount;
    }

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
