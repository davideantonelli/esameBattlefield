package battlefield;

public interface Robot {
	
	void robot(Position p) ;
	Position RobotgetPosizione();
	int RobotIncrementaLongevit�();
	int getLongevit�();
	int passo(Battlefield field);
	
}
