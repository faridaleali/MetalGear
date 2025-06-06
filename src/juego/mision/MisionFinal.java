package juego.mision;

import juego.personaje.Snake;

import java.util.Random;
import java.util.Scanner;

public class MisionFinal extends Mision {

    public MisionFinal(String mision) {
        super(mision);
    }

    @Override
    public void iniciar(Snake snake) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int vidaSnake = snake.getVida();
        int vidaRex = 100;

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
                int danio = random.nextInt(21) + 10; // 10 a 30
                vidaRex -= danio;
                if (vidaRex < 0) vidaRex = 0;
                System.out.println("\n¡Le diste a REX! (-" + danio + " HP)");
                System.out.println("Vida de REX: " + vidaRex + " HP");
            } else {
                System.out.println("\n¡Preparándote para esquivar!");
            }

            if (vidaRex <= 0) break;

            // Turno de REX
            int ataqueRex = random.nextInt(26) + 15;
            int danioRecibido = ataqueRex;

            if (eleccion == 2) {
                int reduccion = random.nextInt(51) + 50; // 50% a 100%
                danioRecibido = ataqueRex * (100 - reduccion) / 100;
                System.out.println("¡Metal Gear ataca con un Cañón Láser! (" + ataqueRex + " HP original)");
                System.out.println("¡Esquivaste! Daño reducido a " + danioRecibido + " HP.");
            } else {
                System.out.println("¡Metal Gear ataca con un Cañón Láser! (-" + ataqueRex + " HP)");
            }

            vidaSnake -= danioRecibido;
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