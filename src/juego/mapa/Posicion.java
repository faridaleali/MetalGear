package juego.mapa;

import juego.personaje.Personaje;

public class Posicion {

    public Personaje personaje;
    public int PosicionX;
    public int PosicionY;

    public Posicion (int PosicionX, int PosicionY) {
        this.PosicionX = PosicionX;
        this.PosicionY = PosicionY;
    }

    public Personaje getPersonaje() {
        return personaje;
    }

    public void setPersonaje(Personaje personaje) {
        this.personaje = personaje;
    }

    public int getPosicionX() {
        return PosicionX;
    }

    public void setPosicionX(int x) {
        this.PosicionX = x;
    }

    public int getPosicionY() {
        return PosicionY;
    }

    public void setPosicionY(int y) {
        this.PosicionY = y;
    }
}
