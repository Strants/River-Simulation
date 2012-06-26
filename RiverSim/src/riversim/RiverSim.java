/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package riversim;

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
        for(int i = 0; i < 18; i++) {
            r.dayCycle();
            r.printRiver();
        }
    }
    
}
