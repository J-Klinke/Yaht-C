package yahtc;

import java.util.ArrayList;

public abstract class Result implements Comparable<Result> {
    final String name;
    private int score;
    private final int ordinal;
    private static final ArrayList<Result> allResults = new ArrayList<>();

    public Result(int score, String name, int ordinal) {
       this.score = score;
       this.name = name;
       this.ordinal = ordinal;
    }

    public static class Ones extends Result {

        public Ones(int score) {
            super(score, "Ones", 1);
        }

        @Override
        public String toString() {
            return name + ": " + getScore() + " point(s); ";
        }

    }
    public static class Twos extends Result {
        public Twos(int score) {
            super(score, "Twos", 2);
        }

    }
    public static class Threes extends Result {
        public Threes(int score) {
            super(score, "Threes", 3);
        }

    }
    public static class Fours extends Result {
        public Fours(int score) {
            super(score, "Fours", 4);
        }


    }
    public static class Fives extends Result {
        public Fives(int score) {
            super(score, "Fives", 5);
        }

    }
    public static class Sixes extends Result {
        public Sixes(int score) {
            super(score, "Sixes", 6);
        }


    }
    public static class Bonus extends Result {
        public Bonus() {
            super(35, "Bonus", 7);
        }
    }

    public static class ThreeOfAKind extends Result {
        public ThreeOfAKind(int score) {
            super(score, "Three of a Kind", 8);
        }


    }
    public static class FourOfAKind extends Result {
        public FourOfAKind(int score) {
            super(score, "Four of a Kind", 9);
        }


    }
    public static class FullHouse extends Result {
        private static final int FULL_HOUSE_SCORE = 25;

        public FullHouse() {
            super(FULL_HOUSE_SCORE, "Full House", 10);
        }

    }
    public static class SmallStraight extends Result {
        public static final int SMALL_STRAIGHT_SCORE = 30;

        public SmallStraight() {
            super(SMALL_STRAIGHT_SCORE, "Small Straight", 11);
        }
    }
    public static class LargeStraight extends Result {
        public static final int LARGE_STRAIGHT_SCORE = 40;

        public LargeStraight() {
            super(LARGE_STRAIGHT_SCORE, "Large Straight", 12);
        }
    }
    public static class YahtC extends Result {
        public static final int YAHT_C_SCORE = 50;

        public YahtC() {
            super(YAHT_C_SCORE, "Yaht-C", 13);
        }

    }
    public static class Chance extends Result {
        public Chance(int score) {
            super(score, "Chance", 14);
        }


    }
    private static void fillAllResults() {
        allResults.add(new Ones(0));
        allResults.add(new Twos(0));
        allResults.add(new Threes((0)));
        allResults.add(new Fours(0));
        allResults.add(new Fives(0));
        allResults.add(new Sixes(0));
        allResults.add(new ThreeOfAKind(0));
        allResults.add(new FourOfAKind(0));
        allResults.add(new FullHouse());
        allResults.add(new SmallStraight());
        allResults.add(new LargeStraight());
        allResults.add(new YahtC());
        allResults.add(new Chance(0));
    }

    @Override
    public String toString() {
        return name + ": " + score + " points; ";
    }

    @Override
    public int compareTo(Result other) {
        return this.ordinal - other.ordinal;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return this.name;
    }

    public int getOrdinal() {
        return ordinal;
    }

    public static ArrayList<Result> getAllResults() {
        if (allResults.isEmpty()) {
            fillAllResults();
        }
        return allResults;
    }
}
