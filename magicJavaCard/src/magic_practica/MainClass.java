package magic_practica;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.lang.Math;

public class MainClass {

	public static void main(String[] args) {		
		Menu menu = new Menu();
        menu.mostrarMenu();		
	}
	public boolean comprobarDni(String dni, String letraDni) {
		
		return false;
		
	}
}

class Menu{
    private ContenedorJugadoresCartas contenedor_jugadores_cartas;
    private ContenedorCartas contenedor_cartas;
    private ArrayList<JugadorCartas> jugadores_cartas;
    private int rondas;
	public Menu(ContenedorJugadoresCartas contenedorJugadores, ContenedorCartas cartas, int rondas) {
		super();
		this.contenedor_jugadores_cartas = contenedorJugadores;
		this.contenedor_cartas = cartas;
		this.jugadores_cartas = new ArrayList<JugadorCartas>();
		this.rondas = rondas;
		cargarCartas();
	}
	public Menu() {
		super();
		this.contenedor_jugadores_cartas = new ContenedorJugadoresCartas();
		this.contenedor_cartas = new ContenedorCartas();
		this.jugadores_cartas = new ArrayList<JugadorCartas>();
		this.rondas = 1;
		cargarCartas();
		
	}
	
    public void mostrarMenu() {
        Scanner sc = new Scanner(System.in);
        String menu = "\n\n     LA CONQUISTA DE VINLAND     \n\n1.-Crear Jugador\n2.-Setear Jugadores\n3.-Comenzar Partida\n4.-Mostrar Jugadores\n5.-Mostrar cartas\n6.-Exit";
        int opcion =0;

        do {
            System.out.println(menu);
            System.out.print("Selecciona una opción: ");
            if (sc.hasNextInt())  {

                opcion = sc.nextInt();
				if (opcion>=1 || opcion <=6) {
					
					sc.nextLine();
		
			            switch (opcion) {
			                case 1:
			                	crearJugadores(sc);
			                    break;
			                case 2:
			                    setearJugadores(sc);
			                    break;
			                case 3:
			                	iniciarJuego(sc);
			                    break;
			                case 4:
	                            mostrarEstadisticas(sc);
			                    break;
			                case 5:
			                	mostrarCartas(sc);
			                    break;
			                case 6:
			                    System.out.println("Saliendo del juego...");
			                    break;
		
			            }
				}else {
					System.out.println("Error, numero fuera de rango");
					sc.nextInt();
				}
			}else {
				System.out.println("Error, solo se pueden introducir numeros");
				sc.next();
			}
        } while (opcion != 6);
        

        sc.close();
    }
    
    private void mostrarCartas(Scanner sc) {
    	String enterContinuar = String.format("  %s ", "Enter para continuar...");
    	contenedor_cartas.mostrar_cartas();

		System.out.println();
		System.out.print(enterContinuar);
		sc.nextLine();
    }
    

    private void mostrarEstadisticas(Scanner sc) {
        String menu = "MOSTRAR JUGADORES\n1.-Mostrar ordenados por dni\n2.-Mostrar ordenados por nombre\n3.-Mostrar ordenados por apellidos\n4.-Go back";
        int opcion = 0;
        do {
            System.out.println(menu);
            System.out.print("Selecciona una opción: ");
            if (sc.hasNextInt()) {

                opcion = sc.nextInt();
                if (opcion >= 1 || opcion <= 6) {

                    sc.nextLine();
                    String cabecera = String.format("\n  %-10s %-32s ", "DNI", "Nombre Apellidos");
                    switch (opcion) {
                        case 1:
                            Collections.sort(jugadores_cartas);

                            System.out.println(cabecera);
                            for (JugadorCartas jugador : jugadores_cartas) {
                                System.out.println(jugador.mostrarJugadores());
                            }
                            break;
                        case 2:
                            Collections.sort(jugadores_cartas, new OrdenarJugadoresPorNombre());
                            System.out.println(cabecera);
                            for (JugadorCartas jugador : jugadores_cartas) {
                                System.out.println(jugador.mostrarJugadores());
                            }
                            break;
                        case 3:
                            Collections.sort(jugadores_cartas, new OrdenarJugadoresPorApellidos());
                            System.out.println(cabecera);
                            for (JugadorCartas jugador : jugadores_cartas) {
                                System.out.println(jugador.mostrarJugadores());
                            }
                            break;
                        case 4:
                            return;

                    }
                } else {
                    System.out.println("Error, numero fuera de rango");
                    sc.nextInt();
                }
            } else {
                System.out.println("Error, solo se pueden introducir numeros");
                sc.next();
            }
        } while (opcion != 4);

        sc.close();

    }
    
