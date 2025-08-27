package br.com.fiap.Mottusense.services;

import br.com.fiap.Mottusense.models.Patio;
import br.com.fiap.Mottusense.repositorys.PatioRepository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatioService {

    private final PatioRepository patioRepository;

    public PatioService(PatioRepository patioRepository) {
        this.patioRepository = patioRepository;
    }

    @Cacheable("patios")
    public List<Patio> getAllPatios() {
        return patioRepository.findAll();
    }

    public Patio save(Patio patio) {
        return patioRepository.save(patio);
    }
}