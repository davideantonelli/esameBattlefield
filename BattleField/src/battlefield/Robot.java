package battlefield;

public interface Robot {
	
	void robot(Position p) ;
	Position RobotgetPosizione();
	int RobotIncrementaLongevità();
	int getLongevità();
	int passo(Battlefield field);
	
}
