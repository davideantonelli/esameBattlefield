package battlefield;

public class Chaser extends Robot {

	public Chaser(Position p) {
		super(p);// eredito la variabile p dalla superclasse Robot
	}
	
	
	public void passo(Battlefield field) {
		Position nuova = this.decidiMossa(field);
		if (nuova!=null) {
			Chaser clone = new Chaser(nuova);
			field.addChaser(clone);
		}
		this.incrementaLongevita();
	}
	
	public Position decidiMossa(Battlefield field) {
		Walker inseguito = cercaAvversario(field);
		if (inseguito==null) 
			return null; /* nessuno da inseguire: stai fermo */
		else return inseguito.getPosizione();
	}

	private Walker cercaAvversario(Battlefield field) {
		for(Position p : field.adiacenti(this.getPosizione())) {
			Walker vicino = field.getWalker(p);
			if (isAvversario(vicino)) {
				return vicino;
			}
		}
		return null;
	}

	private boolean isAvversario(Object avvistato) {
		return true ; /* è sicuramente un Walker??? per ora SI! */
	}

}

