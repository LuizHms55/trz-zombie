package trz.tvirus.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import trz.tvirus.domain.utils.enums.Genero;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sobrevivente {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank
    private String nome;

    @NotNull
    private Genero genero;

    @OneToOne(cascade = CascadeType.ALL)
    private Localizacao localizacao;

    @OneToOne(cascade = CascadeType.ALL)
    private Inventario inventario;
}
