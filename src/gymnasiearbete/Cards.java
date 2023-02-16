package gymnasiearbete;

public class Cards {
    //private final int value;
    private final Suit suit;

    public enum Suit {
        CLUBS,
        DIAMONDS,
        HEARTS,
        SPADES
    }

    public Cards(int value, Suit suit) {
        //this.value = value;
        this.suit = suit;
    }

    /*public int getValue() {

        return value;
    }

     */

    public Suit getSuit() {
        return suit;

    }

    public enum Rank {
        ACE("Ace", 11),
        TWO("Two", 2),
        THREE("Three", 3),
        FOUR("Four", 4),
        FIVE("Five", 5),
        SIX("Six", 6),
        SEVEN("Seven", 7),
        EIGHT("Eight", 8),
        NINE("Nine", 9),
        TEN("Ten", 10),
        JACK("Jack", 10),
        QUEEN("Queen", 10),
        KING("King", 10);

        String suitName;
        int suitValue;

        Rank(String suitName, int suitValue) {
            this.suitName = suitName;
            this.suitValue = suitValue;
        }

        public String toString() {
            return suitName;
        }

        public static void main(String[] args) {
            System.out.println(Rank.JACK + " Value: " + Rank.JACK.suitValue);
        }
    }
}