    private void setearJugadores(Scanner sc) {
    	
    	String cabecera = String.format("\n  %-10s %-32s %4s %4s \n", "DNI","Nombre Apellidos","Mana","Vida");
    	String cabeceraCompleto = String.format("\n  %-10s %-32s ", "DNI","Nombre Apellidos");
    	String enterContinuar = String.format("  %s ", "Enter para continuar...");
    	
    	if (jugadores_cartas.size() < contenedor_jugadores_cartas.getNUMERO_JUGADORES()) {
    		System.out.println("\nPara setear, debes crear jugadores previamente.");

			System.out.print(enterContinuar);
			sc.nextLine();
    		return;
     	}else if(contenedor_jugadores_cartas.jugadores_completo()) {
     		
     		System.out.println("\nYa se han seleccionado jugadores, puedes comenzar la partida.\n Jugadores seleccionados: "+"\n");
    		System.out.println(cabeceraCompleto);

    		for (JugadorCartas jcartas :contenedor_jugadores_cartas.getJugadores()) {
				if (jcartas instanceof JugadorCartas) {
					System.out.println(jcartas.mostrarJugadores());	
				}
			}
    		System.out.println();
			System.out.print(enterContinuar);
			sc.nextLine();
    		return;  		
    		
    	}
    	
    	
    	
		System.out.println(cabecera);
		
		 
		while (!contenedor_jugadores_cartas.jugadores_completo()) {		
			for (JugadorCartas jcartas :jugadores_cartas) {System.out.println(jcartas.showJC());}
			System.out.println("\nJugadores seleccionados: "+ contenedor_jugadores_cartas.num_jugadores_notnull());
			if (contenedor_jugadores_cartas.num_jugadores_notnull()>0) {
				for (JugadorCartas jcartas :contenedor_jugadores_cartas.getJugadores()) {
					if (jcartas instanceof JugadorCartas) {
						System.out.println(jcartas.mostrarJugadores());	
					}
				}				
			}
			System.out.println("Pendientes de seleccionar: "+ contenedor_jugadores_cartas.num_jugadores_null()+"\n");
			System.out.println("Introduce el DNI del jugador a seleccionar: "+"\n");
			String dni_jugador = sc.next();
			JugadorCartas jugadorSeteado = buscar_por_dni(dni_jugador);

			if (jugadorSeteado instanceof JugadorCartas) {
				contenedor_jugadores_cartas.AddJugador(jugadorSeteado);
				
				for (JugadorCartas jcartas :jugadores_cartas) { System.out.println(jcartas.showJC()); }

				System.out.println();
				System.out.println("Jugador/es seleccionado:");

				System.out.println(jugadorSeteado.mostrarJugadores()+"\n");
				System.out.print(enterContinuar);
				sc.nextLine();
				
			}else {
				System.out.println("No existe ningún jugador con DNI " + dni_jugador + "\nIntentelo nuevamente. ");
				System.out.print(enterContinuar);
				sc.nextLine();
			}

		}
		System.out.println();
		
    }
    
    private JugadorCartas buscar_por_dni(String dni) {
		for (JugadorCartas jcartas :jugadores_cartas) {
			if (jcartas.getDni().equals(dni)){
				System.out.println(jcartas.showJC());
				return jcartas;
			}
		}
    	return null;
    }

