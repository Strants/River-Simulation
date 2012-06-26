package riversim;

import riversim.vehicles.WaterVehicle;

/**
 *
 * @author gavin
 */
public class River {
    
    private  final int numberStops;
    private WaterVehicle[] stops;
    private int numberTrips;
    private int numberDays;
    private static final int MAX_DAYS = 180;

    public River(int numberStops) {
        this.numberStops = numberStops;
        stops = new WaterVehicle[numberStops];
        numberTrips = 0;
        numberDays = 0;
    }
    
    private int maxMove(WaterVehicle v) {
        return Math.max(v.maxMove(225./((double)(numberStops+1))), numberStops - 5 + v.getNightsRested());
    }
}
