package yahtc;

public abstract class GameSheet {

    protected final Player player;

    protected Integer[] scores = new Integer[Result.getAllResults().size()+1];

    public GameSheet(Player player) {
        this.player = player;
    }
    @Override
    public abstract String toString();

    public int calculateLower() {
        int score = 0;
        for (Result result : player.getResultsFilled()) {
            if (result.getOrdinal() < 7) {
                score += result.getScore();
            }
        }
        return score;
    }

    public int calculateUpper() {
        int score = 0;
        for (Result result : player.getResultsFilled()) {
            if (result.getOrdinal() > 7) {
                score += result.getScore();
            }
        }
        return score;
    }

    public int calculateTotal() {
        int score = 0;
        for (Result result : player.getResultsFilled()) {
            score += result.getScore();
        }
        return score;

    }

    public void updateResults() {
        for (Result result : player.getResultsFilled()) {
            scores[result.getOrdinal()] = result.getScore();
        }
    }
}