    private void crearJugadores(Scanner sc) {
        System.out.println("Introduce el nombre de tu personaje");
        String nombre = sc.next();
        System.out.println("Introduce los apellidos de tu personaje");
        sc.nextLine();
        String apellidos = sc.nextLine();
        System.out.println("Introduce el DNI de tu personaje");
        String dni = sc.next();
        System.out.println("Quieres agregar el jugador a la lista? Y/n");
        String letra = sc.next();
        if (letra.equals("Y")) {
            System.out.println("JUgador añadido");
            jugadores_cartas.add(new JugadorCartas(dni, nombre, apellidos, 3, 20, 20));
        } else {
            System.out.println("JUgador no añadido");
        }
        System.out.println(jugadores_cartas);
    }
    
//    private void cargarJugadores() {
//        jugadores_cartas.add(new JugadorCartas("12345678L", "Gervasio", "de León Mora", 3, 20, 20));
//        jugadores_cartas.add(new JugadorCartas("11111111H","Margarita","Flores Giménez",3,20,20));
//        jugadores_cartas.add(new JugadorCartas("12312312A", "Pedro", "Sanchez", 3, 20, 20));
//        jugadores_cartas.add(new JugadorCartas("46464646A", "Pepe", "García García",3, 20, 20));
//        jugadores_cartas.add(new JugadorCartas("88888888Y", "Eustaquio","Avichuela", 3, 20, 20)); 
//        jugadores_cartas.add(new JugadorCartas("45678934Z","Cristian" , "Navarro Gonzalez", 3, 20, 20));
//        jugadores_cartas.add(new JugadorCartas("46845365N","Dolores","Suárez Castillo", 3, 20, 20));
//        jugadores_cartas.add(new JugadorCartas("11112222A","Pablo","Suarez",3,20,20));
//        jugadores_cartas.add(new JugadorCartas("11223344M", "Ezequiel", "De todos los santos", 3, 20, 20));
//        jugadores_cartas.add(new JugadorCartas("76547821D", "Marcos", "Fernández Martín", 3, 20, 20));
//        jugadores_cartas.add(new JugadorCartas("84267193J","Inmaculada","Ponce",3,20,20));
//        
//		//Collections.shuffle(jugadores_cartas);
//		
//		//contenedor_jugadores_cartas.AddJugadores(jugadores_cartas );
//    }
//    
	private void iniciarJuego(Scanner scanner) {
    	String enterContinuar = String.format("  %s ", "Enter para continuar...");
		if (contenedor_jugadores_cartas.num_jugadores_null()==0) {
			
			int opcion=0;   
			
			do {
				System.out.print("\nAñadir número de rondas: (Maximo 3 rondas)\n");
	            if (scanner.hasNextInt())  {

	                opcion = scanner.nextInt();
					if (opcion < 1 || opcion > 3) {
						System.out.print("\nEl valor de rondas debe ser mayor a cero y menor o igual a 3 \n");
						scanner.nextInt();
					}
				}else {
					System.out.println("Error, solo se pueden introducir numeros");
					scanner.next();
				}				
			}while(opcion < 1 || opcion > 3);	
			
			
		
	        this.rondas = opcion;
			PartidaCartas pc = new PartidaCartas(this.contenedor_jugadores_cartas, this.contenedor_cartas, this.rondas);
			pc.repartir_cartas();
			pc.iniciar_juego();			
		}else {
			System.out.println();
			System.out.println("ERROR Antes de iniciar juego debe setear jugadores. ");
			System.out.println(enterContinuar);
			scanner.nextLine();
		}
	}
	
