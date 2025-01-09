import java.util.ArrayList;
import java.util.Random;

public class Deck
{
    // instance variables 
    private int size;
    private ArrayList<Card> deck;
    
    //default Deck constructor
    public Deck () {
        deck = new ArrayList<>();
        for (Rank r: Rank.values()) {
            for (Suit s: Suit.values()) {
                Card c = new Card(r, s);
                deck.add(c);
            }
        }
        size = deck.size();
        shuffle();
    }

    //Deck constructor with parameters    
    public Deck (Rank[] rankArray, Suit[] suitArray) {
        for (Rank rank: rankArray) {
            for (Suit suit : suitArray) {
                Card c = new Card(rank, suit);
                deck.add(c);
            }
        }
        size = deck.size();
    }

    //isEmpty function - true if deck is empty
    public boolean isEmpty()
    {
        if (size == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    //return deck size
    public int getSize()
    {
        return size;
    }

    //deal function
    public Card deal()
    {
        if (size > 0){
            size--;
            Card deal = deck.get(size);
            return deal;
        }
        else {
            return null;
        }
    }

    //perfect shuffle function
    public void perfectShuffle()
    {
        ArrayList<Card> perfectShuffled = new ArrayList<Card>();
        for (int j=0; j<=25; j++) {
            perfectShuffled.add(deck.get(j));
        }
        int k = 1;
        for (int j=26; j<=51; j++) {
            perfectShuffled.add(k, deck.get(j));
            k = k+2;
        }
        size = perfectShuffled.size();
        deck = perfectShuffled;
    }
    
    //inshuffle function
    public void inShuffle() 
    {
        ArrayList<Card> inShuffled = new ArrayList<Card>();
        int k=0;
        for (int j=26; j<=51; j++) {
            inShuffled.add(deck.get(j));
            k = k+2;
        }
        k = 1;
        for (int j=0; j<=25; j++) {
            inShuffled.add(k, deck.get(j));
            k = k+2;
        }
        size = inShuffled.size();
        deck = inShuffled;
    }
    
    //select shuffle function
    public void shuffle()
    {
        int n = deck.size();
        int r;
        for (int k=n-1; k>=1; k--) {
            r = (int) (Math.random() * (k+1));
            Card kCard = deck.get(k);
            Card rCard = deck.get(r);
            deck.set(k, rCard);
            deck.set(r, kCard);
        }
        size = deck.size();
    }

    //toString
    @Override
    public String toString() {
        String finalString = "";
        for (int i=0; i<size; i++) {
            finalString += deck.get(i).toString() + "\n";
        }
        return finalString;
    }
    public static void main(String[] args) {
        Deck d1 = new Deck();
        d1.shuffle(); 
        System.out.println(d1);
        }
    }
