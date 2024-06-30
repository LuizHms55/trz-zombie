package trz.tvirus.api.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trz.tvirus.api.dto.AtualizarInventarioDTO;
import trz.tvirus.api.dto.AtualizarLocalizacaoDTO;
import trz.tvirus.api.dto.SobreviventeDTO;
import trz.tvirus.domain.model.Sobrevivente;
import trz.tvirus.domain.service.SobreviventeService;

import java.util.List;

@RestController
@RequestMapping("/sobreviventes")
@AllArgsConstructor
public class SobreviventeController {

    private final SobreviventeService sobreviventeService;

    @GetMapping
    public List<Sobrevivente> buscarTodos() {
        return sobreviventeService.buscarTodos();
    }

    @PostMapping
    public ResponseEntity<SobreviventeDTO> cadastrar(@RequestBody @Valid SobreviventeDTO sobreviventeDTO){
        return sobreviventeService.cadastrar(sobreviventeDTO);
    }

    @PutMapping
    @RequestMapping("/localizacao")
    public ResponseEntity<SobreviventeDTO> atualizarLocal(@RequestBody @Valid AtualizarLocalizacaoDTO localizacaoDTO) throws NoSuchMethodException {
        return sobreviventeService.atualizarLocal(localizacaoDTO);
    }

    @PutMapping
    @RequestMapping("/inventario")
    public ResponseEntity<SobreviventeDTO> atualizarInventario(@RequestBody @Valid AtualizarInventarioDTO inventarioDTO) throws NoSuchMethodException {
        return sobreviventeService.atualizarInventario(inventarioDTO);
    }


}
