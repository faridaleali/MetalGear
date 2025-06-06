package juego.mapa;

import juego.objeto.Objeto;

public class Celda {
    private boolean ocupada;
    private String nombre;
    private Objeto tipo;

    public Celda() {
        this.nombre = " ";
        this.tipo = null;
        this.ocupada = false;
    }

    public Celda(String nombre) {
        this.nombre = nombre;
        this.tipo = asignarObjeto(nombre);
        this.ocupada = nombre.equals("S") || nombre.equals("G");
    }

    public void setCeldaPersonaje(String nombre) {
        if ("G".equals(nombre) || "S".equals(nombre)) {
            setNombre(nombre);
            setOcupada(true);
        } else {
            System.err.println("Nombre de personaje no válido: " + nombre);
        }
    }

    public void setCeldaObjeto(String nombre) {
        if ("H".equals(nombre) || "L".equals(nombre) || "C4".equals(nombre) || "P".equals(nombre)) {
            setNombre(nombre);
            this.tipo = asignarObjeto(nombre);
        } else {
            System.err.println("Nombre de objeto no válido: " + nombre);
        }
    }

    public Objeto asignarObjeto(String nombre) {
        return switch (nombre) {
            case "H" -> new Objeto("Helipuerto");
            case "L" -> new Objeto("Llave");
            case "C4" -> new Objeto("Explosivo");
            case "P" -> new Objeto("Pistola");
            default -> null;
        };
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Objeto getTipo() {
        return tipo;
    }

    public void setTipo(Objeto tipo) {
        this.tipo = tipo;
    }
}
