//Enumeration class Suit - suit value (clubs, dia, ace, spades)
public enum Suit
{
    CLUBS, DIAMONDS, HEARTS, SPADES;
    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
