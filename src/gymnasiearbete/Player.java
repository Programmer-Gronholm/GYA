package gymnasiearbete;

import java.util.Random;
public class Player extends Person{
    private int decision = 0;
    private Random random = new Random();


    public void makeDecision(Deck deck, Deck discard, Hand playerHand, Hand dealerHand) {
        // få valet av strategierna och sätt den som "decision":
        decision = 0;
        /*
        if (Strategies.getTrueCount(deck.getCards(), deck.getDeckAmount()) >= 1) {
            // If the true count is 1 or higher, we should hit more often
            // Assume "random" is a Random object used to simulate randomness
            decision = (random.nextDouble() < 0.7) ? 1 : 2;
            // 70% chance to hit, 30% chance to stand
        } else {
            // If the true count is less than 1, we should stand more often
            decision = (random.nextDouble() < 0.3) ? 1 : 2;
            // 30% chance to hit, 70% chance to stand
        }
        */


        // decision = Strategies.randomDecision();


        Strategies.basicStrategy(playerHand, dealerHand);
        decision = Strategies.getDecision();


        // Vad som händer beroende på val av hit, stand eller split
        if (decision == 1) {
            //hit the deck using the deck and discard deck
            hit(deck, discard, playerHand);
            //return (exit the method) if they have blackjack or busted
            if(playerHand.calculatedValue()>20){
                // går ut ur metoden
                return;

            }
            //if they didn't bust or get 21, allow them to decide to hit or stand again by going back to this same method
            else{

                makeDecision(deck, discard, playerHand, dealerHand);
            }

            // Else stand
        } else{
                return;
            }
    }


}
