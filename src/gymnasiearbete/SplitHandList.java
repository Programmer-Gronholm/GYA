package gymnasiearbete;

import java.util.ArrayList;

public class SplitHandList {

    private ArrayList<Hand> splitHandsList;

    public SplitHandList() {
        splitHandsList = new ArrayList<Hand>();
    }

    public void addHand(Hand hand) {
        splitHandsList.add(hand);
    }

    public void removeSplitHand(Hand hand) {
        splitHandsList.remove(hand);
    }

    public ArrayList<Hand> getSplitHandsList() {
        return splitHandsList;
    }
}

