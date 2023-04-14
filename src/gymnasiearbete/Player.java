package gymnasiearbete;

import java.util.Random;
public class Player extends Person{
    private int decision = 0;
    private Random random = new Random();

    public void makeDecision(Deck deck, Deck discard, Hand hand) {
        // få valet av strategierna och sätt den som "decision":

        if (CardCounting.getTrueCount(deck.getCards(), deck.getDeckAmount()) >= 1) {
            // If the true count is 1 or higher, we should hit more often
            // Assume "random" is a Random object used to simulate randomness
            decision = (random.nextDouble() < 0.7) ? 1 : 2;
            // 70% chance to hit, 30% chance to stand
        } else {
            // If the true count is less than 1, we should stand more often
            decision = (random.nextDouble() < 0.3) ? 1 : 2;
            // 30% chance to hit, 70% chance to stand
        }
        /*
        if(canSplit(hand)){
            decision = 3;
        }
        */
        // Vad som händer beroende på val av hit, stand eller split
        if (decision == 1) {
            //hit the deck using the deck and discard deck
            hit(deck, discard, hand);
            //return (exit the method) if they have blackjack or busted
            if(hand.calculatedValue()>20){
                // går ut ur metoden
                return;

            }
            //if they didn't bust or get 21, allow them to decide to hit or stand again by going back to this same method
            else{

                makeDecision(deck, discard, hand);
            }

            // Else stand
        } else if (decision == 2){
                return;
            }
        else if(decision == 3){

            split(deck, discard, hand);


        }
    }
    public int getDecision(){
        return decision;
    }

}
