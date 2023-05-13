package model.vo;

public class Sala {

    private int id;
    private String numero;
    private boolean disponivel;

    public Sala(int id, String numero, boolean disponivel) {
        this.id = id;
        this.numero = numero;
        this.disponivel = disponivel;
    }

    public Sala() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public String toString() {
        return "Sala{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", disponivel=" + disponivel +
                '}';
    }
}
