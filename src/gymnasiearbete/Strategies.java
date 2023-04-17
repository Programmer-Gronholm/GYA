package gymnasiearbete;

import java.util.ArrayList;
import java.util.Random;

public class Strategies {
    // Define the count variable
    private static int count = 0;


    // Method to update the count based on the drawn card
    public static void updateCount(int cardValue) {
        if (cardValue >= 2 && cardValue <= 6) {
            count++;
        } else if (cardValue >= 10) {
            count--;
        }
    }

    // Method to calculate the true count
    public static double getTrueCount(ArrayList<Card> deck, int numDecks) {
        int numCards = deck.size();
        double decksLeft = (numDecks * 52 - numCards) / 52.0;
        double trueCount = count / decksLeft;
        return trueCount;
    }

    // Method to reset the count
    public static void resetCount() {
        count = 0;
    }

    public static int randomDecision(){
        Random rand = new Random();
        int randDecision = rand.nextInt(2) + 1;
        return randDecision;
    }

    public static void basicStrategy(Hand playerHand, Hand dealerHand, int decision){
        if(playerHand.isSoftHand()){
            switch (playerHand.calculatedValue()){
                case 13, 14, 15, 16, 17:
                    decision = 1;
                    break;
                case 18:
                    if(dealerHand.getCardValue(1) >= 7 && dealerHand.getCardValue(1) <= 8){
                        break;
                    } else
                        decision = 1;
                    break;
                case 19:
                    if(dealerHand.getCardValue(1) == 6){
                        decision = 1;
                    }
                    break;
                case 20:
                    break;
            }
        } else{
            switch (playerHand.calculatedValue()){
                case 5, 6, 7, 8, 9 , 10, 11:
                    decision = 1;
                    break;
                case 12:
                    if(dealerHand.getCardValue(1) <= 6 && dealerHand.getCardValue(1) >= 4 ){
                        decision = 1;
                    }
                    break;

                case 17, 18, 19, 20:
                    break;

            }

        }


    }
    public static void splitStrategy(Hand playerHand, Hand dealerHand, boolean willSplit, Deck deck, Deck discard, Player player){
        if(playerHand.canSplit()){
            switch (playerHand.getCardValue(0)){
                case 2, 3:
                    if(dealerHand.getCardValue(1) >= 2 && dealerHand.getCardValue(1) <= 7){
                        willSplit = true;
                    } else{
                        willSplit = false;
                        player.hit(deck,discard,playerHand);
                    }
                    break;
                case 4:
                    if(dealerHand.getCardValue(1) == 5 || dealerHand.getCardValue(1) == 6){
                        willSplit = true;
                    } else {
                        willSplit = false;
                        player.hit(deck,discard,playerHand);
                    }
                    break;
                case 5:
                    willSplit = false;
                    player.hit(deck,discard,playerHand);
                    break;
                case 6:
                    if (dealerHand.getCardValue(1) >= 2 && dealerHand.getCardValue(1) <= 6){
                        willSplit = true;
                    } else {
                        willSplit = false;
                        player.hit(deck,discard,playerHand);
                    }
                    break;
                case 7:
                    if (dealerHand.getCardValue(1) >= 2 && dealerHand.getCardValue(1) <= 7){
                        willSplit = true;
                    } else{
                        willSplit = false;
                        player.hit(deck,discard,playerHand);
                    }
                    break;
                case 8:
                    if (dealerHand.getCardValue(1) >= 2 && dealerHand.getCardValue(1) <= 9){
                        willSplit = true;
                    } else{
                        willSplit = false;
                        player.hit(deck,discard,playerHand);
                    }
                    break;
                case 9:
                    if (dealerHand.getCardValue(1) == 7 || dealerHand.getCardValue(1) == 10 || dealerHand.getCardValue(1) == 11){
                        willSplit = false;
                        // stand
                    } else
                        willSplit = true;
                    break;
                case 10:
                    willSplit = false;
                    // stand
                    break;
                case 11:
                    if (dealerHand.getCardValue(1) == 11){
                        willSplit = false;
                        player.hit(deck,discard,playerHand);
                    } else
                        willSplit = true;
                    break;

            }

        }

    }



}
