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
import trz.tvirus.domain.model.Denuncia;
import trz.tvirus.domain.model.Inventario;
import trz.tvirus.domain.model.Recurso;
import trz.tvirus.domain.model.Sobrevivente;
import trz.tvirus.domain.repository.SobreviventeRepository;

import java.util.*;

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

        if (sobrevivente.isInfectado()) {
            throw new NoSuchMethodException("Sobrevivente infectado");
        }

        Inventario inventario = new Inventario();
        inventario.setRecursos(atualizarInventarioDTO.getRecursos());
        inventario.setSobrevivente(sobrevivente);

        sobrevivente.setInventario(inventario);

        sobreviventeRepository.save(sobrevivente);

        SobreviventeDTO sobreviventeDTO = modelMapper.map(sobrevivente, SobreviventeDTO.class);
        return ResponseEntity.ok(sobreviventeDTO);
    }

    public ResponseEntity<SobreviventeDTO> indicarInfectado(String id, String id2) throws NoSuchMethodException {
        Sobrevivente denunciador = buscarPorId(id);
        Sobrevivente denunciado = buscarPorId(id2);

        Denuncia denuncia = new Denuncia();
        denuncia.setSobrevivente(denunciador);

        if (!denunciado.getDenuncias().isEmpty()) {
            for (Denuncia denunciaPega : denunciado.getDenuncias()) {
                if (denunciaPega.getSobrevivente() == denunciador) {
                    throw new NoSuchMethodException("Sobrevivente já denunciou!");
                }
            }
            denunciado.getDenuncias().add(denuncia);
        }else {
            denunciado.getDenuncias().add(denuncia);
        }

        if (denunciado.getDenuncias().size() >= 5 ) {
            denunciado.setInfectado(true);
        }

        sobreviventeRepository.save(denunciado);

        SobreviventeDTO sobreviventeDTO = modelMapper.map(denunciado, SobreviventeDTO.class);
        return ResponseEntity.ok(sobreviventeDTO);
    }

    public double taxaSobreviventes() {
        List<Sobrevivente> sobreviventes = sobreviventeRepository.findAll();
        int naoInfectados = 0;
        for (Sobrevivente sobrevivente : sobreviventes) {
            if (!sobrevivente.isInfectado()) {
                naoInfectados++;
            }
        }

        return (naoInfectados * 100.0) / sobreviventes.size();
    }

    public double taxaInfectados() {
        List<Sobrevivente> sobreviventes = sobreviventeRepository.findAll();
        int infectados = 0;
        for (Sobrevivente sobrevivente : sobreviventes) {
            if (sobrevivente.isInfectado()) {
                infectados++;
            }
        }

        return (infectados * 100.0) / sobreviventes.size();
    }

    public List<Recurso> recursosSobreviventes() {
        List<Sobrevivente> sobreviventes = sobreviventeRepository.findAll();
        List<Recurso> recursos = new ArrayList<>();

        for (Sobrevivente sobrevivente : sobreviventes) {
            if (!sobrevivente.isInfectado()) {
                recursos.addAll(sobrevivente.getInventario().getRecursos());
            }
        }

        if (!recursos.isEmpty()) {
            return recursos;
        } else {
            throw new NoSuchElementException("nenhum recurso encontrado");
        }
    }

    public List<Recurso> recursosInfectados() {
        List<Sobrevivente> sobreviventes = sobreviventeRepository.findAll();
        List<Recurso> recursos = new ArrayList<>();

        for (Sobrevivente sobrevivente : sobreviventes) {
            if (sobrevivente.isInfectado()) {
                recursos.addAll(sobrevivente.getInventario().getRecursos());
            }
        }

        if (!recursos.isEmpty()) {
            return recursos;
        } else {
            throw new NoSuchElementException("nenhum recurso encontrado");
        }
    }
}
