package battlefield;

import java.util.*;

public class Battlefield {

	static final private Class<?>[] TIPOLOGIE = { Walker.class, Chaser.class } ;

	static final private int NUMERO_TIPOLOGIE = TIPOLOGIE.length;

	static final private double OCCUPAZIONE_INIZIALE = 0.01d; // 1%

	private int dim;

	private Random random;
	
	private Map<Position, Robot> posizione2Robot;
	

	public Battlefield(int dimensione) {
		this.dim = dimensione;
		this.posizione2Robot = new HashMap<>();
		
		this.random = new Random();
	}

	
	public void addRobot(Robot r) { // costruttore utile per aggiungere il robot
		if (inCampo(r.posizione)) {
			posizione2Robot.put(r.posizione, r);
			
		}
	}
	
	
	
	
	
	public void addWalker(Walker w) {
		addRobot(w);
	}

	public void addChaser(Chaser c) {
		addRobot(c);
	}

	public Walker getWalker(Position p) {
		if (posizione2Robot.containsKey(p)) { // controllo che la posizione2Robot abbia le coordinate passate
			if (posizione2Robot.get(p).getClass()==Walker.class) {// mi assicuro che posizione2Robot sia di tipo Walker
				return (Walker) posizione2Robot.get(p);// Ritorno la posizione del Walker CASTTTTTTTTTT
			}
		}
		return null;
	}

	public Chaser getChaser(Position p) {
		if (posizione2Robot.containsKey(p)) { // controllo che la posizione2Robot abbia le coordinate passate
			if (posizione2Robot.get(p).getClass()==Chaser.class) {// mi assicuro che posizione2Robot sia di tipo Chaser
				return (Chaser) posizione2Robot.get(p);// Ritorno la posizione del Walker CASTTTTTTTTTT
			}
		}
		return null;
	}

	public Collection<Walker> getAllWalkers() {
		return this.posizione2walker.values();
	}
	
	// Ottengo una collection di tutti i robot sil campo.
	public Collection<Robot> getAllRobot() {
		return this.posizione2Robot.values();
	}
	public Collection<Chaser> getAllChasers() {
		return this.posizione2chaser.values();
	}

	@SuppressWarnings("rawtypes")
	public Map<Class, Set<Robot>> raggruppaRobotPerTipo() {
		//Trovo tutti i robot sulla mappa
		Collection<Robot> allRobot = this.getAllRobot();
				
		return null;
	}
	
	public List<?> getRobotOrdinatiPerPosizione() {
		// (vedi DOMANDA 4)
		return null;
	}
	
	public SortedSet<?> getRobotOrdinatiPerLongevita() {
		// (vedi DOMANDA 6)
		return null;
	}
	
	public List<Position> adiacenti(Position perno) {
		final List<Position> adiacenti = new LinkedList<>();
		int x = perno.getX();
		int y = perno.getY();

		for(int i = -1; i<2; i++) {
			for(int j = -1; j<2; j++) {
				Position adiacente = new Position(x+i, y+j);
				if (inCampo(adiacente))
					adiacenti.add(adiacente);
			}
		}
		adiacenti.remove(perno);  // coincidono: quindi non sono adiacenti
		
		Collections.shuffle(adiacenti); /* ordine casuale */
		return adiacenti;
	}

	private boolean inCampo(Position p) {
		return  p.getX()>=0 && p.getX()<this.dim && 
				p.getY()>=0 && p.getY()<this.dim  ;
				
	} 

	public Position posizioneLiberaVicino(Position posizione) {
		for(Position p : this.adiacenti(posizione)) {
			if (this.isLibera(p)) {
				return p;
			}
		}
		return null;
	}

	public boolean isLibera(Position posizione) {
		return ( this.getWalker(posizione)==null && this.getChaser(posizione)==null);
	}

	public int getDimensione() {
		return this.dim;
	}

	public void riempi() {
		long numeroIniziale = Math.round(OCCUPAZIONE_INIZIALE * dim * dim);
		for(int i=0 ; i<numeroIniziale; i++) {
			int x = this.random.nextInt(this.dim);
			int y = this.random.nextInt(this.dim);
			Position posizione = new Position(x, y);
			if (this.isLibera(posizione)) {
				switch (this.random.nextInt(NUMERO_TIPOLOGIE)) {
				case 0: Chaser chaser = new Chaser(posizione);
						this.addChaser(chaser);
				break;
				case 1: Walker walker = new Walker(posizione);
						this.addWalker(walker);
				break;
				//case: NUMERO_TIPOLOGIE-1...
				default: throw new IllegalStateException();
				} 
			}
		}
	}

}
