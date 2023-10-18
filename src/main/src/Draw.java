import java.util.ArrayList;

public class Draw {
    private final Die[] dice = new Die[5];
    private final ArrayList<Integer> dieValues = new ArrayList<>();
    private ArrayList<Result> results = new ArrayList<>();

    public Draw() {
        dice[0] = new Die();
        dice[1] = new Die();
        dice[2] = new Die();
        dice[3] = new Die();
        dice[4] = new Die();
    }
    
    public void doDraw() {
        //for (int i = 0; i < 3; i++) {
            rollDice();
        //}
        translateDiceValues();
        getPossibleResults();
        System.out.println(dieValues);
        System.out.println(printResults());
    }

    private void translateDiceValues() {
        for (Die die : dice) {
            dieValues.add(die.valueAsInt);
        }
    }
    private void getPossibleResults() {
        ResultCalculator resultCalculator = new ResultCalculator(dice, dieValues);
        resultCalculator.calculateResults();
        results = resultCalculator.getResults();
    }


    private void rollDice(){
        for (Die die : dice) {
            die.rollDie();
        }
    }

    private String printResults() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Result result : getResults()) {
            stringBuilder.append(result.toString());
        }
        return stringBuilder.toString();
    }

    public ArrayList<Result> getResults() {
        return results;
    }
}