	private void cargarCartas() {
		
		Carta[] cartas = new Carta[22];    

        cartas[0] = new Carta("Odín, el Padre de Todo", 7, 8, 8, 3); // Sabiduría Eterna: Roba 2 cartas adicionales al comienzo de tu turno.
        cartas[1] = new Carta("Thor, el Dios del Trueno", 9, 6, 7, 5); // Martillo de Tormentas: Inflige 3 puntos de daño a todos los enemigos al entrar en juego.
        cartas[2] = new Carta("Loki, el Tramposo", 5, 4, 6, 8); // Engaño Maestro: Intercambia los valores de ataque y defensa de una criatura enemiga.
        cartas[3] = new Carta("Freyja, la Diosa del Amor", 4, 7, 6, 4); // Bendición de Vida: Cura 5 puntos de vida a todas tus criaturas.
        cartas[4] = new Carta("Fenrir, el Lobo Gigante", 8, 5, 7, 6); // Furia Desatada: Si Fenrir es derrotado, inflige 5 puntos de daño a todas las criaturas enemigas.
        cartas[5] = new Carta("Jörmundgander, la Serpiente del Mundo", 10, 10, 9, 2); // Veneno Letal: Inflige 2 puntos de daño adicionales por cada ataque.
        cartas[6] = new Carta("Heimdall, el Vigilante", 5, 7, 5, 4); // Cuerno de Alerta: Invoca una criatura adicional al comienzo de tu turno.
        cartas[7] = new Carta("Baldur, el Dios de la Luz", 6, 6, 6, 5); // Inmortalidad Temporal: Baldur no puede ser derrotado en el próximo turno.
        cartas[8] = new Carta("Hel, la Reina del Inframundo", 6, 8, 7, 3); // Toque de Muerte: Reduce la defensa del enemigo en 2 puntos al atacar.
        cartas[9] = new Carta("Tyr, el Dios de la Guerra", 7, 5, 6, 6); // Sacrificio Valiente: Tyr puede atacar dos veces en un turno, pero pierde 2 puntos de defensa.
        cartas[10] = new Carta("Skadi, la Diosa del Invierno", 5, 6, 5, 4); // Frío Glacial: Congela a una criatura enemiga, impidiendo que ataque en el próximo turno.
        cartas[11] = new Carta("Freyr, el Dios de la Fertilidad", 4, 6, 5, 5); // Cosecha Abundante: Aumenta el maná máximo en 2 puntos.
        cartas[12] = new Carta("Valkiria, la Elegidora de los Caídos", 5, 4, 4, 7); // Ascenso al Valhalla: Revive una criatura aliada cuando Valkiria es derrotada.
        cartas[13] = new Carta("Nidhogg, el Devorador de Raíces", 9, 7, 8, 3); // Corrupción Eterna: Reduce el ataque y defensa de todas las criaturas enemigas en 1 punto.
        cartas[14] = new Carta("Sif, la Diosa de la Cosecha", 3, 5, 4, 4); // Crecimiento Rápido: Aumenta la defensa de todas tus criaturas en 2 puntos.
        cartas[15] = new Carta("Mimir, el Sabio", 3, 6, 5, 2); // Conocimiento Ancestral: Roba 3 cartas adicionales.
        cartas[16] = new Carta("Surtr, el Gigante de Fuego", 10, 8, 9, 2); // Llamas del Ragnarok: Inflige 5 puntos de daño a todas las criaturas enemigas al entrar en juego.
        cartas[17] = new Carta("Huginn y Muninn, los Cuervos de Odín", 2, 3, 3, 8); // Vigilancia Eterna: Permite ver la mano de tu oponente.
        cartas[18] = new Carta("Yggdrasil, el Árbol del Mundo", 3, 15, 10, 1); // Raíces Eternas: No puede ser destruido por efectos de daño.
        cartas[19] = new Carta("Bifröst, el Puente del Arcoíris", 3, 5, 4, 6); // Viaje Instantáneo: Mueve una criatura aliada a cualquier posición en el campo de batalla.
        cartas[20] = new Carta("Fafnir, el Dragón Codicioso", 9, 7, 8, 4); // Tesoro Maldito: Ganas 2 puntos de maná cada vez que Fafnir ataca.
        cartas[21] = new Carta("Ragnarok, el Fin del Mundo", 15, 15, 12, 1); // Destrucción Total: Destruye todas las criaturas en el campo de batalla, excepto a Ragnarok.
        
		for (int i= 0; i< cartas.length; i++) {
			contenedor_cartas.AddCarta(cartas[i]);
		}
        
	}
	   
}

