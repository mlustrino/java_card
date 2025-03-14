package magic_practica;
	
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;


import java.lang.Math;

public class MainClass {

	public static void main(String[] args) {
		Carta[] cartas = new Carta[22];
        cartas[0] = new Carta("Zorro de 9 colas", 4, 5, 4, 1);
        cartas[1] =  new Carta("Gobblin", 5, 5, 4, 3);
        cartas[2] = new Carta("Guerrero", 6, 4, 5, 4);
        cartas[3] = new Carta("Piedrin", 1, 8, 1, 1);
        cartas[4] = new Carta("Slime", 2, 3, 2, 2);
        cartas[5] =  new Carta("Puerta de Baldur", 0,7,4,1);
        cartas[6] = new Carta("Guiverno", 7, 4, 6, 9);
        cartas[7] = new Carta("Ferrosaurio", 8, 6, 5, 7);
        cartas[8] = new Carta("Balatro Balátrez",6,2,3,5);
        cartas[9] = new Carta("Slime de Oro", 2, 1, 9, 10);
        cartas[10] = new Carta("Gólem de piedra", 7, 8, 10, 1);
        cartas[11] = new Carta("LoboLava",2,4,5,6);
        cartas[12] = new Carta("Lightning ", 5, 6,4,5);
        cartas[13] = new Carta("Cifosoa", 5, 3, 6, 1);
        cartas[14] = new Carta("Minotauro", 3, 2, 2, 6);
        cartas[15] = new Carta("Hacienda",9 , 10, 5, 7);
        cartas[16] = new Carta("Gustavo Fring", 3, 7, 1, 6);
        cartas[17] = new Carta("Rusello", 6, 6,2,4);
        cartas[18] = new Carta("Hombre Menguante",8,8,9,4);
        cartas[19] =  new Carta("Business Mundo",5,5,5,2);
        cartas[20] =  new Carta("Centinela", 2, 8, 5, 3);
        cartas[21] =  new Carta("Segarro", 9, 5, 4, 3);    
        

		ArrayList<JugadorCartas> jugadores_cartas = new ArrayList<JugadorCartas>();
        jugadores_cartas.add(new JugadorCartas("12345678L", "Gervasio", "de León Mora", 3, 20, 20));
        jugadores_cartas.add(new JugadorCartas("11111111H","Margarita","Flores Giménez",3,20,20));
        jugadores_cartas.add(new JugadorCartas("12312312A", "Pedro", "Sanchez", 3, 20, 20));
        jugadores_cartas.add(new JugadorCartas("46464646A", "Pepe", "García García", 3, 20, 20));
        jugadores_cartas.add(new JugadorCartas("88888888Y", "Eustaquio", "Avichuela", 3, 20, 20));
        jugadores_cartas.add(new JugadorCartas("45678934Z", "Cristian" , "Navarro Gonzalez", 3, 20, 20)); 
        jugadores_cartas.add(new JugadorCartas("46845365N","Dolores","Suárez Castillo", 3, 20, 20));
        jugadores_cartas.add(new JugadorCartas("11112222A","Pablo","Suarez",3,20,20)); 
        jugadores_cartas.add(new JugadorCartas("11223344M", "Ezequiel", "De todos los santos", 3, 20, 20));
        jugadores_cartas.add(new JugadorCartas("76547821D", "Marcos", "Fernández Martín", 3, 20, 20));
        jugadores_cartas.add(new JugadorCartas("84267193J","Inmaculada","Ponce",3,20,20));
		
		/**
		 * 
		 * 
		 * */
		ContenedorJugadoresCartas contenedor_jugadores_cartas = new ContenedorJugadoresCartas();
		Collections.shuffle(jugadores_cartas);
		contenedor_jugadores_cartas.AddJugadores(jugadores_cartas );
		


		ContenedorCartas contenedor_cartas = new ContenedorCartas();
		for (int i= 0; i< cartas.length; i++) {
			contenedor_cartas.AddCarta(cartas[i]);
		}
		PartidaCartas pc = new PartidaCartas(contenedor_jugadores_cartas, contenedor_cartas);
		pc.repartir_cartas();
		pc.iniciar_juego();
		
		
		System.out.println("**************ultimos 6 jugadores****************");
		
		for (JugadorCartas jcartas :jugadores_cartas) {
			System.out.println(jcartas);
		}
		
	}

}



