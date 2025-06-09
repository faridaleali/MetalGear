package juego.personaje;

import juego.mapa.Mapa;

public abstract class Personaje {

    protected String personaje;
    protected int posicionX;
    protected int posicionY;

    public Personaje(int posicionX, int posicionY, String personaje) {
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.personaje = personaje;
    }

    public abstract int mover(int movimiento, Mapa mapa);

    public String getPersonaje() {
        return personaje;
    }

    public void setPersonaje(String personaje) {
        this.personaje = personaje;
    }

    public int getPosicionX() {
        return posicionX;
    }

    public void setPosicionX(int posicionX) {
        this.posicionX = posicionX;
    }

    public int getPosicionY() {
        return posicionY;
    }

    public void setPosicionY(int posicionY) {
        this.posicionY = posicionY;
    }
}
