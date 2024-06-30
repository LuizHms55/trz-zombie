package trz.tvirus.api.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import trz.tvirus.domain.model.Recurso;
import trz.tvirus.domain.service.SobreviventeService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/estatisticas")
@AllArgsConstructor
public class EstatisticasController {

    private final SobreviventeService sobreviventeService;

    @GetMapping("/infectados")
    public double taxaInfectados() {
        return sobreviventeService.taxaInfectados();
    }

    @GetMapping("/ninfectados")
    public double taxaSobreviventes() {
        return sobreviventeService.taxaSobreviventes();
    }

    @GetMapping("/recursos/sobreviventes")
    public List<Recurso> recursosSobreviventes() {
        return sobreviventeService.recursosSobreviventes();
    }

    @GetMapping("/recursos/infectados")
    public List<Recurso> recursosInfectados() {
        return sobreviventeService.recursosInfectados();
    }

}
