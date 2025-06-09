package juego.personaje;

import juego.mapa.Celda;
import juego.mapa.Mapa;
import juego.objeto.Objeto;

public class Snake extends Personaje {

    protected Objeto objetoRecogido;
    protected int vida;
    protected final int vidaMaxima = 100;

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
        String nombreCelda = mapa.getCeldaNombre(nuevaX, nuevaY);

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
                    int nuevaFilaArriba = nuevaX - i;
                    int nuevaFilaAbajo = nuevaX + i;
                    int nuevaColIzquierda = nuevaY - i;
                    int nuevaColDerecha = nuevaY + i;

                    if (mapa.posicionValida(nuevaX, nuevaColIzquierda)) {
                        Celda celda = mapa.getCeldaObjeto(nuevaX, nuevaColIzquierda);
                        if (celda.getNombre().equals("G")) {
                            System.out.println("Te capturaron: enemigo escuchó la explosión horizontalmente.");
                            return 4;
                        }
                    }
                    if (mapa.posicionValida(nuevaX, nuevaColDerecha)) {
                        Celda celda = mapa.getCeldaObjeto(nuevaX, nuevaColDerecha);
                        if (celda.getNombre().equals("G")) {
                            System.out.println("Te capturaron: enemigo escuchó la explosión horizontalmente.");
                            return 4;
                        }
                    }

                    if (mapa.posicionValida(nuevaFilaArriba, nuevaY)) {
                        Celda celda = mapa.getCeldaObjeto(nuevaFilaArriba, nuevaY);
                        if (celda.getNombre().equals("G")) {
                            System.out.println("Te capturaron: enemigo escuchó la explosión verticalmente.");
                            return 4;
                        }
                    }
                    if (mapa.posicionValida(nuevaFilaAbajo, nuevaY)) {
                        Celda celda = mapa.getCeldaObjeto(nuevaFilaAbajo, nuevaY);
                        if (celda.getNombre().equals("G")) {
                            System.out.println("Te capturaron: enemigo escuchó la explosión verticalmente.");
                            return 4;
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
            mapa.setCelda(nuevaX, nuevaY, celdaDestino);
        }

        if (!mapa.celdaLibre(nuevaX, nuevaY)) {
            System.out.println("Celda ocupada.");
            return -1;
        }

        mapa.setCelda(posicionX, posicionY, new Celda(posicionX, posicionY)); // borra celda anterior
        posicionX = nuevaX;
        posicionY = nuevaY;

        Celda nuevaCelda = new Celda("S", posicionX, posicionY);
        mapa.setCelda(posicionX, posicionY, nuevaCelda); // coloca Snake en nueva celda

        return 1;
    }

    public Objeto getObjetoRecogido() {
        return objetoRecogido;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = Math.max(0, Math.min(vida, vidaMaxima));
    }

    public void recibirDanio(int danio) {
        this.vida = Math.max(0, this.vida - danio);
    }

    public void curar(int cantidad) {
        this.vida = Math.min(vidaMaxima, this.vida + cantidad);
    }
}