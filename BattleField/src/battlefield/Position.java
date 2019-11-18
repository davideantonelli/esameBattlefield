package battlefield;

import java.util.HashSet;
import java.util.Set;

/*
 * Da sistemare. Vedi PositionTest.
 * (vedi DOMANDA 1)
 */
public class Position  {
	private int x, y;
	 private Set<Position> posizione;
	
	public Position(int x, int y){
		this.x = x;
		this.y = y;
		posizione = new HashSet<Position>();
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}
		
	public String toString() {
		return this.x+"-"+this.y;
	}

}