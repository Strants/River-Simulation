package riversim;

/**
 *
 * @author gavin
 */
public class RiverSim {
    
    public static void main(String[] args) {
        for(int i = 6; i <= 40; i++) {
            System.out.println("For " + i + " stops: ");
            River.loopFinder(i);
            System.out.println("\n\n");
        }
    }

}
