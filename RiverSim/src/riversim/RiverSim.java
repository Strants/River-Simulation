package riversim;

/**
 *
 * @author gavin
 */
public class RiverSim {

    public static void main(String[] args) {
        for(int i = 6; i < 20; i++) {
            System.out.println("For Y = " + i);
            River.loopFinder(i);
            System.out.println();
        }
        /*
         * KLUDGE COMMENT
         */
        if(true) {
            return;
        }
        River test = new River(12,12,6);
        for(int i = 0; i < 15; i++) {
            test.dayCycle();
            System.out.println("For " + (i+1) + "  on a 6 cycle, X = " + test.getNumberTrips());
        }
        
        if(true) {
            return;
        }
        River[][] rivers = new River[112 - 5][18 - 5];
        //Populate rivers
        for (int i = 6; i <= 112; i++) {
            for (int j = 6; j <= 18; j++) {
                rivers[i - 6][j - 6] = new River(i, i, j);
            }
        }
        //Cycle 180 days
        for (int i = 0; i < 180; i++) {
            for (River[] rArray : rivers) {
                for (River r : rArray) {
                    r.dayCycle();
                }
            }
        }
        System.out.print("Y");
        for(int i = 6; i <= 18; i++) {
            System.out.print(",\"Cycle" + i +"\"");
        }
        System.out.println();
        for (int i = 0; i < 112 - 5; i++) {
            System.out.print((i + 6));
            for (int j = 0; j <= 18 - 6; j++) {
                System.out.print("," + rivers[i][j].getNumberTrips());
            }
            System.out.println();
        }
    }

}
