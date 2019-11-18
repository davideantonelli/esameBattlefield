/**
 * 
 */
package battlefield;

/**
 * @author Davide
 * Questa classe è una classe astratta che generalizza i comportamenti dei Robot. 
 */

public abstract class Robot {
	
	 Position posizione;
	 int longevita;
	 
	 Robot (Position p) {
			posizione = p;
			this.longevita = 0 ;
		}
	public Position getPosizione() {
		return this.posizione;
		
	}
	public int incrementaLongevita() {
		return ++this.longevita;
	}
	public int getLongevita() {
		return this.longevita;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + longevita;
		result = prime * result + ((posizione == null) ? 0 : posizione.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Robot other = (Robot) obj;
		if (longevita != other.longevita)
			return false;
		if (posizione == null) {
			if (other.posizione != null)
				return false;
		} else if (!posizione.equals(other.posizione))
			return false;
		return true;
	}
	public abstract void passo(Battlefield field);
}
