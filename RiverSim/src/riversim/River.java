package riversim;

import java.util.ArrayList;
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
    private final int maxRafts;
    private int numberRafts;
    private int numberBoats;
    private static final int MAX_DAYS = 180;

    public River(int numberStops, int maxBoats) {
        this.numberStops = numberStops;
        this.maxBoats = maxBoats;
        maxRafts = numberStops - maxBoats;
        stops = new WaterVehicle[numberStops];
        numberTrips = 0;
        numberDays = 0;
        numberBoats = 0;
    }

    private int maxMove(WaterVehicle v, int stopNum) {
        return Math.min(numberStops, Math.min(v.maxMove(225. / ((double) (numberStops + 1))) + stopNum, numberStops - 6 + v.getNightsRested()));
    }

    public void dayCycle() {
        for (int i = stops.length - 1; i >= 0; i--) {
            if (stops[i] == null) {
                /*
                 * Ignore unoccupied stops.
                 */
                continue;
            } else if (maxMove(stops[i], i) > numberStops - 1) {
                /*
                 * If a boat can complete the journey. 
                 */
                if (stops[i] instanceof Boat) {
                    numberBoats--;
                }
                numberTrips++;
                stops[i] = null;
            } else {
                /*
                 * Cycle through moves until we find a target move we can move to.
                 */
                int targetMove;
                for (targetMove = maxMove(stops[i], i); targetMove > i && stops[targetMove] != null; targetMove--);
                stops[targetMove] = stops[i].clone();
                stops[targetMove].sleep();
                if (i != targetMove) {
                    stops[i] = null;
                }
            }
        }
        /*
         * All boats currently on the river have moved, so we add new boats.
         */
        while (numberBoats < maxBoats) {
            Boat b = new Boat();
            int targetSpace = maxMove(b, -1);
            for (; targetSpace >= 0 && stops[targetSpace] != null; targetSpace--);
            if (targetSpace < 0) {
                /*
                 * If the boat cannot move from the start
                 */
                break;
            } else {
                numberBoats++;
                stops[targetSpace] = b;
                stops[targetSpace].sleep();
            }
        }
        while (numberRafts < maxRafts) {
            Raft r = new Raft();
            int targetSpace = maxMove(r, -1);
            for (; targetSpace >= 0 && stops[targetSpace] != null; targetSpace--);
            if (targetSpace < 0) {
                /*
                 * If the boat cannot move from the start
                 */
                break;
            } else {
                numberRafts++;
                stops[targetSpace] = r;
                stops[targetSpace].sleep();
            }
        }
        numberDays++;
    }

    public void printRiver() {
        System.out.println("Day " + numberDays + "\n");
        for (int i = 0; i < stops.length; i++) {
            if (stops[i] == null) {
                System.out.println((i + 1) + ":");
            } else {
                System.out.println((i + 1) + ":\t" + ((stops[i] instanceof Boat) ? ("B\t") : ("R\t")) + stops[i].getNightsRested());
            }
        }
        System.out.println("\nBoats finished: " + numberTrips);
    }

    public int getNumberDays() {
        return numberDays;
    }

    public int getNumberTrips() {
        return numberTrips;
    }

    public WaterVehicle[] getStops() {
        return (WaterVehicle[]) stops.clone();
    }

    /**
     * A method to find the time until a boating schedule begins to loop on a river with a certain number of stops.
     * @param numberStops The number of stops on the river.
     */
    //TODO: Add capability to add more rafts.
    public static void loopFinder(int numberStops) {
        River r = new River(numberStops, numberStops);
        ArrayList<WaterVehicle[]> pastStates = new ArrayList<WaterVehicle[]>();
        int loopStart = 0;
        int loopEnd = 0;
        boolean equal = false;
        while (!equal) {
            for (int i = 0; i < pastStates.size(); i++) {
                boolean currentEqual = true;
                for (int j = 0; j < r.getStops().length; j++) {
                    if (pastStates.get(i)[j] == null && r.getStops()[j] != null) {
                        /*
                         * If the current state is occupied, and the past state is not.
                         */
                        currentEqual = false;
                        break;

                    }
                    if (!pastStates.get(i)[j].equals(r.getStops()[j])) {
                        /*
                         * If only the past state is occupied, or both occupied by an different water vehicle
                         */
                        currentEqual = false;
                        break;
                    }
                }
                if (currentEqual) {
                    loopStart = i;
                    loopEnd = pastStates.size();
                    equal = true;
                    break;
                }
            }
            pastStates.add(r.getStops());
            if (!equal) {
                r.dayCycle();
            }
        }
        System.out.println("Loop Time is:" + (loopEnd - loopStart));
        System.out.println("Preloop Time is: " + loopStart);
    }

}
