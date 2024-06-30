package trz.tvirus.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import trz.tvirus.domain.model.Sobrevivente;

public interface SobreviventeRepository extends JpaRepository<Sobrevivente, String> {
}
