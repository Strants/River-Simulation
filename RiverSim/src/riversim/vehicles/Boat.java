/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package riversim.vehicles;

/**
 *
 * @author gavin
 */
public class Boat extends WaterVehicle implements Cloneable {
    
    public Boat() {
        super(8);
    }

    @Override
    public WaterVehicle clone() {
        Boat b = new Boat();
        while(b.getNightsRested() != getNightsRested()) {
            b.sleep();
        }
        return b;
    }
    
   
}
