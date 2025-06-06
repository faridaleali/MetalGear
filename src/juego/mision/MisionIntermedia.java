package juego.mision;

import juego.personaje.Snake;

import java.util.Objects;

public class MisionIntermedia extends Mision {

    public MisionIntermedia(String mision) {
        super(mision);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        MisionIntermedia otro = (MisionIntermedia) obj;
        return Objects.equals(this.mision, otro.mision);
    }

    @Override
    public void iniciar(Snake snake) {
        if (Objects.equals(mision, "Principal")) {
            misionPrimera();
        } else if (Objects.equals(mision, "Intermedia")) {
            misionSecundaria();
        } else {
            System.out.println("Iniciando misión final...");
        }
    }

    public void misionPrimera() {
        System.out.println("\nIniciando misión principal\n");
        System.out.println("Debes dirigirte hacia la celda “L” para tomar la llave de entrada al hangar.\n" +
                "Luego, deberas dirigirse a la puerta del hangar (“H”) para continuar la siguiente nivel.\n");
        System.out.println("Tu eres Snake, asi que tu posicion actual es donde esta marcado con la letra “S” \n");
        System.out.println("Para moverte deberas decidir si moverte deberas colocar el numero: \n" +
                "1 = Hacia arriba\n" +
                "2 = Hacia abajo\n" +
                "3 = Hacia la izquierda\n" +
                "4 = Hacia la derecha\n");
    }

    public void misionSecundaria() {
        System.out.println("\nIniciando misión intermedia\n");
        System.out.println("Debes dirigirte hacia la celda “C4” para tomar el explosivo. Luego, deberas dirigirse a la puerta (“P”)\n" +
                "para continuar la siguiente nivel. Como el explosivo hace ruido, solo se continuará con la misión si Snake\n" +
                "se encuentra en la celda “P”, y no hay ningún enemigo a 3 celdas de la celda “P”.  Si alguno de los enemigos se\n" +
                "encuentra a 1 bloque de distancia de snake en cualquier momento, Snake es capturado y debe comenzar nuevamente.\n");
        System.out.println("Tu eres Snake, asi que tu posicion actual es donde esta marcado con la letra “S” \n");
        System.out.println("Para moverte deberas decidir si moverte deberas colocar el numero: \n" +
                "1 = Hacia arriba\n" +
                "2 = Hacia abajo\n" +
                "3 = Hacia la izquierda\n" +
                "4 = Hacia la derecha\n");
    }

}
