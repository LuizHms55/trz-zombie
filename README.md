# TRZ (The Resident Zombie)🧟

Você desenvolverá uma API REST que guardará as informações sobre os sobreviventes, assim como seus recursos.

Para completar esta tarefa, seu sistema deverá seguir os seguintes requisitos:

<ul>
  <li>Adicionar sobreviventes ao banco de dados
  <li>Um sobrevivente deve ter nome, idade, género e última localização (latitude e longitude).
  <li>Cada sobrevivente terá seu próprio inventário, com recursos e itens. O sobrevivente deverá declarar seus recursos no momento de cadastro.
  <li>Sobreviventes podem adicionar ou remover itens do inventário.
  <li>O sobrevivente deve ter a opção de atualizar sua última localização, guardando os novos dados de latitude e longitude.
  <li>Denunciar um sobrevivente como infectado.
  <li>Em uma situação caótica como essa, um sobrevivente pode inevitavelmente ser contaminado pelo vírus. Quando isso acontecer devemos registrá-lo como infectado. Um sobrevivente infectado nao poderá realizar atualizar seu inventário e também não será listado no relatório de sobreviventes.
  <li>Um sobrevivente será definido como infectado, quando pelo menos outros 5 sobreviventes denunciá-lo como infectado.
  <li>Um novo usuário deverá registrar seus pertences durante seu cadastro. E após isso, ele poderá alterar os itens em seu inventário de acordo com novos recursos encontrados.
</ul>

## Relatórios
A API deverá fornecer os seguintes relatórios:

- Percentual de sobreviventes infectados.
- Percentual de sobreviventes não infectados.
- O total de cada recurso encontrado por cada sobrevivente.
- Recursos perdidos por estar em posse de sobreviventes infectados.


## Requisições
### Sobrevivente Controller

Endpoint "/sobreviventes"

Traz todos os Sobreviventes
```
    @GetMapping
    public List<Sobrevivente> buscarTodos() {
        return sobreviventeService.buscarTodos();
    }
```

Cadastra um Sobrevivente
```
    @PostMapping
    public ResponseEntity<SobreviventeDTO> cadastrar(@RequestBody @Valid SobreviventeDTO sobreviventeDTO){
        return sobreviventeService.cadastrar(sobreviventeDTO);
    }
```

Atualiza a Localização
```

    @PutMapping("/localizacao")
    public ResponseEntity<SobreviventeDTO> atualizarLocal(@RequestBody @Valid AtualizarLocalizacaoDTO localizacaoDTO) throws NoSuchMethodException {
        return sobreviventeService.atualizarLocal(localizacaoDTO);
    }
```

Atualiza o Inventário
```

    @PutMapping("/inventario")
    public ResponseEntity<SobreviventeDTO> atualizarInventario(@RequestBody @Valid AtualizarInventarioDTO inventarioDTO) throws NoSuchMethodException {
        return sobreviventeService.atualizarInventario(inventarioDTO);
    }
```

Indica um Infectado
```

    @PutMapping("/infectado/{id}/{id2}")
    public ResponseEntity<SobreviventeDTO> indicarInfectado(@PathVariable String id, @PathVariable String id2) throws NoSuchMethodException {
        return sobreviventeService.indicarInfectado(id, id2);
    }
```

### Estatisticas Controller

Endpoint "/estatisticas"

Retorna a taxa de infectados
```
    @GetMapping("/infectados")
    public double taxaInfectados() {
        return sobreviventeService.taxaInfectados();
    }

```

Retorna a taxa de sobreviventes
```

    @GetMapping("/ninfectados")
    public double taxaSobreviventes() {
        return sobreviventeService.taxaSobreviventes();
    }
```

Retorna os recursos dos sobreviventes
```

    @GetMapping("/recursos/sobreviventes")
    public List<Recurso> recursosSobreviventes() {
        return sobreviventeService.recursosSobreviventes();
    }
```

Retorna os recursos dos infectados
```

    @GetMapping("/recursos/infectados")
    public List<Recurso> recursosInfectados() {
        return sobreviventeService.recursosInfectados();
    }
```



