package battlefield;

public class Chaser implements Robot {

	private Position posizione;
	private int longevita;

	
	public Position decidiMossa(Battlefield field) {
		Walker inseguito = cercaAvversario(field);
		if (inseguito==null) 
			return null; /* nessuno da inseguire: stai fermo */
		else return inseguito.getPosizione();
	}

	private Walker cercaAvversario(Battlefield field) {
		for(Position p : field.adiacenti(this.RobotgetPosizione())) {
			Walker vicino = field.getWalker(p);
			if (isAvversario(vicino)) {
				return vicino;
			}
		}
		return null;
	}

	private boolean isAvversario(Object avvistato) {
		return true ; /* Ã¨ sicuramente un Walker??? per ora SI! */
	}

	@Override
	public void robot(Position p) {
		this.posizione = p;
		this.longevita = 0 ;
	}

	@Override
	public Position RobotgetPosizione() {
		
		return this.posizione;
	}

	@Override
	public int RobotIncrementaLongevità() {
		
		return ++this.longevita;
		
	}

	@Override
	public int getLongevità() {
		return this.longevita;
	}

	@Override
	public int passo(Battlefield field) {
		Position nuova = this.decidiMossa(field);
		if (nuova!=null) {
			Chaser clone = new Robot(nuova);
			field.addChaser(clone);
		}
		this.RobotIncrementaLongevità();
	}

}

