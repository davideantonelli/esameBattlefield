package battlefield;

import java.util.Comparator;

public class ComparatoreRobotPerPosizione implements Comparator<Robot> {

    @Override
    public int compare(Robot o1, Robot o2) {
        //X crescenti ed a parità di X, per Y crescenti.
        int result = o1.getPosizione().getX()-o2.getPosizione().getX();
        if (result==0) result = o1.getPosizione().getY()-o2.getPosizione().getY();

        return result;
    }
}
