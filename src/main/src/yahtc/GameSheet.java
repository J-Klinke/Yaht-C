package yahtc;

public class GameSheet {
    private final Player player;

    public GameSheet(Player player) {
        this.player = player;
    }

    public void print() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return player.getName()
                + "\n"
                + player.getResultsFilled()
                + "\n"
                + player.calculateCurrentTotalScore()
                + "\n";
    }
}
