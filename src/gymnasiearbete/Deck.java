package gymnasiearbete;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {
    private ArrayList<Card> deck;
    private int deckAmount = 8;
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
    public void shuffle(){
        Collections.shuffle(deck, new Random());
    }

}
