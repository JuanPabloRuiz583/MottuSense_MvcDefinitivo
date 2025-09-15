package br.com.fiap.Mottusense.repositorys;


import br.com.fiap.Mottusense.models.SensorLocalizacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorLocalizacaoRepository extends JpaRepository<SensorLocalizacao, Long> {
    boolean existsByLatitudeAndLongitudeAndTimeDaLocalizacao(double latitude, double longitude, java.time.LocalDateTime timeDaLocalizacao);
    SensorLocalizacao findByLatitudeAndLongitudeAndTimeDaLocalizacao(double latitude, double longitude, java.time.LocalDateTime timeDaLocalizacao);
}
