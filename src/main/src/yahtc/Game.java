package yahtc;

public class Game {
    private final Player player1;
    private final Player player2;

    public Game(String player1Name, String player2Name, InputHandler inputHandler) {
        this.player1 = new Player(player1Name, inputHandler);
        this.player2 = new Player(player2Name, inputHandler);
    }

    /**
     * 'main' method of Game.
     */
    public void playGame() {
        for (int i = 0; i < Result.getAllResults().size(); i++) {
            System.out.println("Round: " + (i + 1)); //TODO OutputHandler
            player1.doDraw();
            player2.doDraw();
        }
        printWinner();
    }

    private void printWinner() {
        System.out.println("Winner is: " + getWinner().getName()); //TODO OutPutHandler
    }

    /**
     * calculates the winner by comparing their totalScores.
     * @return the winner
     */
    private Player getWinner() {
        if (player1.calculateCurrentTotalScore()
                > player2.calculateCurrentTotalScore()) {
            return player1;
        } else {
            return player2;
        }
    }


}
