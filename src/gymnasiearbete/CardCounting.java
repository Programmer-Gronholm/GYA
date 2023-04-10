package gymnasiearbete;

import java.util.ArrayList;
import java.util.Random;

public class CardCounting {
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

    public static int RandomDecision(){
        Random rand = new Random();
        return rand.nextInt(2) + 1;
    }
    

}
