package gymnasiearbete;

public abstract class Person {

    private Hand hand;

    /**
     * Skapa ny spelare
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

        //Om korten är slut
        if (!deck.hasCards()) {
            deck.reloadDeckFromDiscard(discard);
        }
        this.hand.takeCardFromDeck(deck);

    }
    public boolean hasBlackjack(){
        if(this.getHand().calculatedValue() == 21){
            return true;
        }
        else{
            return false;
        }
    }
}
