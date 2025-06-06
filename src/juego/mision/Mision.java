package juego.mision;

import juego.personaje.Snake;

public abstract class Mision {

    protected String mision;

    protected Mision(String mision) {
        this.mision = mision;
    }

    public abstract void iniciar(Snake snake);

}
