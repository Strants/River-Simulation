package riversim;


/**
 *
 * @author gavin
 */
public class RiverSim {
    
    public static void main(String[] args) {
        River[] rivers = new River[112-5];
        //Populate rivers
        for(int i = 6; i <= 112; i++) {
            rivers[i-6] = new River(i, i);
        }
        //Cycle 180 days
        for(int i = 0; i < 180; i++) {
            for(River r : rivers) {
                r.dayCycle();
            }
        }
        System.out.println("Y , Number of trips");
        for(int i = 0; i < 112 - 5; i++) {
            System.out.println((i+6) + "," + rivers[i].getNumberTrips());
        }
    }

}
