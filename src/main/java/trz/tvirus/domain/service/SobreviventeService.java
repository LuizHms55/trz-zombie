package trz.tvirus.domain.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import trz.tvirus.api.dto.AtualizarInventarioDTO;
import trz.tvirus.api.dto.AtualizarLocalizacaoDTO;
import trz.tvirus.api.dto.SobreviventeDTO;
import trz.tvirus.domain.model.Inventario;
import trz.tvirus.domain.model.Sobrevivente;
import trz.tvirus.domain.repository.SobreviventeRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SobreviventeService {

    private final ModelMapper modelMapper;

    private final SobreviventeRepository sobreviventeRepository;

    public ResponseEntity<SobreviventeDTO> cadastrar(SobreviventeDTO sobreviventeDTO) {
        Sobrevivente sobrevivente = modelMapper.map(sobreviventeDTO, Sobrevivente.class);

        sobreviventeRepository.save(sobrevivente);

        return ResponseEntity.status(HttpStatus.CREATED).body(sobreviventeDTO);
    }

    public List<Sobrevivente> buscarTodos() {
        return sobreviventeRepository.findAll();
    }

    public Sobrevivente buscarPorId(String id) throws NoSuchMethodException {
        Optional<Sobrevivente> sobrevivente = sobreviventeRepository.findById(id);

        if (sobrevivente.isEmpty()) {
            throw new NoSuchMethodException("id não encontrado");
        }
        return modelMapper.map(sobrevivente.get(), Sobrevivente.class);
    }

    public ResponseEntity<SobreviventeDTO> atualizarLocal(AtualizarLocalizacaoDTO atualizarLocalizacaoDTO) throws NoSuchMethodException {
        Sobrevivente sobrevivente = buscarPorId(atualizarLocalizacaoDTO.getId());

        sobrevivente.getLocalizacao().setLatitude(atualizarLocalizacaoDTO.getLatitude());
        sobrevivente.getLocalizacao().setLongitude(atualizarLocalizacaoDTO.getLongitude());

        sobreviventeRepository.save(sobrevivente);

        SobreviventeDTO sobreviventeDTO = modelMapper.map(sobrevivente, SobreviventeDTO.class);
        return ResponseEntity.ok(sobreviventeDTO);
    }

    public ResponseEntity<SobreviventeDTO> atualizarInventario(AtualizarInventarioDTO atualizarInventarioDTO) throws NoSuchMethodException {
        Sobrevivente sobrevivente = buscarPorId(atualizarInventarioDTO.getId());

        Inventario inventario = new Inventario();
        inventario.setRecursos(atualizarInventarioDTO.getRecursos());
        inventario.setSobrevivente(sobrevivente);

        sobrevivente.setInventario(inventario);

        sobreviventeRepository.save(sobrevivente);

        SobreviventeDTO sobreviventeDTO = modelMapper.map(sobrevivente, SobreviventeDTO.class);
        return ResponseEntity.ok(sobreviventeDTO);

    }
}
