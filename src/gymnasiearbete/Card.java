package gymnasiearbete;

public class Card {
    private Suit suit;
    private Rank rank;

    //create a card given a suit and a rank
    public Card(Suit suit, Rank rank){
        this.suit = suit;
        this.rank = rank;
    }

    public int getValue(){
        return rank.rankValue;
    }

    public Suit getSuit(){
        return suit;
    }

}
