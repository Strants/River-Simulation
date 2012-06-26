package riversim;

import riversim.vehicles.Boat;
import riversim.vehicles.Raft;
import riversim.vehicles.WaterVehicle;

/**
 *
 * @author gavin
 */
public class River {

    private final int numberStops;
    private WaterVehicle[] stops;
    private int numberTrips;
    private int numberDays;
    private final int maxBoats;
    private int numberBoats;
    private static final int MAX_DAYS = 180;

    public River(int numberStops, int maxBoats) {
        this.numberStops = numberStops;
        this.maxBoats = maxBoats;
        stops = new WaterVehicle[numberStops];
        numberTrips = 0;
        numberDays = 0;
        numberBoats = 0;
    }

    private int maxMove(WaterVehicle v, int stopNum) {
        return Math.min(numberStops - 1, Math.min(v.maxMove(225. / ((double) (numberStops + 1))) + stopNum, numberStops - 5 + v.getNightsRested()));
    }

    public void dayCycle() {
        for (int i = stops.length - 1; i >= 0; i--) {
            /*
             * Ignore empty stops.
             */
            if (stops[i] == null) {
                continue;
            }

            if (maxMove(stops[i], i + 1) > numberStops) {
                /*
                 * If a boat can finish, take it out of the array and increment the number of trips completed.
                 */
                if (stops[i] instanceof Boat) {
                    numberBoats--;
                }
                stops[i] = null;
                numberTrips++;
            } else {
                /*
                 * Otherwise, check for the farthest position the boat can go and send it there.
                 */
                int targetPosition = maxMove(stops[i], i + 1);
                while (stops[targetPosition] != null && targetPosition > i) {
                    targetPosition--;
                }
                stops[targetPosition] = stops[i];
                stops[i] = null;
                stops[targetPosition].sleep();
            }
        }
        while (numberBoats < maxBoats) {
            int i;
            for (i = maxMove(new Boat(), 0); i > 0 && stops[i - 1] != null; i--) {
                //Cycle through all the positions a boat at the start could move to.
            }
            if (i == 0) {
                //If the boat cannot move.
                break;
            } else {
                stops[i - 1] = new Boat();
                numberBoats++;
                stops[i - 1].sleep();
                //System.out.println("Boat added");
            }
        }
        while (true) {
            int i;
            for (i = maxMove(new Raft(), 0); stops[i] != null && i > 0; i--) {
                //Cycle through all the positions a raft at the start could move to.
            }
            if (i == 0) {
                //If the raft cannot move.
                break;
            } else {
                stops[i - 1] = new Raft();
                stops[i - 1].sleep();
            }
        }
        numberDays++;
    }

    public void printRiver() {
        System.out.println("Day " + numberDays + "\n");
        for (int i = 0; i < stops.length; i++) {
            if (stops[i] == null) {
                System.out.println(i +":");
            } else {
                System.out.println(i + ":\t" + ((stops[i] instanceof Boat) ? ("B\t") : ("R\t")) + stops[i].getNightsRested());
            }
        }
        System.out.println("\nBoats finished: " + numberTrips);
    }

}
