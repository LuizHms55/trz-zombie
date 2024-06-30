package trz.tvirus.api.dto;

import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import trz.tvirus.domain.model.Inventario;
import trz.tvirus.domain.model.Localizacao;
import trz.tvirus.domain.utils.enums.Genero;

@Data
public class SobreviventeDTO {

    private String nome;

    private Genero genero;

    private Localizacao localizacao;

    private Inventario inventario;
}
