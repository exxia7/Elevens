
public class Card
{
    // instance variables
    private Suit suit;
    private Rank rank;

    /**
     * Constructor for objects of class Card
     */
    public Card(Rank rank, Suit suit)
    {
        // initialise instance variables
        this.rank = rank;
        this.suit = suit;
    }
    /**
     * getSuit 
     *
     * @param String suit
     * @return suit
     */
    public Suit getSuit(){
        return suit;
        } 
    /**
     * getRank 
     *
     * @param int rank
     * @return rank
     */
    public Rank getRank() {
        return rank;
    }
    /**
     * matches 
     *
     * @param Card other
     * @return true if this and other represent the same card
     */
    public boolean matches(Card other){
        return this.rank == other.rank && this.suit == other.suit;
    }
    /**
     * print 
     *
     * @param 
     * @return 
     */

    public void print(){
        if (this.rank.getPointValue() <= 10 || this.rank.getPointValue() >= 2) {
            System.out.println((this.rank.name() + " of " + this.suit.name()).toLowerCase()); 
        }
        else {
            System.out.println(Integer.toString(this.rank.getPointValue()) + " of " + (this.suit.name()).toLowerCase());
        }
    }
    @Override
    public String toString() {
        return rank.toString() + suit.toString();
    }
    public static void main (String[] args) {
    }
}
