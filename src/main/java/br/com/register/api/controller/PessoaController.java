package br.com.register.api.controller;

import br.com.register.api.dto.PessoaDTO;
import br.com.register.api.dto.VehicleDTO;
import br.com.register.api.entity.Pessoa;
import br.com.register.api.entity.Vehicle;
import br.com.register.api.service.PessoaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/pessoa")
@CrossOrigin(origins = "*")
public class PessoaController {

    private static final Logger log = LoggerFactory.getLogger(PessoaController.class);

    @Autowired
    private PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody PessoaDTO pessoaDTO, UriComponentsBuilder uriBuilder) {
        log.info("Post insert pessoaDTO: {}", pessoaDTO);

        Pessoa pessoa = pessoaDTO.buildToEntity();
        Pessoa persist = pessoaService.persist(pessoa);

        URI uri = uriBuilder.path("/api/pessoa/{id}").buildAndExpand(pessoa.getId()).toUri();
        return ResponseEntity.created(uri).body(PessoaDTO.buildDTO(pessoa));
    }
}
