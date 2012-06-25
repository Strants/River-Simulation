/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package riversim.vehicles;

/**
 *
 * @author gavin
 */
public abstract class WaterVehicle {
    
    private final double speed;
    private int nightsRested;
    
    
    public WaterVehicle(double speed) {
        this.speed = speed;
        nightsRested = 0;
    }
    
    public int maxMove(double stopDistance) {
        return (int) Math.floor(12 * speed / stopDistance);
    }
    
}
