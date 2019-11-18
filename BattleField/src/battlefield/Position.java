package battlefield;

import java.util.HashSet;
import java.util.Set;

/*
 * Da sistemare. Vedi PositionTest.
 * (vedi DOMANDA 1)
 */
public class Position  {
	private int x, y;
	 public Set<Position> posizione;//dichiaro un insieme di tipo Position
	
	public Position(int x, int y){
		this.x = x;
		this.y = y;
		posizione = new HashSet<Position>(); //inizializzo l'insieme posizione a nuovo Hashset
		posizione.add(this);//aggiungo questa posizione all'HASHSET
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