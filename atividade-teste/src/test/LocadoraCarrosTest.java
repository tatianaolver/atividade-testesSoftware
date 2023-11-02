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

public class LocadoraCarrosTest {
    private LocadoraCarros locadora;

    @Mock
    private Carro carroMock;

    @Mock
    private Cliente clienteMock;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        locadora = new LocadoraCarros();
        locadora.adicionarCarro(carroMock);
    }

    @Test
    public void fazerReservaCarroDisponivel() {
        when(carroMock.isDisponivel()).thenReturn(true);
        when(carroMock.getId()).thenReturn(101);

        Locacao locacao = locadora.fazerReserva(clienteMock, 101, LocalDate.now(), LocalDate.now().plusDays(3));

        assertNotNull(locacao, "A reserva deve ser bem-sucedida para um carro disponível.");
        verify(carroMock).reservar();
    }

    @Test
    public void fazerReservaCarroIndisponivel() {
        when(carroMock.isDisponivel()).thenReturn(false);
        when(carroMock.getId()).thenReturn(101);

        Locacao locacao = locadora.fazerReserva(clienteMock, 101, LocalDate.now(), LocalDate.now().plusDays(3));

        assertNull(locacao, "A reserva deve falhar para um carro indisponível.");
        verify(carroMock, never()).reservar();
    }

    @Test
    public void tentarFazerReservaCarroInexistente() {
        Locacao locacao = locadora.fazerReserva(clienteMock, 999, LocalDate.now(), LocalDate.now().plusDays(3));

        assertNull(locacao, "A reserva deve falhar para um modelo de carro inexistente.");
    }

    @Test
    public void adicionarCarroALocadora() {
        Carro novoCarro = mock(Carro.class);
        when(novoCarro.getId()).thenReturn(102);

        locadora.adicionarCarro(novoCarro);
        Locacao locacao = locadora.fazerReserva(clienteMock, 102, LocalDate.now(), LocalDate.now().plusDays(3));

        assertNotNull(locacao, "A reserva deve ser bem-sucedida após adicionar um novo carro.");
    }

    @Test
    public void verificarCarrosDisponiveisAposReserva() {
        when(carroMock.isDisponivel()).thenReturn(true);
        when(carroMock.getId()).thenReturn(101);

        locadora.fazerReserva(clienteMock, 101, LocalDate.now(), LocalDate.now().plusDays(3));

        assertFalse(carroMock.isDisponivel(), "O carro não deve estar disponível após ser reservado.");
    }
}
