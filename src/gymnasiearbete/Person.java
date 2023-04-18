package gymnasiearbete;

public class Person {


    private HandList handList;
    private boolean willSplit;

    /**
     * Skapa ny spelare
     */
    public Person(){
        this.handList = new HandList();
    }


    public HandList getHandList() {
        return handList;
    }

    public void hit(Deck deck, Deck discard, Hand hand){

        //Om korten är slut
        if (!deck.hasCards()) {
            deck.reloadDeckFromDiscard(discard);
        }
        int hitCard = deck.getCards().get(0).getValue();
        hand.takeCardFromDeck(deck, discard);
        Strategies.updateCount(hitCard);

    }

    public void split(Deck deck, Deck discard, Hand hand){
        if (!deck.hasCards()) {
            deck.reloadDeckFromDiscard(discard);
        }


        // Skapa ny hand (vid split delas den första handen upp på två händer)
        Hand splithand1 = new Hand();
        Hand splithand2 = new Hand();
        System.out.println("Skapade nya händer!");
        // Lägger till den nya handen till en lista så att denna kan senare raderas från spelet

        // Lägger till det andra kortet från den första handen till den nya handen
        splithand1.addCard(hand.getIndexCard(0));
        splithand2.addCard(hand.getIndexCard(1));
        System.out.println("Första kortet!");


        // Lägger till ett nytt kort till båda händerna
        hit(deck, discard, splithand1);
        hit(deck, discard, splithand2);
        System.out.println("Andra kortet!");

        handList.addHand(splithand1);
        handList.addHand(splithand2);
        System.out.println("Händer i lisatan!");



    }

    public boolean getWillSplit(){
        return willSplit;
    }


    public boolean hasBlackjack(Hand hand){
        if(hand.calculatedValue() == 21){
            return true;
        }
        else{
            return false;
        }
    }
}
