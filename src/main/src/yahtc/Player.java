package yahtc;

import java.util.ArrayList;

public class Player {
    private final GameSheet gameSheet;
    private final String name;

    private final InputHandler inputHandler = new BareBonesInputHandler();

    private final ArrayList<Result> resultsFilled;

    public Player(String name) {
        this.name = name;
        this.gameSheet = new GameSheet(this);
        this.resultsFilled = new ArrayList<>();
    }

    /**
     * does a whole draw from a player's perspective.
     */
    public void doDraw() {
        Draw draw = new Draw();
        ArrayList<Result> player1results = draw.doDraw();
        removeExistingResults(player1results);
        if (chooseIfReroll()) {
            Die[] fixedDice = chooseDiceToFix(draw.getDice());
            player1results = draw.doFurtherDraws(fixedDice);
            removeExistingResults(player1results);
        }
        if (chooseIfReroll()) {
            Die[] fixedDice = chooseDiceToFix(draw.getDice());
            player1results = draw.doFurtherDraws(fixedDice);
            removeExistingResults(player1results);
        }
        chooseResult(player1results);
        gameSheet.print();
    }

    /**
     * lets the player choose whether they want to roll th dice again.
     * @return their choice
     */
    private boolean chooseIfReroll() {
        return inputHandler.yesNoChooser();
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
            chosenResult = inputHandler.resultChooser(results);

        }
        resultsFilled.add(chosenResult);
    }

    /**
     * lets the player choose which dice should not be rolled again.
     * @param dice the current dice
     * @return Die[] with the dice not to be rolled again
     */
    private Die[] chooseDiceToFix(Die[] dice) {
        return inputHandler.diceChooser(dice);
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
        chosenResult = inputHandler.resultChooser(eliminableResults);
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

    public ArrayList<Result> getResultsFilled() {
        return resultsFilled;
    }

    public String getName() {
        return name;
    }
}
