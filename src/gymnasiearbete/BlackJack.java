package gymnasiearbete;

public class BlackJack {
    private Deck deck, discarded;
    private Dealer dealer;
    private Player player;
    private Hand playerHand;
    private Hand dealerHand;
    private int wins, losses, pushes;

    public BlackJack() {
        //Create a new deck with 52 cards
        deck = new Deck(true);
        //Create a new empty deck
        discarded = new Deck(true);

        //Create the People
        dealer = new Dealer();
        player = new Player();


        //Shuffle the deck and start the first round
        deck.shuffle();
        for (int i = 0; i <= 1000; i++) {
            startRound();
        }
    }

    private void startRound(){
        CardCounting.resetCount();
        boolean roundOver = false;
        playerHand = player.getHand();
        dealerHand = dealer.getHand();

        while(!roundOver) {
            //If this isn't the first round, display the users score and put their cards back in the deck
            if (wins > 0 || losses > 0 || pushes > 0) {
                System.out.println();
                System.out.println("Starting Next Round... Wins: " + wins + " Losses: " + losses + " Pushes: " + pushes);
                dealer.getHand().discardHandToDeck(discarded);
                player.getHand().discardHandToDeck(discarded);
            }

            //Check to make sure the deck has at least 4 cards left to start
            //Technically this could mean there's 1-3 cards left in the deck that are usable, but we'll just reshuffle them and not worry about it
            if (deck.cardsLeft() < 4) {
                //reload the deck from discard pile if we're out of cards
                deck.reloadDeckFromDiscard(discarded);
            }

            //Give the dealer two cards
            //Give the player two cards

            for (int i = 0; i < 2; i++) {
                playerHand.takeCardFromDeck(deck);
                dealerHand.takeCardFromDeck(deck);
                CardCounting.updateCount(playerHand.getCardValue(i));
            }
            CardCounting.updateCount(dealerHand.getCardValue(1));


            //Check if dealer has BlackJack to start
            if (dealer.hasBlackjack()) {
                //Check if the player also has BlackJack
                if (player.hasBlackjack()) {
                    //End the round with a push
                    pushes++;
                    //start a new round
                    break;
                } else {
                    losses++;
                    //player lost, start a new round
                    break;
                }
            }

            //Check if player has blackjack to start
            //If we got to this point, we already know the dealer didn't have blackjack
            if (player.hasBlackjack()) {
                wins++;
                break;
            }

            //Let the player decide what to do next
            //pass the decks in case they decide to hit
            player.makeDecision(deck, discarded);

            //Check if they busted
            if (player.getHand().calculatedValue() > 21) {
                losses++;

                break;
            }

            //Now it's the dealer's turn
            while (dealer.getHand().calculatedValue() < 17) {
                dealer.hit(deck, discarded);
            }

            //Check who wins and count wins or losses
            if (dealer.getHand().calculatedValue() > 21) {
                wins++;
                roundOver = true;
            } else if (dealer.getHand().calculatedValue() > player.getHand().calculatedValue()) {
                losses++;
                roundOver = true;
            } else if (player.getHand().calculatedValue() > dealer.getHand().calculatedValue()) {
                wins++;
                roundOver = true;
            } else {
                pushes++;
                roundOver = true;
            }

        }
    }

}
