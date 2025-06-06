import juego.Juego;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        menuInicial();
    }

    public static void menuInicial() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\nBienvenidos a MetalGear");
            System.out.println("1. Inicializar el juego");
            System.out.println("2. Salir del juego");
            System.out.print("Seleccione una opción: ");

            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        System.out.println("\n-------------------------");
                        System.out.println("Inicializando el juego");
                        System.out.println("-------------------------\n");

                        Juego juego = new Juego();
                        juego.menuPrincipal();
                        opcion = 2;
                        break;
                    case 2:
                        System.out.println("Hasta luego");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente nuevamente.");
                }
            } else {
                System.out.println("Entrada no válida.");
                scanner.nextLine(); // limpiar entrada
                opcion = -1;
            }

        } while (opcion != 2);
    }
}