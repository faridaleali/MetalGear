package juego.personaje.enemigo;

import juego.mapa.Mapa;
import juego.personaje.Personaje;

public class MetalGear extends Personaje implements Enemigo {


    public MetalGear(int posicionX, int posicionY, String personaje) {
        super(posicionX, posicionY, personaje);
    }

    @Override
    public int mover(int movimiento, Mapa mapa) {
        return 0;
    }
}
