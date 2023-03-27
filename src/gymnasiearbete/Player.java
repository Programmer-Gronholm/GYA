package gymnasiearbete;

public class Player extends Person{

    public void makeDecision(Deck deck, Deck discard) {
        //Coming Soon
    }
    public boolean hasBlackjack(){
        if(this.getHand().calculatedValue() == 21){
            return true;
        }
        else{
            return false;
        }
    }
}
