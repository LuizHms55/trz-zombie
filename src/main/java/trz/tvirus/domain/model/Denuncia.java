package trz.tvirus.domain.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Denuncia {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    private Sobrevivente sobrevivente;
}
