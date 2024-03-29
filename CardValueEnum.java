import org.jetbrains.annotations.Contract;

public enum CardValueEnum {

    ACE(11),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10),
    QUEEN(10),
    KING(10);

    private final int points;

    @Contract(pure = true)
    CardValueEnum(final int points) {
        this.points = points;
    }

    @Contract(pure = true)
    public int getCardPoints() {
        return points;
    }
}
