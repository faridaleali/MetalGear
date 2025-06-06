package juego;

import juego.mapa.Mapa;
import juego.mision.Mision;
import juego.mision.MisionFinal;
import juego.mision.MisionIntermedia;
import juego.personaje.Snake;
import juego.personaje.enemigo.Guardia;

import java.util.Scanner;

public class Juego {

    protected Mapa mapa;
    protected Mision mision;
    protected Snake snake;
    protected Guardia guardia;

    public Juego() { }

    public void menuPrincipal() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\nEscape de la base");
            System.out.println("1. Iniciar misión principal");
            System.out.println("2. Cargar estado de juego.");
            System.out.println("9. Salir del juego");
            System.out.print("Seleccione una opción: ");

            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        iniciarMisionPrincipal();
                        break;
                    case 2:
                        cargaMision();
                        break;
                    case 9:
                        System.out.println("Hasta luego.");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente nuevamente.");
                }
            } else {
                System.out.println("Entrada no válida. Ingrese un número.");
                scanner.nextLine();
                opcion = -1;
            }
        } while (opcion != 9);
    }

    public void mostrarMenuDeMovimiento() {

        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.print("\nIngrese su movimiento (9 para salir): ");
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                        int resultado = snake.mover(opcion, mapa);

                        if (resultado == 2) iniciarMisionSecundaria();
                        else if ( resultado == 3) iniciarMisionFinal();

                        guardia.mover(opcion, mapa);

                        mapa.mostrarMapa();

                        if (mapa.snakeAtrapado()) {
                            System.out.println("\n¡Snake fue atrapado por un guardia!");
                            System.out.println("FIN DEL JUEGO\n");
                            menuPrincipal();
                            break;
                        }

                        break;
                    case 9:
                        System.out.println("Saliendo de la misión...");
                        break;
                    default:
                        System.out.println("Opción inválida.");
                }
            } else {
                System.out.println("Entrada no válida.");
                scanner.next();
                opcion = -1;
            }

        } while (opcion != 9);

    }

    public void cargaMision() {
        Scanner scanner = new Scanner(System.in);
        String codigo;

        System.out.print("Ingrese el codigo de guardado: ");
        codigo = scanner.next();

        if(codigo.equals("MS-2")) {
            iniciarMisionSecundaria();
        } else if(codigo.equals("MF-1")) {
            iniciarMisionFinal();
        } else {
            System.out.println("Codigo incorrecto, ingreselo nuevamente");
        }
    }

    public void iniciarMisionPrincipal() {

        // --- Inicialización mision primaria ---
        mapa = new Mapa(7, 7, "Principal");
        snake = new Snake(6, 0, "S");
        mision = new MisionIntermedia("Principal");

        for (int i = 0; i < mapa.getFilas(); i++) {
            for (int j = 0; j < mapa.getColumnas(); j++) {
                guardia = new Guardia(i,j, "G");
            }
        }

        mision.iniciar(snake);
        mapa.crearMapa();
        mapa.completarMapa();
        mapa.mostrarMapa();
        mostrarMenuDeMovimiento();
    }

    public void iniciarMisionSecundaria() {

        // --- Inicialización mision secundaria ---
        mapa = new Mapa(9, 9, "Intermedia");
        snake = new Snake(8, 8, "S");
        mision = new MisionIntermedia("Intermedia");

        for (int i = 0; i < mapa.getFilas(); i++) {
            for (int j = 0; j < mapa.getColumnas(); j++) {
                guardia = new Guardia(i,j, "G");
            }
        }

        mision.iniciar(snake);
        mapa.crearMapa();
        mapa.completarMapa();
        mapa.mostrarMapa();
        mostrarMenuDeMovimiento();
    }

    public void iniciarMisionFinal() {

        // --- Inicialización mision secundaria ---
        mision = new MisionFinal("Final");
        snake = new Snake(0, 0, "S");
        mision.iniciar(snake);
    }

    public Mapa getMapa() {
        return mapa;
    }

    public void setMapa(Mapa mapa) {
        this.mapa = mapa;
    }

    public Mision getMision() {
        return mision;
    }

    public void setMision(Mision mision) {
        this.mision = mision;
    }

    public Snake getSnake() {
        return snake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }
}
