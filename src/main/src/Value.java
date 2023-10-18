public enum Value {
    ONE (1),
    TWO (2),
    THREE (3),
    FOUR (4),
    FIVE (5),
    SIX (6);

    final int number;
    Value(int number) {
        this.number = number;
    }

    int getNumber() {
        return number;
    }
}
