package br.com.fiap.Mottusense.services;

import br.com.fiap.Mottusense.models.Moto;
import br.com.fiap.Mottusense.repositorys.MotoRepository;
import br.com.fiap.Mottusense.repositorys.PatioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotoService implements DuplicidadeValidator<Moto> {

    private final MotoRepository motoRepository;
    private final PatioRepository patioRepository;

    public MotoService(MotoRepository motoRepository, PatioRepository patioRepository) {
        this.motoRepository = motoRepository;
        this.patioRepository = patioRepository;
    }
    public List<Moto> getAllMotos() {
        return motoRepository.findAll();
    }

    public Moto save(Moto moto) {
        return motoRepository.save(moto);
    }

    public void deleteById(Long id) {
        motoRepository.deleteById(id);
    }


    //para cadastro
    @Override
    public String validarDuplicidadeCadastro(Moto moto) {
        if (motoRepository.existsByPlaca(moto.getPlaca())) {
            return "Placa já cadastrada.";
        }
        if (motoRepository.existsByNumeroChassi(moto.getNumeroChassi())) {
            return "Número de chassi já cadastrado.";
        }
        return null;
    }

    //para edicao
    @Override
    public String validarDuplicidadeEdicao(Moto moto, Long idAtual) {
        var existentePorPlaca = motoRepository.findByPlaca(moto.getPlaca());
        if (existentePorPlaca != null && !existentePorPlaca.getId().equals(idAtual)) {
            return "Placa já cadastrada.";
        }
        var existentePorChassi = motoRepository.findByNumeroChassi(moto.getNumeroChassi());
        if (existentePorChassi != null && !existentePorChassi.getId().equals(idAtual)) {
            return "Número de chassi já cadastrado.";
        }
        return null;
    }

    public Moto findById(Long id) {
        return motoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Moto não encontrada"));
    }

    public List<Moto> findByPlacaContainingIgnoreCase(String placa) {
        return motoRepository.findByPlacaContainingIgnoreCase(placa);
    }
}