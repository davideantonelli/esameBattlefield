package battlefield;

import java.util.*;

public class Battlefield {

	static final private Class<?>[] TIPOLOGIE = { Walker.class, Chaser.class } ;

	static final private int NUMERO_TIPOLOGIE = TIPOLOGIE.length;

	static final private double OCCUPAZIONE_INIZIALE = 0.01d; // 1%

	private int dim;

	private Random random;
	
	private Map<Position, Walker> posizione2walker;
	private Map<Position, Chaser> posizione2chaser;

	public Battlefield(int dimensione) {
		this.dim = dimensione;
		this.posizione2walker = new HashMap<>();
		this.posizione2chaser = new HashMap<>();
		this.random = new Random();
	}

	public void addWalker(Walker w) {
		this.posizione2walker.put(w.getPosizione(), w);
	}

	public void addChaser(Chaser c) {
		this.posizione2chaser.put(c.getPosizione(), c);
	}

	public Walker getWalker(Position p) {
		return posizione2walker.get(p);
	}

	public Chaser getChaser(Position p) {
		return posizione2chaser.get(p);
	}

	public Collection<Walker> getAllWalkers() {
		return this.posizione2walker.values();
	}

	public Collection<Chaser> getAllChasers() {
		return this.posizione2chaser.values();
	}

	@SuppressWarnings("rawtypes")
	public Map<Class, Set<?>> raggruppaRobotPerTipo() {
		// (vedi DOMANDA 3)
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