class JugadorCartas extends Jugador{
	private int numero_cartas;
	private Carta[] cartas_jugador;
	private int mana;
	private int vida;
	private int vida_inicial;
	private int mana_inicial;
	public JugadorCartas(String dni, String nombre, String apellidos, int numero_cartas, Carta[] cartas_jugador,
			int mana, int vida) {
		super(dni, nombre, apellidos);
		this.numero_cartas = numero_cartas;
		this.cartas_jugador = cartas_jugador;
		this.mana = mana;
		this.vida = vida;
		vida_inicial = vida;
		mana_inicial = mana;
	}
	public JugadorCartas(String dni, String nombre, String apellidos, int numero_cartas, int mana, int vida) {
		super(dni, nombre, apellidos);
		this.numero_cartas = numero_cartas;
		this.mana = mana;
		this.vida = vida;
		vida_inicial = vida;
		mana_inicial = mana;
		cartas_jugador = new Carta[numero_cartas];
		
	}
	public void resetear_vida() {
		vida = vida_inicial;
	}
	public void resetear_mana() {
		mana = mana_inicial;
	}
	public int getNumero_cartas() {
		return numero_cartas;
	}
	public void setNumero_cartas(int numero_cartas) {
		this.numero_cartas = numero_cartas;
	}
	public Carta[] getCartas_jugador() {
		return cartas_jugador;
	}
	public void setCartas_jugador(Carta[] cartas_jugador) {
		this.cartas_jugador = cartas_jugador;
	}
	public int getMana() {
		return mana;
	}
	public void setMana(int mana) {
		this.mana = mana;
	}
	public int getVida() {
		return vida;
	}
	public void setVida(int vida) {
		this.vida = vida;
	}
	public Carta getCartaAleatoria() {
		return cartas_jugador[(int) (numero_cartas*Math.random())];
	}
	
	public String toString() {
		return super.toString()+"\n"+"Vida = "+vida+"\n"+"Mana = "+mana+"\n"+"Cartas = "+Arrays.toString(cartas_jugador);
	}
}

class PartidaCartas{
	Scanner sc = new Scanner(System.in);
	private ContenedorJugadoresCartas jugadores;
	private ContenedorCartas cartas;
	private JugadorCartas[] jugadores_turno ;
	public PartidaCartas(ContenedorJugadoresCartas jugadores, ContenedorCartas cartas) {
		super();
		this.jugadores = jugadores;
		this.cartas = cartas;
	}
	public void repartir_cartas() {
		JugadorCartas[] array_jugadores = jugadores.getJugadores();
		for (JugadorCartas jugadorCartas : array_jugadores) {
			for (int i = 0; i< jugadores.getNUMERO_CARTAS(); i++) {
				jugadorCartas.getCartas_jugador()[i]= cartas.getCartaAleatoria();
			}
		}
		/*
		 * for (int i= 0; i< jugadores.getNUMERO_JUGADORES(); i++) { for (int j = 0; j<
		 * jugadores.getNUMERO_CARTAS(); j++) {
		 * array_jugadores[i].getCartas_jugador()[j]= cartas.getCartaAleatoria(); } }
		 */
	}
	public void iniciar_juego() {
		int turno;
		Carta carta_atacante;
		Carta carta_defensora;
		int damage, recuperacion_mana;
		boolean acierto;
		int numero_aleatorio;
		//while (!jugadores.ganador()) {
			

			jugadores_turno = jugadores.getDosJugadoresCartasAleatorios();
			System.out.println("lalala");
			// un solo turno
			while (jugadores_turno[0].getVida() > 0 && jugadores_turno[1].getVida() > 0) {
				turno =((int) ( 10 * Math.random()))%2;
				carta_atacante = jugadores_turno[turno%2].getCartaAleatoria();
				carta_defensora = jugadores_turno[(turno+1)%2].getCartaAleatoria();
				recuperacion_mana = (int) (3*Math.random() +1);
				numero_aleatorio = (int) (10*Math.random() +1);
				if (carta_atacante.getAgilidad() >= numero_aleatorio) {
					acierto = true;
				}else {
					acierto = false;
				}
				
				if (acierto) {
					// hago daño al defensor
					damage = carta_atacante.getAtaque()- (int)(carta_atacante.getAtaque()*carta_defensora.getDefensa()/10);
					jugadores_turno[(turno+1)%2].setVida(jugadores_turno[(turno+1)%2].getVida()-damage);
				}
				jugadores_turno[turno%2].setMana(jugadores_turno[turno%2].getMana()-carta_atacante.getCosteMana());
				jugadores_turno[turno%2].setMana(jugadores_turno[turno%2].getMana()+recuperacion_mana);
				turno ++;
				
			}
			
			if (jugadores_turno[0].getVida() > 0) {
				System.out.println(jugadores_turno[0].getNombre() + " ha ganado el turno");
				System.out.println(jugadores_turno[1].getNombre() + " ha perdido el turno");
			}else {
				System.out.println(jugadores_turno[1].getNombre() + " ha ganado el turno");
				System.out.println(jugadores_turno[0].getNombre() + " ha perdido el turno");
			}

	}
	
}