class JugadorCartas extends Jugador implements Comparable<JugadorCartas> {
	
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
	public String getNombreCompleto() {
		return String.format("%s %s", this.getNombre(),this.getApellidos() ); 
	}
	
	public String showJC() {
		return String.format("%-12s %-12s %-20s %4d %4d ", super.dni, super.nombre,super.apellidos,this.mana,this.vida);
	}

    public String mostrarJugadores() {
        return String.format("%-12s %s %s ", super.dni, super.nombre, super.apellidos);
    }

    @Override
    public int compareTo(JugadorCartas o) {
        return this.getDni().compareTo(o.getDni());
    }
	
	public String toString() {
		return super.toString()+"\n"+"Vida = "+vida+"\n"+"Mana = "+mana+"\n"+"Cartas = "+Arrays.toString(cartas_jugador);
	}
}

class OrdenarJugadoresPorNombre implements Comparator<JugadorCartas> {

    @Override
    public int compare(JugadorCartas o1, JugadorCartas o2) {
        return o1.getNombre().compareTo(o2.getNombre());
    }
}

class OrdenarJugadoresPorApellidos implements Comparator<JugadorCartas> {

    @Override
    public int compare(JugadorCartas o1, JugadorCartas o2) {
        return o1.getApellidos().toLowerCase().compareTo(o2.getApellidos().toLowerCase());
    }
}

class PartidaCartas{
	private int rondas;
	public PartidaCartas(int rondas) {
		super();
		this.rondas = rondas;
	}
	public int getRondas() {
		return rondas;
	}
	public void setRondas(int rondas) {
		this.rondas = rondas;
	}
	Scanner sc = new Scanner(System.in);
	private ContenedorJugadoresCartas jugadores;
	private ContenedorCartas cartas;
	private JugadorCartas[] jugadores_turno ;
	public PartidaCartas(ContenedorJugadoresCartas jugadores, ContenedorCartas cartas, int rondas) {
		super();
		this.jugadores = jugadores;
		this.cartas = cartas;
		this.rondas = rondas;
	}
	public void repartir_cartas() {
		JugadorCartas[] array_jugadores = jugadores.getJugadores();
		for (JugadorCartas jugadorCartas : array_jugadores) {
			for (int i = 0; i< jugadores.getNUMERO_CARTAS(); i++) {
				jugadorCartas.getCartas_jugador()[i]= cartas.getCartaAleatoria();
			}
		}
	}
	public void iniciar_juego() {
		int turno;
		Carta carta_atacante;
		Carta carta_defensora;
		int damage, recuperacion_mana;
		boolean acierto;
		int numero_aleatorio;
		int jugador_uno_win = 0 ,jugador_dos_win = 0;

		String enterContinuar = String.format("  %s ", "Enter para continuar a la siguiente ronda ...");



			jugadores_turno = jugadores.getJugadores();
			System.out.println("Rondas "+ getRondas());
			System.out.println(String.format("%s %s %s","=".repeat(70),"Inicio de Partida","=".repeat(70)));
			for (int i = 0; i < jugadores_turno.length; i++) {
				System.out.println(String.format("%s %s %s","Jugador "+(i+1)+" = ",jugadores_turno[i].getNombreCompleto()," ("+jugadores_turno[i].getDni()+")"));
			}
			for (int i = 0; i<getRondas();i++) {
				System.out.println(String.format("%s %s %s","-".repeat(70),"Ronda número "+(i+1),"-".repeat(70)));
				if (i>0) {prepararProximaRonda(); }

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
						damage = carta_atacante.getAtaque()- (int)(carta_atacante.getAtaque()*carta_defensora.getDefensa()/10);
						jugadores_turno[(turno+1)%2].setVida(jugadores_turno[(turno+1)%2].getVida()-damage);
						System.out.println("El jugador " + jugadores_turno[turno % 2].getNombre() + " ha atacado a " + jugadores_turno[(turno + 1) % 2].getNombre() + " con la carta " + 
			                    carta_atacante.getNombre() + " y le ha hecho " + damage + " de daño");
					}
					jugadores_turno[turno%2].setMana(jugadores_turno[turno%2].getMana()-carta_atacante.getCosteMana());
					jugadores_turno[turno%2].setMana(jugadores_turno[turno%2].getMana()+recuperacion_mana);
					turno ++;
					
				}
				
