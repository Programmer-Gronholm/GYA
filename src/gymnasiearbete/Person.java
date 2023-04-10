package gymnasiearbete;

public abstract class Person {

    private Hand hand;
    private SplitHandList splitHandList;

    /**
     * Skapa ny spelare
     */
    public Person(){
        this.hand = new Hand();
        this.splitHandList = new SplitHandList();
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

    public void split(Deck deck, Deck discard){

        // Om korten är slut, tar korten från discardeck
        if (!deck.hasCards()) {
            deck.reloadDeckFromDiscard(discard);
        }

        // Skapa ny hand (vid split delas den första handen upp på två händer)
        Hand splithand = new Hand();
        // Lägger till den nya handen till en lista så att denna kan senare raderas från spelet
        splitHandList.addHand(splithand);

        // Lägger till det andra kortet från den första handen till den nya handen
        splithand.addCard(this.hand.removeCard(1));

        // Lägger till ett nytt kort till båda händerna
        this.hand.takeCardFromDeck(deck);
        splithand.takeCardFromDeck(deck);


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
