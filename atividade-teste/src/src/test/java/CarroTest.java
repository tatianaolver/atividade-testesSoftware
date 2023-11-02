package teste;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CarroTest {
    private Carro carro;

    @Mock
    private Cliente clienteMock;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        carro = new Carro(101, "MarcaCarro", "ModeloCarro", 2022, 100.0);
    }

    @Test
    public void fazerReservaCarroDisponivel() {
        when(clienteMock.getNome()).thenReturn("ClienteTest");

        Locacao locacao = carro.fazerReserva(clienteMock, LocalDate.now(), LocalDate.now().plusDays(3));

        assertNotNull(locacao, "A reserva deve ser bem-sucedida para um carro disponível.");
        assertTrue(locacao.isReservado(), "O carro deve estar reservado após a reserva ser bem-sucedida.");
    }

    @Test
    public void fazerReservaCarroIndisponivel() {
        when(clienteMock.getNome()).thenReturn("ClienteTest");

        carro.reservar(); // Simulação caso o carro já esteja reservado

        Locacao locacao = carro.fazerReserva(clienteMock, LocalDate.now(), LocalDate.now().plusDays(3));

        assertNull(locacao, "A reserva deve falhar para um carro indisponível.");
    }

    @Test
    public void verificarCarroDisponivelAposReserva() {
        when(clienteMock.getNome()).thenReturn("ClienteTest");

        Locacao locacao = carro.fazerReserva(clienteMock, LocalDate.now(), LocalDate.now().plusDays(3));

        assertFalse(carro.isDisponivel(), "O carro não deve estar disponível após ser reservado.");
    }
    @Test
    public void verificarCarroDisponivelAposCancelamentoReserva() {
        when(clienteMock.getNome()).thenReturn("ClienteTest");

        Locacao locacao = carro.fazerReserva(clienteMock, LocalDate.now(), LocalDate.now().plusDays(3));
        carro.cancelarReserva(locacao);

        assertTrue(carro.isDisponivel(), "O carro deve estar disponível após o cancelamento de reserva.");
    }

    @Test
    public void verificarPrecoTotalDaReserva() {
        when(clienteMock.getNome()).thenReturn("ClienteTest");
    
        LocalDate dataInicio = LocalDate.now();
        LocalDate dataFim = LocalDate.now().plusDays(5);

        Locacao locacao = carro.fazerReserva(clienteMock, dataInicio, dataFim);

        double precoEsperado = carro.getDiaria() * (dataFim.getDayOfYear() - dataInicio.getDayOfYear());
        assertEquals(precoEsperado, locacao.getPrecoTotal(), 0.001, "O preço total da reserva deve ser calculado corretamente.");
    }

    @Test
    public void verificarReservaComDatasInvalidas() {
        when(clienteMock.getNome()).thenReturn("ClienteTest");

        LocalDate dataInicio = LocalDate.now();
        LocalDate dataFim = LocalDate.now().minusDays(2);

        Locacao locacao = carro.fazerReserva(clienteMock, dataInicio, dataFim);

        assertNull(locacao, "A reserva deve falhar para datas de início e término inválidas.");
    }
}
