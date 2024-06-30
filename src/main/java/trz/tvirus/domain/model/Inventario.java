package trz.tvirus.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Recurso> recursos;

    @OneToOne
    @JsonIgnore
    private Sobrevivente sobrevivente;
}
