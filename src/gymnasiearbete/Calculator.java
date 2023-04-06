package gymnasiearbete;

public class Calculator {
    public double calWinPrc(int wins, int losses, int pushes){
        double prcWins= 0;
        prcWins = (double)wins/(losses+pushes+wins);
        prcWins *= 100;
        return prcWins;
    }

    public double calNoLossPrc(int wins, int losses, int pushes){
        double prcNoLoss= 0;
        prcNoLoss = (double) (wins+pushes)/(losses+pushes+wins);
        prcNoLoss *= 100;
        return prcNoLoss;
    }


}
