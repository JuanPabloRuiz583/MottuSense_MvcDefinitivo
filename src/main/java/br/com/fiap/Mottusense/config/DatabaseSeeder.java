package br.com.fiap.Mottusense.config;

import br.com.fiap.Mottusense.models.Patio;
import br.com.fiap.Mottusense.repositorys.PatioRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseSeeder {
    @Autowired
    private PatioRepository patioRepository;

    @PostConstruct
    public void init() {
        if (patioRepository.count() == 0) {
            var patio1 = Patio.builder()
                    .nome("Pátio Central")
                    .endereco("Rua Principal, 123")
                    .build();

            var patio2 = Patio.builder()
                    .nome("Pátio Norte")
                    .endereco("Avenida Secundária, 456")
                    .build();


            var patio3 = Patio.builder()
                    .nome("Pátio Sul")
                    .endereco("Rua das Flores, 789")
                    .build();

            var patio4 = Patio.builder()
                    .nome("Pátio Leste")
                    .endereco("Avenida das Palmeiras, 321")
                    .build();

            patioRepository.saveAll(List.of(patio1, patio2, patio3, patio4));
        }
    }
}
