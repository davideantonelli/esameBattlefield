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

	public Robot getRobot(Position p){
		return posizione2Robot.get(p); 
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

	public Collection<Robot> getAllWalkers() {
		return this.raggruppaRobotPerTipo().get(Walker.class);
	}
	
	// Ottengo una collection di tutti i robot sil campo.
	public Collection<Robot> getAllRobot() {
		return this.posizione2Robot.values();
	}
	public Collection<Robot> getAllChasers() {
		return this.raggruppaRobotPerTipo().get(Chaser.class);
	}

	@SuppressWarnings("rawtypes")
	public Map<Class, Set<Robot>> raggruppaRobotPerTipo() {
		//Trovo tutti i robot sulla mappa
		Collection<Robot> allRobot = this.getAllRobot();
		Set<Robot> tmpSetOfRobot; //lista temporanea dei robot che sarà valore della View con chiave tipo
        Map<Class, Set<Robot>> viewRobotPerTipo = new HashMap<Class, Set<Robot>>(); // Dichiaro la mappa di tipo hashmap		
        //scansione posizioni
        for(Robot r: allRobot ){
            //ESISTE GIA questo tipo nel Set?
            if (viewRobotPerTipo.containsKey(r.getClass())){
                //estraggo il valore associato alla chiave
                //OVVERO IL SET DI ROBOT PRE-ESISTENTI
                tmpSetOfRobot = viewRobotPerTipo.get(r.getClass());
                //e attuo il metodo add del Set per aggiungere il nuovo robot
                tmpSetOfRobot.add(r); //aggiungo nella lista del suo set
                //VERSIONE ABBREVIATA
                viewRobotPerTipo.get(r.getClass()).add(r);
            } else{
                //alloco un nuovo set
                tmpSetOfRobot = new HashSet<Robot>();
                tmpSetOfRobot.add(r);
            }
            //update della chiave con il set dati
            viewRobotPerTipo.put(r.getClass(),tmpSetOfRobot);
        }

        return viewRobotPerTipo;
	}
	
	 public List<Robot> getRobotOrdinatiPerPosizione() {
	        // (vedi DOMANDA 4)
	        ComparatoreRobotPerPosizione comparator= new ComparatoreRobotPerPosizione();
	        //---conoversione da Collection ad Arraylist tramite costruttore
	        ArrayList<Robot> robotOrdinatiPerPosizione = new ArrayList<Robot>(this.getAllRobot());
	        Collections.sort(robotOrdinatiPerPosizione,comparator);

	        return robotOrdinatiPerPosizione;
	    }

	    public SortedSet<Robot> getRobotOrdinatiPerLongevita() {
	        // (vedi DOMANDA 6)

	        SortedSet<Robot> l = new TreeSet<Robot>();

	        for(Robot r: this.posizione2Robot.values()){
	            l.add(r);

	        }
	        return l;
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
	        return ( this.getRobot(posizione)== null);
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
	                       // this.addChaser(chaser);
	                        this.addRobot(chaser);
	                        break;
	                    case 1: Walker walker = new Walker(posizione);
	                       // this.addWalker(walker);
	                        this.addRobot(walker);
	                        break;
	                    //case: NUMERO_TIPOLOGIE-1...
	                    default: throw new IllegalStateException();

	                }
	            }
	        }
	    }



	}