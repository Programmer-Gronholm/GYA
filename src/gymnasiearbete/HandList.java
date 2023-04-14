package gymnasiearbete;

import java.util.ArrayList;

public class HandList {

    private ArrayList<Hand> handsList;

    public HandList() {
        this.handsList = new ArrayList<Hand>();
    }

    public void addHand(Hand hand) {
        handsList.add(hand);
    }

    public void removeHand(int idx, Deck discard) {
        handsList.get(idx).discardHandToDeck(discard);
        handsList.remove(idx);
    }

    public ArrayList<Hand> getHandsList() {
        return handsList;
    }
}

