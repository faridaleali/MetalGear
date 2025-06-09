package juego.personaje.enemigo;

import java.util.Random;
import juego.mapa.Mapa;
import juego.personaje.Personaje;

public class MetalGear extends Personaje implements Enemigo {
    private int vida;
    private final int vidaMaxima = 100;

    public MetalGear(int posicionX, int posicionY, String personaje) {
        super(posicionX, posicionY, personaje);
        this.vida = vidaMaxima;
    }

    @Override
    public int mover(int movimiento, Mapa mapa) {
        return 0;
    }

    public int ataque() {
        Random random = new Random();
        int ataqueRex = random.nextInt(26) + 15;

        return ataqueRex;
    }

    public int ataqueReducido() {
        Random random = new Random();
        int ataqueRex = random.nextInt(51) + 50;

        return ataqueRex;
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
