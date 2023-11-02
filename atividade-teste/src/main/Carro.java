public class Carro {
    private int id;
    private String marca;
    private String modelo;
    private int ano;
    private double precoPorDia;
    private boolean disponivel;

    public Carro(int id, String marca, String modelo, int ano, double precoPorDia) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.precoPorDia = precoPorDia;
        this.disponivel = true;
    }

    public int getId() {
        return id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public double getPrecoPorDia() {
        return precoPorDia;
    }

    public void setPrecoPorDia(double precoPorDia) {
        this.precoPorDia = precoPorDia;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void reservar() {
        disponivel = false;
    }

    public void liberar() {
        disponivel = true;
    }

    @Override
    public String toString() {
        return "Carro [id=" + id + ", marca=" + marca + ", modelo=" + modelo + ", ano=" + ano
                + ", precoPorDia=" + precoPorDia + ", disponivel=" + disponivel + "]";
    }
}
