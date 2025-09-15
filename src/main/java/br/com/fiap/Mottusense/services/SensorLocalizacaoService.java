package br.com.fiap.Mottusense.services;

import br.com.fiap.Mottusense.models.SensorLocalizacao;
import br.com.fiap.Mottusense.models.Moto;
import br.com.fiap.Mottusense.repositorys.SensorLocalizacaoRepository;
import br.com.fiap.Mottusense.repositorys.MotoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SensorLocalizacaoService implements DuplicidadeValidator<SensorLocalizacao> {

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



    // Validação de duplicidade para cadastro
    @Override
    public String validarDuplicidadeCadastro(SensorLocalizacao sensor) {
        boolean existe = repository.existsByLatitudeAndLongitudeAndTimeDaLocalizacao(
                sensor.getLatitude(), sensor.getLongitude(), sensor.getTimeDaLocalizacao());
        if (existe) {
            return "Já existe um sensor cadastrado para esta localização e data/hora.";
        }
        return null;
    }

    // Validação de duplicidade para edição
    @Override
    public String validarDuplicidadeEdicao(SensorLocalizacao sensor, Long idAtual) {
        SensorLocalizacao existente = repository.findByLatitudeAndLongitudeAndTimeDaLocalizacao(
                sensor.getLatitude(), sensor.getLongitude(), sensor.getTimeDaLocalizacao());
        if (existente != null && !existente.getId().equals(idAtual)) {
            return "Já existe um sensor cadastrado para esta localização e data/hora.";
        }
        return null;
    }
}