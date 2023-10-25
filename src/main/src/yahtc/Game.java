package yahtc;

public class Game {
    private final Player player1;
    private final Player player2;

    private final IOHandler ioHandler;

    public Game(String player1Name, String player2Name, IOHandler ioHandler) {
        this.player1 = new Player(player1Name, ioHandler);
        this.player2 = new Player(player2Name, ioHandler);
        this.ioHandler = ioHandler;
    }

    /**
     * 'main' method of Game.
     */
    public void playGame() {
        for (int i = 0; i < Result.getAllResults().size(); i++) {
            ioHandler.printRound(i+1);
            player1.doDraw();
            player2.doDraw();
        }
        ioHandler.printWinner(getWinner());
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
