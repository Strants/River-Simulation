/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package riversim.vehicles;

/**
 *
 * @author gavin
 */
public class Raft extends WaterVehicle implements Cloneable {

    public Raft() {
        super(4);
    }

    @Override
    public WaterVehicle clone() {
        Raft r = new Raft();
        while(r.getNightsRested() != getNightsRested()) {
            r.sleep();
        }
        return r;
    }
    
    
}