class ContenedorJugadoresCartas{
	private final int NUMERO_CARTAS = 3;
	private final int NUMERO_JUGADORES = 5;
	private JugadorCartas[] jugadores = new JugadorCartas[NUMERO_JUGADORES];

	
	public int getNUMERO_CARTAS() {
		return NUMERO_CARTAS;
	}

	public int getNUMERO_JUGADORES() {
		return NUMERO_JUGADORES;
	}

	public JugadorCartas[] getJugadores() {
		return jugadores;
	}

	public void setJugadores(JugadorCartas[] jugadores) {
		this.jugadores = jugadores;
	}

	public void resetear_jugadores() {
		jugadores = new JugadorCartas[NUMERO_JUGADORES];
	}

	public boolean AddJugador(JugadorCartas jugador) {
		for (int i=0; i< jugadores.length;i++) {
			if (jugadores[i]==null) {
				jugadores[i] = jugador;
				System.out.println("jugador añadido correctamente"+ "\n"+ jugador);
				return true;
			}
		}
		System.out.println("No se ha podido añadir el Jugador");
		return false;
	}
	
	public void AddJugadores(ArrayList<JugadorCartas> jugadores_cartas) {
		int limit = (jugadores_cartas.size() > NUMERO_JUGADORES) ? NUMERO_JUGADORES:jugadores_cartas.size();
		
		for (int i=0; i < limit;i++) {
			this.AddJugador(jugadores_cartas.get(i));
		}

	}
	
	public JugadorCartas getJugadorAleatorio() {
		int numero_aleatorio ;
		if (!jugadores_completo()) {
			System.out.println("Primero mete todos los jugadores");
			return null;
		}
		numero_aleatorio = (int) (NUMERO_JUGADORES*Math.random());
		return jugadores[numero_aleatorio];
	}
	public boolean ganador() {
		int jugadores_con_vida = 0;
		for (int i = 0; i< jugadores.length; i++) {
			if (jugadores[i].getVida() > 0) {
				jugadores_con_vida ++;
			}
		}
		if (jugadores_con_vida > 1) {
			return false;
		}
		return true;
	}
	public JugadorCartas[] getDosJugadoresCartasAleatorios() {
		JugadorCartas[] jugadoresCartasAleatorios = new JugadorCartas[2];
		int numero_aleatorio ;
		do{
			numero_aleatorio = (int) (NUMERO_JUGADORES*Math.random());
		}while (jugadores[numero_aleatorio].getVida()<=0);
		
		jugadoresCartasAleatorios[0] = jugadores[numero_aleatorio];
		do {
			numero_aleatorio = (int) (NUMERO_JUGADORES*Math.random());
		}while (jugadores[numero_aleatorio] == jugadoresCartasAleatorios[0] && jugadores[numero_aleatorio].getVida() <= 0);
		jugadoresCartasAleatorios[1] = jugadores[numero_aleatorio];
		return jugadoresCartasAleatorios;
		
	}
	public void mostrar_jugadores() {
		for (int i= 0; i < jugadores.length; i++) {
			if (jugadores[i] != null) {
				System.out.println("*************Carta "+i+" **************");
				System.out.println(jugadores[i]);
			}
		}
	}
	public boolean jugadores_completo() {
		boolean completo = true;
		for (int i= 0; i < jugadores.length; i++) {
			if (jugadores[i] == null) {
				completo = false;
			}
		}
		return completo;
	}
	
}


