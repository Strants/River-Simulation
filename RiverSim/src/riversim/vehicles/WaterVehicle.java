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
    
    public void sleep() {
        nightsRested++;
    }

    public int getNightsRested() {
        return nightsRested;
    }

    public double getSpeed() {
        return speed;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final WaterVehicle other = (WaterVehicle) obj;
        if (Double.doubleToLongBits(this.speed) != Double.doubleToLongBits(other.speed)) {
            return false;
        }
        if (this.nightsRested != other.nightsRested) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + (int) (Double.doubleToLongBits(this.speed) ^ (Double.doubleToLongBits(this.speed) >>> 32));
        hash = 83 * hash + this.nightsRested;
        return hash;
    }
    
    
}
