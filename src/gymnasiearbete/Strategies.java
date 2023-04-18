package gymnasiearbete;

import java.util.ArrayList;
import java.util.Random;

public class Strategies {
    // Define the count variable
    private static int count = 0;
    private static boolean willSplit = false;
    private static int decision = 0;


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

    public static void basicStrategy(Hand playerHand, Hand dealerHand){
        if(playerHand.getIsSoftHand()){
            System.out.println("Soft Hand!");
            switch (playerHand.calculatedValue()){
                case 13: case 14: case 15: case 16: case 17:
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
                case 5: case 6: case 7: case 8: case 9: case 10: case 11:
                    decision = 1;
                    break;
                case 12:
                    if(dealerHand.getCardValue(1) <= 6 && dealerHand.getCardValue(1) >= 4){
                        decision = 1;
                    }
                    break;
                case 13: case 14: case 15: case 16:
                    if(dealerHand.getCardValue(1) >= 7 && dealerHand.getCardValue(1) <= 11){
                        decision = 1;
                    }
                    break;
                case 17: case 18: case 19: case 20:
                    break;

            }

        }


    }
    public static void splitStrategy(Hand playerHand, Hand dealerHand, Deck deck, Deck discard, Player player){
        willSplit = false;
        if(playerHand.canSplit()){
            switch (playerHand.getCardValue(0)){
                case 2: case 3: case 7:
                    if(dealerHand.getCardValue(1) >= 2 && dealerHand.getCardValue(1) <= 7){
                        willSplit = true;
                    } else{
                        player.hit(deck,discard,playerHand);
                    }
                    break;
                case 4:
                    if(dealerHand.getCardValue(1) == 5 || dealerHand.getCardValue(1) == 6){
                        willSplit = true;
                    } else {
                        player.hit(deck,discard,playerHand);
                    }
                    break;
                case 5:
                    player.hit(deck,discard,playerHand);
                    break;
                case 6:
                    if (dealerHand.getCardValue(1) >= 2 && dealerHand.getCardValue(1) <= 6){
                        willSplit = true;
                    } else {
                        player.hit(deck,discard,playerHand);
                    }
                    break;
                case 8:
                    if (dealerHand.getCardValue(1) >= 2 && dealerHand.getCardValue(1) <= 9){
                        willSplit = true;
                    } else{
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
                    // stand
                    break;
                case 11:
                    if (dealerHand.getCardValue(1) == 11){
                        player.hit(deck,discard,playerHand);
                    } else
                        willSplit = true;
                    break;

            }

        }

    }
    public static int getDecision(){
        return decision;
    }

    public static boolean getWillSplit(){
        return willSplit;
    }



}