class ContenedorCartas{
	private  final int NUMERO_CARTAS = 22;
	private Carta[] cartas = new Carta[NUMERO_CARTAS];
	private int[] cartasEscogidas = new int[NUMERO_CARTAS];
	
	public void resetear_cartas() {
		cartas = new Carta[NUMERO_CARTAS];
		cartasEscogidas = new int[NUMERO_CARTAS];
	}
	public void resetear_cartas_escogidas() {
		cartasEscogidas = new int[NUMERO_CARTAS];
	}
	
	public void AddCarta(Carta carta) {
		for (int i=0; i< cartas.length;i++) {
//			if (i == cartas.length ) {
//				System.out.println("No se ha podido añadir la carta");
//				break;
//			}
			if (cartas[i]==null) {
				cartas[i] = carta;
				System.out.println("Carta añadida correctamente");
//				break;
				return;
			}
			
		}
		System.out.println("No se ha podido añadir la carta");
		//System.out.println("*******************");
		//System.out.println(Arrays.toString(cartas));
	}
	
	public Carta getCartaAleatoria() {
		int numero_aleatorio ;
		int numero_cartas_escogidas = 0;
		if (!cartas_completo()) {
			System.out.println("Primero mete todas las cartas");
			return null;
		}
		for (int i=0; i< NUMERO_CARTAS; i++) {
			numero_cartas_escogidas += cartasEscogidas[i];
		}
		if ( numero_cartas_escogidas == NUMERO_CARTAS) {
			System.out.println("Ya no podemos escoger mas cartas");
			return null;
		}
		
		
		do {
			numero_aleatorio = (int) (NUMERO_CARTAS*Math.random());
		}while (cartasEscogidas[numero_aleatorio] == 1);
		cartasEscogidas[numero_aleatorio] = 1;
		return cartas[numero_aleatorio];
		
	}
	public void mostrar_cartas() {
		for (int i= 0; i < cartas.length; i++) {
			if (cartas[i] != null) {
				System.out.println("*************Carta "+i+" **************");
				System.out.println(cartas[i]);
			}
		}
	}
	public boolean cartas_completo() {
		boolean completo = true;
		for (int i= 0; i < cartas.length; i++) {
			if (cartas[i] == null) {
				completo = false;
			}
		}
		return completo;
	}
	
}



class Carta{
    private String nombre;
    private int ataque;
    private int defensa;
    private int costeMana;
    private int agilidad;
    public Carta(String nombre, int ataque, int defensa, int costeMana, int agilidad) {
        super();
        this.ataque = ataque;
        this.defensa = defensa;
        this.costeMana = costeMana;
        this.agilidad = agilidad;
        this.nombre = nombre;
    }
    public int getAtaque() {
        return ataque;
    }
    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }
    public int getDefensa() {
        return defensa;
    }
    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }
    public int getCosteMana() {
        return costeMana;
    }
    public void setCosteMana(int costeMana) {
        this.costeMana = costeMana;
    }
    public int getAgilidad() {
        return agilidad;
    }
    public void setAgilidad(int agilidad) {
        this.agilidad = agilidad;
    }
    
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String toString(){
        // String nombre, int ataque, int defensa, int costeMana, int agilidad
        return "Nombre = "+nombre+"\n" +"Ataque = "+ataque+"\n" +"Defensa = "+defensa+"\n" +"costeMana = "+costeMana+"\n" +"Agilidad = "+agilidad;
    }
}
abstract class Jugador{
	String dni;
	String nombre;
	String apellidos;
	
	public Jugador(String dni, String nombre, String apellidos) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String toString() {
		//return "Dni = "+dni+"\n"+"Nombre = "+nombre+"\n"+"Apellidos = "+apellidos;
		return "Dni = "+dni+" -- "+"Nombre = "+nombre+" - "+"Apellidos = "+apellidos;
	}
}