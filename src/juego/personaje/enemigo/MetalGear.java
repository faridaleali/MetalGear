package juego.personaje.enemigo;

import juego.mapa.Mapa;
import juego.personaje.Personaje;

public class MetalGear extends Personaje implements Enemigo {
    private int vida;
    private final int vidaMaxima = 100;
    private int ataque;

    public MetalGear(int posicionX, int posicionY, String personaje) {
        super(posicionX, posicionY, personaje);
        this.vida = vidaMaxima;
        this.ataque = ataque;
    }

    @Override
    public int mover(int movimiento, Mapa mapa) {
        return 0;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }
}
