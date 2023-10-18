public abstract class Result {
    int score;
    String name;

    public Result(int score, String name){
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
            return name + ": " + score + " point(s); ";
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

        public FullHouse() {
            super(25, "Full House");
        }

    }

    public static class SmallStraight extends Result {

        public SmallStraight() {
            super(30, "Small Straight");
        }
    }

    public static class LargeStraight extends Result {
        public LargeStraight() {
            super(40, "Large Straight");
        }
    }

    public static class Yahtzee extends Result {
        public Yahtzee() {
            super(50, "Yahtzee");
        }

    }

    public static class Chance extends Result {
        public Chance(int score) {
            super(score, "Chance");
        }

    }

}

























