package trz.tvirus.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import trz.tvirus.domain.model.Recurso;
import trz.tvirus.domain.model.Sobrevivente;

import java.util.List;
@Data
public class AtualizarInventarioDTO {

    private String id;

    private List<Recurso> recursos;

}
