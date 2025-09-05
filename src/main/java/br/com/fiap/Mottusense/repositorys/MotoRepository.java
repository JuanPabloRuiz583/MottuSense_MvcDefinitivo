package br.com.fiap.Mottusense.repositorys;
import br.com.fiap.Mottusense.models.Moto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface MotoRepository extends JpaRepository<Moto, Long> {

    boolean existsByPlaca(String placa);
    boolean existsByNumeroChassi(String numeroChassi);
    Moto findByPlaca(String placa);
    Moto findByNumeroChassi(String numeroChassi);
    List<Moto> findByPlacaContainingIgnoreCase(String placa);
}
