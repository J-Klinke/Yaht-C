public enum Value {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6);

    private final int asInt;
    Value(int asInt) {
        this.asInt = asInt;
    }

    int asInt() {
        return asInt;
    }
}
