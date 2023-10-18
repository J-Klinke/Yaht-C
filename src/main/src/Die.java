import java.util.Random;

public class Die {
    Value value;
    int valueAsInt;
    private final Random random = new Random();

    public void rollDie(){
        int result = random.nextInt(1,7);
        value = switch (result) {
            case 1 -> Value.ONE;
            case 2 -> Value.TWO;
            case 3 -> Value.THREE;
            case 4 -> Value.FOUR;
            case 5 -> Value.FIVE;
            case 6 -> Value.SIX;
            default -> throw new IllegalStateException("Unexpected value: " + result);
        };
        valueAsInt = value.getNumber();
    }
}
