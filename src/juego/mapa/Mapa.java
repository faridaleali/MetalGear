package juego.mapa;

public class Mapa {

    private final int filas;
    private final int columnas;
    private final String nivel;
    private Celda[][] celdas;

    public Mapa(int filas, int columnas, String nivel) {
        this.filas = filas;
        this.columnas = columnas;
        this.nivel = nivel;
    }

    public void crearMapa() {
        celdas = new Celda[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                celdas[i][j] = new Celda();
            }
        }
    }

    public void completarMapa() {
        if ("Principal".equals(nivel)) {
            celdas[0][3].setCeldaObjeto("H");
            celdas[1][2].setCeldaPersonaje("G");
            celdas[2][3].setCeldaPersonaje("G");
            celdas[3][0].setCeldaObjeto("L");
            celdas[4][2].setCeldaPersonaje("G");
            celdas[4][5].setCeldaPersonaje("G");
            celdas[5][3].setCeldaPersonaje("G");
            celdas[6][0].setCeldaPersonaje("S");
        } else if ("Intermedia".equals(nivel)) {
            celdas[1][0].setCeldaPersonaje("G");
            celdas[1][8].setCeldaObjeto("P");
            celdas[2][6].setCeldaPersonaje("G");
            celdas[3][7].setCeldaPersonaje("G");
            celdas[5][2].setCeldaObjeto("C4");
            celdas[7][1].setCeldaPersonaje("G");
            celdas[8][8].setCeldaPersonaje("S");
        }
    }

    public void mostrarMapa() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print("[" + celdas[i][j].getNombre() + "]");
            }
            System.out.println();
        }
    }

    public Celda getCeldaObjeto(int fila, int columna) {
        return celdas[fila][columna];
    }

    public String getCeldaNombre(int fila, int columna) {
        return celdas[fila][columna].getNombre();
    }

    public boolean posicionValida(int fila, int columna) {
        return fila >= 0 && fila < filas && columna >= 0 && columna < columnas;
    }

    public boolean celdaLibre(int fila, int columna) {
        Celda celda = celdas[fila][columna];
        return !celda.isOcupada() || celda.getNombre().equals("L") || celda.getNombre().equals("C4") || celda.getNombre().equals("P") || celda.getNombre().equals("H");
    }

    public Celda[][] getCeldas() {
        return celdas;
    }

    public void setCelda(int fila, int columna, Celda celda) {
        if (posicionValida(fila, columna)) {
            celdas[fila][columna] = celda;
        }
    }

    public boolean snakeAtrapado() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (celdas[i][j].getNombre().equals("G")) {
                    if (esSnake(i - 1, j) || esSnake(i + 1, j) || esSnake(i, j - 1) || esSnake(i, j + 1)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean esSnake(int fila, int columna) {
        return posicionValida(fila, columna) && celdas[fila][columna].getNombre().equals("S");
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }
}
