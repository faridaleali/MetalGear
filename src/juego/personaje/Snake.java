package juego.personaje;

import juego.mapa.Celda;
import juego.mapa.Mapa;
import juego.objeto.Objeto;

public class Snake extends Personaje {

    private Objeto objetoRecogido;

    private int vida;
    private final int vidaMaxima = 100;

    public Snake(int posicionX, int posicionY, String personaje) {
        super(posicionX, posicionY, personaje);
        this.vida = vidaMaxima;
    }

    @Override
    public int mover(int movimiento, Mapa mapa) {
        int nuevaX = posicionX;
        int nuevaY = posicionY;

        switch (movimiento) {
            case 1 -> nuevaX -= 1; // arriba
            case 2 -> nuevaX += 1; // abajo
            case 3 -> nuevaY -= 1; // izquierda
            case 4 -> nuevaY += 1; // derecha
            default -> {
                System.out.println("Movimiento no válido.");
                return -1;
            }
        }

        if (!mapa.posicionValida(nuevaX, nuevaY)) {
            System.out.println("Te sales del mapa.");
            return -1;
        }

        Celda celdaDestino = mapa.getCeldaObjeto(nuevaX, nuevaY);
        String nombreCelda = celdaDestino.getNombre();

        if (nombreCelda.equals("H")) {
            if (objetoRecogido != null) {
                System.out.println("Llegaste al helipuerto con el objeto necesario. ¡Nivel completado!");
                objetoRecogido = null;
                return 2;
            } else {
                System.out.println("Necesitas un objeto para completar el nivel.");
                return -1;
            }
        }

        if (nombreCelda.equals("P")) {
            if (objetoRecogido != null) {
                for (int i = 0; i <= 2; i++) {

                    int nuevaFilaArriba = posicionX + i;
                    int nuevaFilaAbajo = posicionX - i;
                    int nuevaColArriba = posicionY + i;
                    int nuevaColAbajo = posicionY - i;

                    if (mapa.posicionValida(posicionX, nuevaColArriba)) {
                        Celda celda = mapa.getCeldaObjeto(posicionX, nuevaColArriba);
                        if (celda.getNombre().equals("G")) {
                            System.out.println("Te capturaron: enemigo escuchó la explosión horizontalmente.");
                            return 2;
                        }
                    }
                    if (mapa.posicionValida(posicionX, nuevaColAbajo)) {
                        Celda celda = mapa.getCeldaObjeto(posicionX, nuevaColAbajo);
                        if (celda.getNombre().equals("G")) {
                            System.out.println("Te capturaron: enemigo escuchó la explosión horizontalmente.");
                            return 2;
                        }
                    }
                    if (mapa.posicionValida(nuevaFilaArriba, posicionY)) {
                        Celda celda = mapa.getCeldaObjeto(nuevaFilaArriba, posicionY);
                        if (celda.getNombre().equals("G")) {
                            System.out.println("Te capturaron: enemigo escuchó la explosión verticalmente.");
                            return 2;
                        }
                    }
                    if (mapa.posicionValida(nuevaFilaAbajo, posicionY)) {
                        Celda celda = mapa.getCeldaObjeto(nuevaFilaAbajo, posicionY);
                        if (celda.getNombre().equals("G")) {
                            System.out.println("Te capturaron: enemigo escuchó la explosión verticalmente.");
                            return 2;
                        }
                    }
                }

                System.out.println("Llegaste a la puerta con la C4. ¡Nivel completado!");
                return 3;

            } else {
                System.out.println("Necesitas tener la C4 para completar el nivel.");
                return -1;
            }
        }


        if (celdaDestino.getTipo() != null && objetoRecogido == null) {
            objetoRecogido = celdaDestino.getTipo();
            System.out.println(objetoRecogido.recoger());

            mapa.setCelda(nuevaX, nuevaY, new Celda());
        }

        if (!mapa.celdaLibre(nuevaX, nuevaY)) {
            System.out.println("Celda ocupada.");
            return -1;
        }

        mapa.setCelda(posicionX, posicionY, new Celda());

        posicionX = nuevaX;
        posicionY = nuevaY;

        Celda nuevaCelda = new Celda("S");
        mapa.setCelda(posicionX, posicionY, nuevaCelda);

        return 1;
    }

    public Objeto getObjetoRecogido() {
        return objetoRecogido;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = Math.max(0, Math.min(vida, vidaMaxima)); // mantiene entre 0 y 100
    }

    public void recibirDanio(int danio) {
        this.vida = Math.max(0, this.vida - danio);
    }

    public void curar(int cantidad) {
        this.vida = Math.min(vidaMaxima, this.vida + cantidad);
    }
}
