package juego.mision;

import juego.personaje.Snake;
import juego.personaje.enemigo.MetalGear;
import java.util.Scanner;

public class MisionFinal extends Mision {

    public MisionFinal(String mision) {
        super(mision);
    }

    @Override
    public void iniciar(Snake snake) {
        MetalGear metalGear = new MetalGear(0,0, "MetalGear");
        Scanner scanner = new Scanner(System.in);

        int vidaSnake = snake.getVida();
        int vidaRex = metalGear.getVida();

        System.out.println("\n¡Metal Gear REX aparece!");
        System.out.println("Tu vida: " + vidaSnake + " HP | Vida de REX: " + vidaRex + " HP");

        while (vidaSnake > 0 && vidaRex > 0) {
            // Turno de Snake
            System.out.println("\nTurno de Snake:");
            System.out.println("1 - Disparar misil");
            System.out.println("2 - Esquivar");

            int eleccion = 0;
            while (eleccion != 1 && eleccion != 2) {
                System.out.print("Elige tu acción (1 o 2): ");
                if (scanner.hasNextInt()) {
                    eleccion = scanner.nextInt();
                    if (eleccion != 1 && eleccion != 2) {
                        System.out.println("Opción inválida.");
                    }
                } else {
                    System.out.println("Entrada no válida.");
                    scanner.next();
                }
            }

            if (eleccion == 1) {
                int danio = metalGear.ataque();
                metalGear.setVida(vidaRex - danio);
                if (vidaRex < 0) vidaRex = 0;
                System.out.println("\n¡Le diste a REX! (-" + danio + " HP)");
                System.out.println("Vida de REX: " + vidaRex + " HP");
            } else {
                System.out.println("\n¡Preparándote para esquivar!");
            }

            if (vidaRex <= 0) break;

            // Turno de REX
            int ataqueRex = metalGear.ataque();
            int danioRecibido = ataqueRex;

            if (eleccion == 2) {
                int reduccion = metalGear.ataqueReducido(); // 50% a 100%
                danioRecibido = ataqueRex * (100 - reduccion) / 100;
                System.out.println("¡Metal Gear ataca con un Cañón Láser! (" + ataqueRex + " HP original)");
                System.out.println("¡Esquivaste! Daño reducido a " + danioRecibido + " HP.");
            } else {
                System.out.println("¡Metal Gear ataca con un Cañón Láser! (-" + ataqueRex + " HP)");
            }

            snake.setVida(vidaSnake - danioRecibido);

            if (vidaSnake < 0) vidaSnake = 0;
            System.out.println("Tu vida: " + vidaSnake + " HP");
        }

        // Fin del combate
        if (vidaSnake > 0) {
            System.out.println("\n¡Has derrotado a Metal Gear REX! ¡Misión cumplida!");
            snake.setVida(vidaSnake); // Guardamos la vida restante de Snake
        } else {
            System.out.println("\nHas sido derrotado por REX... ¡Misión fallida!");
            snake.setVida(0); // Vida queda en cero si fue derrotado
        }
    }
}