package juego.personaje.enemigo;

import juego.mapa.Celda;
import juego.mapa.Mapa;
import juego.personaje.Personaje;

import java.util.Random;

public class Guardia extends Personaje implements Enemigo {
    public Guardia(int posicionX, int posicionY, String personaje) {
        super(posicionX, posicionY, personaje);
    }

    @Override
    public int mover(int movimiento, Mapa mapa) {
        Random rand = new Random();

        for (int i = 0; i < mapa.getFilas(); i++) {
            for (int j = 0; j < mapa.getColumnas(); j++) {

                if ("G".equals(mapa.getCeldaNombre(i, j))) {
                    int nuevaX = i;
                    int nuevaY = j;

                    int direccion = rand.nextInt(4);
                    switch (direccion) {
                        case 0 -> nuevaX--; // arriba
                        case 1 -> nuevaX++; // abajo
                        case 2 -> nuevaY--; // izquierda
                        case 3 -> nuevaY++; // derecha
                    }

                    // Asegurarse de que no haya otro guardia (ni temporal "g") en destino
                    if (mapa.posicionValida(nuevaX, nuevaY) &&
                            mapa.celdaLibre(nuevaX, nuevaY) &&
                            !mapa.getCeldaNombre(nuevaX, nuevaY).equals("H") &&
                            !mapa.getCeldaNombre(nuevaX, nuevaY).equals("C4") &&
                            !mapa.getCeldaNombre(nuevaX, nuevaY).equals("L") &&
                            !mapa.getCeldaNombre(nuevaX, nuevaY).equals("P") &&
                            !mapa.getCeldaNombre(nuevaX, nuevaY).equals("G") &&
                            !mapa.getCeldaNombre(nuevaX, nuevaY).equals("g")) {

                        // Liberar la celda original
                        mapa.setCelda(i, j, new Celda(i, j));

                        // Marcar temporalmente la nueva posición como "g"
                        mapa.setCelda(nuevaX, nuevaY, new Celda("g", nuevaX, nuevaY));
                    }
                }
            }
        }

        // Convertir todos los "g" en "G"
        for (int i = 0; i < mapa.getFilas(); i++) {
            for (int j = 0; j < mapa.getColumnas(); j++) {
                if ("g".equals(mapa.getCeldaNombre(i, j))) {
                    mapa.setCelda(i, j, new Celda("G", i, j));
                }
            }
        }

        return 1;
    }

}



