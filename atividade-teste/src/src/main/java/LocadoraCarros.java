package teste;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LocadoraCarros {
    private List<Carro> carros;
    private List<Locacao> locacoes;

    public LocadoraCarros() {
        this.carros = new ArrayList<>();
        this.locacoes = new ArrayList<>();
    }

    public void adicionarCarro(Carro carro) {
        carros.add(carro);
    }

    public Locacao fazerReserva(Cliente cliente, int idCarro, LocalDate dataInicio, LocalDate dataFim) {
        Carro carro = buscarCarro(idCarro);
        if (carro != null && carro.isDisponivel()) {
            Locacao locacao = new Locacao(cliente, carro, dataInicio, dataFim);
            locacoes.add(locacao);
            carro.reservar(); // Atualiza a disponibilidade do carro
            return locacao;
        }
        return null; // Retornar null se o carro não estiver disponível ou o modelo informado não existir
    }

    private Carro buscarCarro(int idCarro) {
        for (Carro carro : carros) {
            if (carro.getId() == idCarro) {
                return carro;
            }
        }
        return null;
    }

    // Getters e Setters para carros e locações
    public List<Carro> getCarros() {
        return new ArrayList<>(carros); 
    }

    public void setCarros(List<Carro> carros) {
        this.carros = new ArrayList<>(carros); 
    }

    public List<Locacao> getLocacoes() {
        return new ArrayList<>(locacoes); 
    }

    public void setLocacoes(List<Locacao> locacoes) {
        this.locacoes = new ArrayList<>(locacoes); 
    }
}
