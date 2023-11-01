package yahtc;

import java.util.ArrayList;
import java.util.Collections;

public class Player {
    private final GameSheet gameSheet;
    private final String name;

    private final IOHandler ioHandler;

    private final ArrayList<Result> resultsFilled;

    private boolean bonusCheckDone = false;

    public Player(String name, IOHandler ioHandler) {
        this.ioHandler = ioHandler;
        this.name = name;
        this.gameSheet = new GameSheet(this);
        this.resultsFilled = new ArrayList<>();
    }

    /**
     * does a whole draw from a player's perspective.
     */
    public void doDraw() {
        ioHandler.printGameSheet(this);
        Draw draw = new Draw(ioHandler);
        ArrayList<Result> playerResults = draw.doDraw();
        removeExistingResults(playerResults);
        for (int i = 0; i < 2; i++) {
            if (chooseIfRollAgain()) {
                Die[] fixedDice = chooseDiceToFix(draw.getDice());
                playerResults = draw.doFurtherDraws(fixedDice);
                removeExistingResults(playerResults);
            } else {
                break;
            }
        }
        chooseResult(playerResults);
        if (!bonusCheckDone) {
            checkForBonus();
        }
        ioHandler.printGameSheet(this);
    }

    /**
     * lets the player choose whether they want to roll th dice again.
     * @return their choice
     */
    private boolean chooseIfRollAgain() {
        return ioHandler.rollAgain();
    }

    /**
     * lets the player choose one of the possible results.
     * @param results the possible results
     */
    private void chooseResult(ArrayList<Result> results) {
        Result chosenResult;
        if (results.isEmpty()) {
            chosenResult = chooseEliminationResult();
        } else {
            chosenResult = ioHandler.resultChooser(results);
        }
        resultsFilled.add(chosenResult);
        sortResultsFilled();
    }

    /**
     * lets the player choose which dice should not be rolled again.
     * @param dice the current dice
     * @return Die[] with the dice not to be rolled again
     */
    private Die[] chooseDiceToFix(Die[] dice) {
        return ioHandler.dicePicker(dice);
    }

    /**
     * lets the player choose a result to be eliminated,
     * if no valid result exists.
     * @return the chosen result
     */
    private Result chooseEliminationResult() {
        Result chosenResult;
        ArrayList<Result> eliminableResults =
                new ArrayList<>(Result.getAllResults());
        removeExistingResults(eliminableResults);
        for (Result result : eliminableResults) {
            result.setScore(0);
        }
        ioHandler.printEliminationMessage();
        chosenResult = ioHandler.resultChooser(eliminableResults);
        return chosenResult;
    }

    /**
     * removes all already filled results from the possible ones.
     * @param results the possible results
     */
    private void removeExistingResults(ArrayList<Result> results) {
        ArrayList<Result> resultsToBeRemoved = new ArrayList<>();
        for (Result result : results) {
            for (Result resultFilled : resultsFilled) {
                if (result.getClass().equals(resultFilled.getClass())) {
                    resultsToBeRemoved.add(result);
                }
            }
        }
        results.removeAll(resultsToBeRemoved);
    }

    /**
     * checks whether the player achieved the bonus.
     */
    private void checkForBonus() {
        boolean isBonus = true;
        for (int i = 0; i <= new Result.Sixes(0).getOrdinal(); i++) {
            if (resultsFilled.get(i).getOrdinal() != Result.getAllResults().get(i).getOrdinal()) {
                isBonus = false;
            }
        }
        if (isBonus) {
            bonusCheckDone = true;
            if (calculateUpperScore() >= 63) {
                resultsFilled.add(new Result.Bonus());
            }
        }
        sortResultsFilled();
    }

    /**
     * calculates the score of the upper part.
     * @return the upper part's score.
     */
    private int calculateUpperScore() {
        int upperScore = 0;
        for (int i = 0; i < 6; i++) {
            upperScore += resultsFilled.get(i).getScore();
        }
        return upperScore;
    }

    /**
     * calculates the current total score.
     * @return the score
     */
    public int calculateCurrentTotalScore() {
        int score = 0;
        for (Result result : resultsFilled) {
            score += result.getScore();
        }
        return score;
    }

    /**
     * sorts resultsFilled.
     */
    public void sortResultsFilled() {
        Collections.sort(resultsFilled);
    }

    public ArrayList<Result> getResultsFilled() {
        return resultsFilled;
    }

    public String getName() {
        return name;
    }
}
