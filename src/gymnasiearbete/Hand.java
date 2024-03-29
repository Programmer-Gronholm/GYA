package gymnasiearbete;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> hand;
    private int aceCount;
    private boolean isSoftHand = false;

    public Hand(){
        hand = new ArrayList<Card>();
    }
    public ArrayList<Card> getHandCards(){
        return hand;
    }
    public int getCardValue(int index) {
        if (index < 0 || index >= hand.size()) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for hand size " + hand.size());
        }
        return hand.get(index).getValue();
    }
    public void takeCardFromDeck(Deck deck, Deck discard){
        if (!deck.hasCards()) {
            deck.reloadDeckFromDiscard(discard);
        }
        hand.add(deck.takeCard());
    }

    public int calculatedValue(){

        //variable to count number of aces, and current total value
        int value = 0;
        aceCount = 0;

        //For each card in this hand (a list of cards)
        for(Card card: hand){
            //Add the card value to the hand
            value += card.getValue();
            //Count how many aces have been added
            if (card.getValue() == 11){
                aceCount ++;
                isSoftHand = true;
            }
        }

        //if we have a scenario where we have multiple aces, as may be the case of drawing 10, followed by two or more aces, (10+11+1 > 21)
        //go back and set each ace to 1 until get back under 21, if possible
        if (value > 21 && aceCount > 0){
            while(aceCount > 0 && value > 21){
                aceCount --;
                value -= 10;
                isSoftHand = false;
            }
        }


        return value;

    }

    public boolean hasAce(){
        for (Card card : hand) {
            if (card.getValue() == 11) {
                return true;
            }
        }
        return false;
    }

    public boolean isSoftHand(){
        return hasAce() && calculatedValue() + 10 <= 21;

    }

    public boolean getIsSoftHand(){
        return isSoftHand;
    }

    public void discardHandToDeck(Deck discardDeck){

        //copy cards from hand to discardDeck
        discardDeck.addCards(hand);

        //clear the hand
        hand.clear();

    }

    public Card removeCard(int i){
        Card cardToTake = new Card(hand.get(i));
        hand.remove(i);
        return cardToTake;
    }

    public void addCard(Card card){
        hand.add(card);
    }

    public Card getIndexCard(int idx){
        return hand.get(idx);
    }
    public Hand copyHand() {
        Hand copy = new Hand();
        for (Card card : hand) {
            Card cardCopy = new Card(card);
            copy.addCard(cardCopy);
        }
        return copy;
    }

    public boolean canSplit(){
        if(getCardValue(0) == getCardValue(1) && hand.size() == 2){
            return true;
        } else{
            return false;
        }
    }

}