				if (jugadores_turno[0].getVida() > 0) {
					System.out.println(String.format("\nLa ronda %d ha acabado y %s ha ganado a %s",(i+1),jugadores_turno[0].getNombre() ,jugadores_turno[1].getNombre())+"\n");
					jugador_uno_win++;
				}else {
					System.out.println(String.format("\nLa ronda %d ha acabado y %s ha ganado a %s",(i+1),jugadores_turno[1].getNombre() ,jugadores_turno[0].getNombre())+"\n");
					jugador_dos_win++;
				}
		        System.out.print(enterContinuar);
		        sc.nextLine();
				
			}
	        if (jugador_uno_win > jugador_dos_win) {
	            System.out.println(jugadores_turno[0].getNombre() + " ha ganado la partida con un total de " + jugador_uno_win + " victorias a " + jugador_dos_win);
	        } else if (jugador_uno_win < jugador_dos_win) {
	            System.out.println(jugadores_turno[1].getNombre() + " ha ganado la partida con un total de " + jugador_dos_win + " victorias a " + jugador_uno_win);
	        }else {
	        	System.out.println(jugadores_turno[1].getNombre() + " y "+jugadores_turno[1].getNombre() + " han empatado la partida con un total de " + jugador_dos_win + " a " + jugador_uno_win);
	        }
			
	}
	
	private void prepararProximaRonda() {
		for (JugadorCartas jugadorCartas : jugadores_turno) {
			for (int i = 0; i< jugadores.getNUMERO_CARTAS(); i++) {
				jugadorCartas.getCartas_jugador()[i]= cartas.getCartaAleatoria();
			}
			jugadorCartas.resetear_mana();
			jugadorCartas.resetear_vida();			
		}
	}
	
}

class ContenedorJugadoresCartas{
	private final int NUMERO_CARTAS = 3;
	private final int NUMERO_JUGADORES = 2;
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
	public boolean jugadores_completo() {
		boolean completo = true;
		for (int i= 0; i < jugadores.length; i++) {
			if (jugadores[i] == null) {
				completo = false;
			}
		}
		return completo;
	}
	public int num_jugadores_null() {
		int num = 0;
		for (int i= 0; i < jugadores.length; i++) {
			if (jugadores[i] == null) {
				num++;
			}
		}
		return num;
	}

	public int num_jugadores_notnull() {
		int num = 0;
		for (int i= 0; i < jugadores.length; i++) {
			if (jugadores[i] != null) {
				num++;
			}
		}
		return num;
	}	
	
}


class ContenedorCartas{
	private final int NUMERO_CARTAS = 22;
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
			if (cartas[i]==null) {
				cartas[i] = carta;
				return;
			}
			
		}
		System.out.println("No se ha podido añadir la carta");
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
		if (cartas.length>0) {
			System.out.println(String.format("\n              %30s              \n","LA CONQUISTA DE VINLAND"));    
			System.out.println(String.format("%-40s %6s %7s %4s %8s ", "Nombre", "Ataque", "Defensa", "Mana", "Agilidad"));
	    }
		for (int i= 0; i < cartas.length; i++) {
			if (cartas[i] != null) {
				System.out.println(cartas[i].mostrarJugadores());
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

    public String mostrarJugadores() {
        return String.format("%-40s %6d %7d %4d %8d ", this.nombre, this.ataque, this.defensa, this.costeMana, this.agilidad);
    }

    public String toString(){
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
	public String getNombreCompleto() {
		return "";		
	}
	public String toString() {
		return "Dni = "+dni+" -- "+"Nombre = "+nombre+" - "+"Apellidos = "+apellidos;
	}
}
