package gymnasiearbete;

import java.util.ArrayList;


public class BlackJack {
    private Deck deck, discarded;
    private Dealer dealer;
    private Player player;

    private Hand dealerHand;
    private ArrayList<Hand> bustedHands;
    private boolean willSplit = false;
    private int wins, losses, pushes;

    public BlackJack() {
        //Create a new deck with 52 cards
        deck = new Deck(true);
        //Create a new empty deck
        discarded = new Deck(false);


        //Create the People
        dealer = new Dealer();
        player = new Player();
        dealerHand = new Hand();
        dealer.getHandList().addHand(dealerHand);


        //Shuffle the deck and start the first round
        deck.shuffle();
        for (int i = 0; i <= 10000; i++) {
            startRound();
        }
        System.out.println("Wins: " + wins + " Losses: " + losses + " Pushes: " + pushes);
    }

    private void startRound(){
        // Lägger till spelaren hand
        player.getHandList().addHand(new Hand());

        willSplit = false;

        bustedHands = new ArrayList<>();

        Strategies.resetCount();
        boolean roundOver = false;

        while(!roundOver) {
            //If this isn't the first round, display the users score and put their cards back in the deck
            if (wins > 0 || losses > 0 || pushes > 0) {
                System.out.println();
                System.out.println("Starting Next Round... Wins: " + wins + " Losses: " + losses + " Pushes: " + pushes);
                dealerHand.discardHandToDeck(discarded);
                //player.getHand().discardHandToDeck(discarded);
            }

            //Check to make sure the deck has at least 4 cards left to start
            //Technically this could mean there's 1-3 cards left in the deck that are usable, but we'll just reshuffle them and not worry about it
            if (deck.cardsLeft() < 4) {
                //reload the deck from discard pile if we're out of cards
                System.out.println("RELOAD");
                deck.reloadDeckFromDiscard(discarded);
            }

            //Give the dealer two cards
            //Give the player two cards

            for (int i = 0; i < 2; i++) {
                player.getHandList().getHandsList().get(0).takeCardFromDeck(deck, discarded);
                dealerHand.takeCardFromDeck(deck, discarded);
                Strategies.updateCount(player.getHandList().getHandsList().get(0).getCardValue(i));
            }
            Strategies.updateCount(dealerHand.getCardValue(1));


            //Check if dealer has BlackJack to start
            if (dealer.hasBlackjack(dealerHand)) {
                //Check if the player also has BlackJack
                if (player.hasBlackjack(player.getHandList().getHandsList().get(0))) {
                    //End the round with a push
                    System.out.println("Push");
                    pushes++;
                    //start a new round
                    player.getHandList().removeHand(0, discarded);
                    break;
                } else {
                    losses++;
                    System.out.println("loss");
                    //player lost, start a new round
                    player.getHandList().removeHand(0, discarded);
                    break;
                }
            }



            //Check if player has blackjack to start
            //If we got to this point, we already know the dealer didn't have blackjack
            if (player.hasBlackjack(player.getHandList().getHandsList().get(0))) {
                System.out.println("wins");
                wins++;
                player.getHandList().removeHand(0, discarded);
                break;
            }

            System.out.println(player.getHandList().getHandsList().size());
            // Hanterar om split så inget error händer
            ArrayList<Hand> splithands = new ArrayList<Hand>();

            // Lägg en while loop runt detta:
            boolean checkSplit = true;
            while(checkSplit) {
                for (Hand hand : player.getHandList().getHandsList()) {
                    Strategies.splitStrategy(hand, dealerHand, willSplit, deck, discarded, player);
                    if (hand.canSplit() && willSplit) {
                        splithands.add(hand.copyHand());

                    }
                }
                if (splithands.size() >= 1) {
                    for (Hand hand : splithands) {
                        player.split(deck, discarded, hand);
                        player.getHandList().getHandsList().remove(hand);
                    }
                }
                splithands.clear();
                for (Hand hand : player.getHandList().getHandsList()){
                    if (!hand.canSplit())
                        checkSplit = false;
                }
            }



            System.out.println(player.getHandList().getHandsList().size());
            //Let the player decide what to do next
            boolean allMatch = false;
            for (Hand hand : player.getHandList().getHandsList()) {

                player.makeDecision(deck, discarded, hand, dealerHand);

                //Check if they busted
                if (hand.calculatedValue() > 21) {
                    System.out.println("loss");
                    losses++;
                    discarded.addCards(hand.getHandCards());
                    bustedHands.add(hand);
                    // Check if all other objects in the list are equal to the first object
                    if(player.getHandList().getHandsList().size() >= 2)
                        for (int i = 1; i < player.getHandList().getHandsList().size(); i++) {
                            if (!player.getHandList().getHandsList().get(i).equals(player.getHandList().getHandsList().get(0))) {
                                // If any object is not equal to the first object, return without clearing the list
                                break;
                            } else {
                                allMatch = true;
                            }
                    }
                }
            }

            player.getHandList().getHandsList().removeAll(bustedHands);
            if(allMatch){
                // If all objects are equal to the first object, clear the list and start new round
                break;
            }



            //Remove hands that have busted




            //Dealer's turn
            while (dealerHand.calculatedValue() < 17) {
                dealer.hit(deck, discarded, dealerHand);
            }


            for (Hand hand : player.getHandList().getHandsList()){
                //Check who wins and count wins, losses and pushes
                if (dealerHand.calculatedValue() > 21) {
                    System.out.println("win");
                    wins++;
                } else if (dealerHand.calculatedValue() > hand.calculatedValue()) {
                    System.out.println("loss");
                    losses++;
                } else if (hand.calculatedValue() > dealerHand.calculatedValue()) {
                    System.out.println("win");
                    wins++;
                } else {
                    System.out.println("Push");
                    pushes++;
                }
                discarded.addCards(hand.getHandCards());
            }



            player.getHandList().getHandsList().clear();


            roundOver = true;
        }

    }
    public Hand getDealerHand(){
        return dealerHand;
    }
}
