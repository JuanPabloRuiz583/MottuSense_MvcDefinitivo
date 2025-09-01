package br.com.fiap.Mottusense.services;

import br.com.fiap.Mottusense.models.Moto;
import br.com.fiap.Mottusense.repositorys.MotoRepository;
import br.com.fiap.Mottusense.repositorys.PatioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotoService {

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


    public String validarDuplicidade(Moto moto) {
        if (motoRepository.existsByPlaca(moto.getPlaca())) {
            return "Placa já cadastrada.";
        }
        if (motoRepository.existsByNumeroChassi(moto.getNumeroChassi())) {
            return "Número de chassi já cadastrado.";
        }
        return null;
    }


}