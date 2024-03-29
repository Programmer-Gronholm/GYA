package gymnasiearbete;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {
    private ArrayList<Card> deck;
    private static int deckAmount = 8;
    public Deck(boolean makeDeck){
        deck = new ArrayList<Card>();
        if(makeDeck){
            //Go through all the suits
            for(Suit suit : Suit.values()){
                //Go through all the ranks
                for(Rank rank : Rank.values()){

                    for (int i = 0; i < deckAmount; i++) {
                        //add a new card containing each iteration's suit and rank
                        deck.add(new Card(suit, rank));

                    }
                }
            }
        }
    }
    public int getDeckAmount(){
        return deckAmount;
    }

    public void shuffle(){
        Collections.shuffle(deck, new Random());
    }
    public Card takeCard(){

        //Take a copy of the first card from the deck
        Card cardToTake = new Card(deck.get(0));
        //Remove the card from the deck
        deck.remove(0);
        //Give the card back
        return cardToTake;

    }
    public boolean hasCards() {
        return deck.size() > 0;
    }
    /**
     * Empties out this Deck
     */
    public void emptyDeck(){
        deck.clear();
    }

    /**
     *
     * @param cards an arraylist of cards to be added to this deck
     */
    public void addCards(ArrayList<Card> cards){
        deck.addAll(cards);
    }

    public ArrayList<Card> getCards(){
        return deck;
    }
    /**
     * Take all the cards from a discarded deck and place them in this deck, shuffled.
     * Clear the old deck
     * @param discard - the deck we're getting the cards from
     */
    // Ran out of cards, creating new deck from discard pile & shuffling deck.
    public void reloadDeckFromDiscard(Deck discard){
        this.addCards(discard.getCards());
        this.shuffle();
        discard.emptyDeck();
    }
    public int cardsLeft(){
        return deck.size();
    }
}
