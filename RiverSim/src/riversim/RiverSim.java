/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package riversim;

import java.util.ArrayList;
import riversim.vehicles.Boat;
import riversim.vehicles.WaterVehicle;

/**
 *
 * @author gavin
 */
public class RiverSim {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        River r = new River(18, 18);
        ArrayList<WaterVehicle[]> pastStates =new ArrayList<WaterVehicle[]>();
        WaterVehicle[] state;
        int loopStart = 0;
        int loopEnd = 0;
        boolean equal = false;
        while(!equal) {
            for(int i = 0; i < pastStates.size(); i++) {
                boolean currentEqual = true;
                for(int j = 0; j < r.getStops().length; j++) {
                    if(pastStates.get(i)[j] != r.getStops()[j]) {
                        currentEqual = false;
                        break;
                    }
                }
                if(currentEqual) {
                    System.out.println("Equal");
                    loopStart = i;
                    loopEnd = pastStates.size();
                    equal = true;
                    break;
                }
            }
            pastStates.add(r.getStops());
            r.dayCycle();
            r.printRiver();
        }
        System.out.println("First loop: ");
        state = pastStates.get(loopStart);
        for(int i = 0; i < state.length; i++) {
            if(state[i] == null) {
                System.out.println((i+1) + ":");
            } else {
                System.out.println((i+1) + ":\t" + ((state[i] instanceof Boat)?("B\t"):("R\t")) + state[i].getNightsRested());
            }
        }
        System.out.println("Second loop: ");
        r.printRiver();
        System.out.println("Loop Time is:" + (loopEnd - loopStart));
        System.out.println("PastStates len: " + pastStates.size());
    }
    
}
