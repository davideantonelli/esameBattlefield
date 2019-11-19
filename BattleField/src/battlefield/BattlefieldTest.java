package battlefield;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import java.util.List;

/* Modificare la classe Position affinche'
 * il primo test abbia successo (vedi DOMANDA 1)
 */
public class BattlefieldTest {

    private Battlefield field;

    @Before
    public void setUp() throws Exception {
        this.field = new Battlefield(2);
    }

    @Test
    public void testAddWalker() {
        assertEquals(0, this.field.getAllWalkers().size());
        this.field.addWalker(new Walker(new Position(0,0)));
        assertEquals(1, this.field.getAllWalkers().size());
    }

    @Test
    public void testRaggruppaRobotDiDueTipiDiversi() {
       // fail("vedi DOMANDA 3");
        this.field.addChaser(new Chaser(new Position(10,5)));
        this.field.addWalker(new Walker(new Position(11,6)));
        assertEquals(2, this.field.raggruppaRobotPerTipo().size());
        assertNotEquals(3, this.field.raggruppaRobotPerTipo().size());
    }

    @Test
    public void RobotOrdinatiPerPosizioneTest(){
        List<Robot> robotPosOrder = this.field.getRobotOrdinatiPerPosizione();
        //1) test campo vuoto
        assertEquals(0, robotPosOrder.size());
        //2) campo contenente un singolo robot
        this.field.addRobot(new Chaser(new Position(10,5)));
        robotPosOrder = this.field.getRobotOrdinatiPerPosizione();
        assertEquals(1, robotPosOrder.size());
        //3 campo contenente due robot in posizioni di X diversa
        this.field.addChaser(new Chaser(new Position(11,6)));
        robotPosOrder = this.field.getRobotOrdinatiPerPosizione();
        assertEquals(2, robotPosOrder.size());
//        assertNotEquals(robotPosOrder.get(0).getPosizione().getX(),
//                            robotPosOrder.get(1).getPosizione().getX());
        assertFalse(robotPosOrder.get(0).equals(robotPosOrder.get(1)));
        //4 campo contenente due robot in posizione di pari X ma Y diversa
        this.field.addChaser(new Chaser(new Position(10,7)));
        robotPosOrder = this.field.getRobotOrdinatiPerPosizione();
        assertEquals(3, robotPosOrder.size());

        this.field.addChaser(new Chaser(new Position(3,6)));
        robotPosOrder = this.field.getRobotOrdinatiPerPosizione();
        assertFalse("Attenzioni 2 robot con diversa Y che risultano uguali",
                            robotPosOrder.get(2).equals(robotPosOrder.get(3)));
        assertEquals(4, robotPosOrder.size());


    }

}