package br.com.fiap.Mottusense.services;

import br.com.fiap.Mottusense.models.Moto;
import br.com.fiap.Mottusense.repositorys.MotoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotoService {

    private final MotoRepository motoRepository;

    public MotoService(MotoRepository motoRepository) {
        this.motoRepository = motoRepository;
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