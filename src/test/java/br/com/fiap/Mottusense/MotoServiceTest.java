package br.com.fiap.Mottusense;

import br.com.fiap.Mottusense.models.Moto;
import br.com.fiap.Mottusense.models.StatusMoto;
import br.com.fiap.Mottusense.repositorys.MotoRepository;
import br.com.fiap.Mottusense.repositorys.PatioRepository;
import br.com.fiap.Mottusense.services.MotoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MotoServiceTest {

    @Mock
    private MotoRepository motoRepository;

    @Mock
    private PatioRepository patioRepository;

    @InjectMocks
    private MotoService motoService;

    private Moto moto1;
    private Moto moto2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        moto1 = Moto.builder()
                .id(1L)
                .placa("ABC1234")
                .modelo("CG 160")
                .numeroChassi("123456789")
                .status(StatusMoto.DISPONIVEL)
                .patio(null)
                .build();

        moto2 = Moto.builder()
                .id(2L)
                .placa("DEF5678")
                .modelo("Fazer 250")
                .numeroChassi("987654321")
                .status(StatusMoto.EM_MANUTENCAO)
                .patio(null)
                .build();
    }

    @Test
    void deveRetornarTodasAsMotos() {
        when(motoRepository.findAll()).thenReturn(List.of(moto1, moto2));

        List<Moto> motos = motoService.getAllMotos();

        assertEquals(2, motos.size());
        verify(motoRepository, times(1)).findAll();
    }

    @Test
    void deveBuscarMotoPorId() {
        when(motoRepository.findById(1L)).thenReturn(Optional.of(moto1));

        Moto motoEncontrada = motoService.findById(1L);

        assertNotNull(motoEncontrada);
        assertEquals("ABC1234", motoEncontrada.getPlaca());
        verify(motoRepository, times(1)).findById(1L);
    }

    @Test
    void deveSalvarMoto() {
        when(motoRepository.save(moto1)).thenReturn(moto1);

        Moto motoSalva = motoService.save(moto1);

        assertEquals("CG 160", motoSalva.getModelo());
        verify(motoRepository, times(1)).save(moto1);
    }

    @Test
    void deveDeletarMoto() {
        motoService.deleteById(1L);

        verify(motoRepository, times(1)).deleteById(1L);
    }
}
