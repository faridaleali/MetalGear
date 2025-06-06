package juego.objeto;

public class Objeto {

    private String tipo;
    private boolean recogido;

    public Objeto(String tipo) {
        this.tipo = tipo;
        this.recogido = false;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isRecogido() {
        return recogido;
    }

    public String recoger() {
        this.recogido = true;
        return "Has recogido: " + tipo;
    }

    @Override
    public String toString() {
        return tipo;
    }
}
