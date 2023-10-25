package yahtc;

import java.util.Random;

public class Die {
    private Value value;
    private int valueAsInt;
    private final Random random = new Random();

    public Die() { }

    public Die(Die another) {
        this.value = another.getValue();
        this.valueAsInt = another.getValueAsInt();
    }

    /**
     * 'rolls' a die: chooses a values randomly and assigns value & valueAsInt.
     */
    public void rollDie() {
        int result = random.nextInt(1, 7);
        value = switch (result) {
            case 1 -> Value.ONE;
            case 2 -> Value.TWO;
            case 3 -> Value.THREE;
            case 4 -> Value.FOUR;
            case 5 -> Value.FIVE;
            case 6 -> Value.SIX;
            default -> throw new IllegalStateException("Unexpected value in Die.rollDie(): " + result);
        };
        valueAsInt = result;
    }

    public Value getValue() {
        return value;
    }

    public int getValueAsInt() {
        return valueAsInt;
    }
}
