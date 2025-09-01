// src/main/java/br/com/fiap/Mottusense/services/SensorLocalizacaoService.java
package br.com.fiap.Mottusense.services;

import br.com.fiap.Mottusense.models.SensorLocalizacao;
import br.com.fiap.Mottusense.models.Moto;
import br.com.fiap.Mottusense.repositorys.SensorLocalizacaoRepository;
import br.com.fiap.Mottusense.repositorys.MotoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SensorLocalizacaoService {

    private final SensorLocalizacaoRepository repository;
    private final MotoRepository motoRepository;

    public SensorLocalizacaoService(SensorLocalizacaoRepository repository, MotoRepository motoRepository) {
        this.repository = repository;
        this.motoRepository = motoRepository;
    }

    public List<SensorLocalizacao> listarTodos() {
        return repository.findAll();
    }

    public void validarSensor(SensorLocalizacao sensorLocalizacao) {
        if (sensorLocalizacao.getTimeDaLocalizacao() != null &&
                sensorLocalizacao.getTimeDaLocalizacao().isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Não pode cadastrar sensores em datas futuras.");
        }
        Moto moto = motoRepository.findByPlaca(sensorLocalizacao.getMoto().getPlaca());
        if (moto == null) {
            throw new IllegalArgumentException("Placa não encontrada");
        }
        sensorLocalizacao.setMoto(moto);
    }

    public SensorLocalizacao salvar(SensorLocalizacao sensorLocalizacao) {
        validarSensor(sensorLocalizacao);
        return repository.save(sensorLocalizacao);
    }

    public SensorLocalizacao buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}