public abstract class Result {
    public final String name;
    private final int score;

    public Result(int score, String name) {
       this.score = score;
       this.name = name;
    }

    @Override
    public String toString() {
        return name + ": " + score + " points; ";
    }

    public static class Ones extends Result {

        public Ones(int score) {
            super(score, "Ones");
        }
        @Override
        public String toString() {
            return name + ": " + getScore() + " point(s); ";
        }

    }
    public static class Twos extends Result {

        public Twos(int score) {
            super(score, "Twos");
        }
    }
    public static class Threes extends Result {

        public Threes(int score) {
            super(score, "Threes");
        }
    }
    public static class Fours extends Result {

        public Fours(int score) {
            super(score, "Fours");
        }

    }
    public static class Fives extends Result {

        public Fives(int score) {
            super(score, "Fives");
        }
    }
    public static class Sixes extends Result {

        public Sixes(int score) {
            super(score, "Sixes");
        }

    }
    public static class ThreeOfAKind extends Result {

        public ThreeOfAKind(int score) {
            super(score, "Three of a Kind");
        }

    }
    public static class FourOfAKind extends Result {

        public FourOfAKind(int score) {
            super(score, "Four of a Kind");
        }

    }
    public static class FullHouse extends Result {

        private static final int FULL_HOUSE_SCORE = 25;
        public FullHouse() {
            super(FULL_HOUSE_SCORE, "Full House");
        }

    }
    public static class SmallStraight extends Result {

        public static final int SMALL_STRAIGHT_SCORE = 30;
        public SmallStraight() {
            super(SMALL_STRAIGHT_SCORE, "Small Straight");
        }
    }
    public static class LargeStraight extends Result {

        public static final int LARGE_STRAIGHT_SCORE = 40;
        public LargeStraight() {
            super(LARGE_STRAIGHT_SCORE, "Large Straight");
        }
    }
    public static class YahtC extends Result {

        public static final int YAHT_C_SCORE = 50;
        public YahtC() {
            super(YAHT_C_SCORE, "Yahtzee");
        }

    }
    public static class Chance extends Result {

        public Chance(int score) {
            super(score, "Chance");
        }

    }

    public int getScore() {
        return score;
    }
}

























