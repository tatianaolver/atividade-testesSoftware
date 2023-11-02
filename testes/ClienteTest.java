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

public class ClienteTest {
    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        cliente = new Cliente(1, "NomeCliente", "EnderecoCliente", "TelefoneCliente");
    }

    @Test
    public void fazerReservaCarroDisponivel() {
        Carro carroMock = mock(Carro.class);
        when(carroMock.isDisponivel()).thenReturn(true);
        when(carroMock.getId()).thenReturn(101);

        Locacao locacao = cliente.fazerReserva(carroMock, LocalDate.now(), LocalDate.now().plusDays(3));

        assertNotNull(locacao, "A reserva deve ser bem-sucedida para um carro disponível.");
        verify(carroMock).reservar();
    }

    @Test
    public void fazerReservaCarroIndisponivel() {
        Carro carroMock = mock(Carro.class);
        when(carroMock.isDisponivel()).thenReturn(false);
        when(carroMock.getId()).thenReturn(101);

        Locacao locacao = cliente.fazerReserva(carroMock, LocalDate.now(), LocalDate.now().plusDays(3));

        assertNull(locacao, "A reserva deve falhar para um carro indisponível.");
        verify(carroMock, never()).reservar();
    }

    @Test
    public void verificarCarroDisponivelAposReserva() {
        Carro carroMock = mock(Carro.class);
        when(carroMock.isDisponivel()).thenReturn(true);
        when(carroMock.getId()).thenReturn(101);

        Locacao locacao = cliente.fazerReserva(carroMock, LocalDate.now(), LocalDate.now().plusDays(3));

        assertFalse(carroMock.isDisponivel(), "O carro não deve estar disponível após ser reservado.");
    }
}
