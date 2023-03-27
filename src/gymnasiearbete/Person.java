package gymnasiearbete;

public abstract class Person {

    private Hand hand;

    /**
     * Create a new Person
     */
    public Person(){
        this.hand = new Hand();
    }
    public Hand getHand(){
        return this.hand;
    }
    public void setHand(Hand hand){
        this.hand = hand;
    }

    public void hit(Deck deck, Deck discard){

        //If there's no cards left in the deck
        if (!deck.hasCards()) {
            deck.reloadDeckFromDiscard(discard);
        }
        this.hand.takeCardFromDeck(deck);



    }
}
