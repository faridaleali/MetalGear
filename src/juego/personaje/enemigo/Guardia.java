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
                        case 0 -> nuevaX--;
                        case 1 -> nuevaX++;
                        case 2 -> nuevaY--;
                        case 3 -> nuevaY++;
                    }

                    if (mapa.posicionValida(nuevaX, nuevaY) &&
                        !mapa.getCeldaNombre(nuevaX, nuevaY).equals("H") &&
                        !mapa.getCeldaNombre(nuevaX, nuevaY).equals("C4") &&
                        !mapa.getCeldaNombre(nuevaX, nuevaY).equals("L") &&
                        !mapa.getCeldaNombre(nuevaX, nuevaY).equals("P") &&
                        mapa.celdaLibre(nuevaX, nuevaY)) {

                        mapa.setCelda(i, j, new Celda());

                        Celda nuevaCelda = new Celda("G");
                        mapa.setCelda(nuevaX, nuevaY, nuevaCelda);
                    }
                }
            }
        }
        return 1;
    }
}



