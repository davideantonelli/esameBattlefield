package battlefield;



import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;


public class RobotOrdinatiPerPosizioneTest {
    /*
    (i) campo vuoto
(ii) campo contenente un singolo robot
(iii) campo contenente due robot in posizioni di X diversa
(iv) campo contenente due robot in posizione di pari X ma Y diversa
(v) campo contenente quattro robot
     */

    private Battlefield field;

    private Position origine;
    private Position unitari;

    @Before
    public void setUp() throws Exception {
        this.field = new Battlefield(2);;
    }

    @Test
    public  void testCampoVuoto(){
        assertEquals(0,this.field.getRobotOrdinatiPerPosizione().size());
    }

    @Test
    public void contieneUnRobot(){
        this.field.addRobot(new Walker(new Position(0,0)));
        assertEquals(1,this.field.getRobotOrdinatiPerPosizione().size());
    }

    @Test
    public void contiene2RobotXdiversa(){
        this.field.addRobot(new Walker(new Position(0,0)));
        this.field.addRobot(new Walker(new Position(1,0)));
        assertNotEquals(this.field.getRobotOrdinatiPerPosizione().get(0).getPosizione().getX(),this.field.getRobotOrdinatiPerPosizione().get(1).getPosizione().getX());
    }

    @Test
    public void contiene2RobotStessaXdiversaY(){
        this.field.addRobot(new Walker(new Position(0,0)));
        this.field.addRobot(new Walker(new Position(0,1)));
        assertEquals(this.field.getRobotOrdinatiPerPosizione().get(0).getPosizione().getX(),this.field.getRobotOrdinatiPerPosizione().get(1).getPosizione().getX());
        assertNotEquals(this.field.getRobotOrdinatiPerPosizione().get(0).getPosizione().getY(),this.field.getRobotOrdinatiPerPosizione().get(1).getPosizione().getY());
    }

    @Test
    public void contiene4Robots(){
        this.field.addRobot(new Walker(new Position(0,0)));
        this.field.addRobot(new Chaser(new Position(0,1)));
        this.field.addRobot(new Chaser(new Position(1,0)));
        this.field.addRobot(new Walker(new Position(1,1)));
        this.field.addRobot(new Walker(new Position(1,2)));//posizione inesistente
        assertEquals(4,this.field.getAllRobot().size());

    }
}
