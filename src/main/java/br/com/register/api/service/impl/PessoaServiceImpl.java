package br.com.register.api.service.impl;

import br.com.register.api.entity.Pessoa;
import br.com.register.api.repository.PessoaRepository;
import br.com.register.api.service.PessoaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaServiceImpl implements PessoaService {

    private static final Logger log = LoggerFactory.getLogger(PessoaServiceImpl.class);

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public Pessoa persist(Pessoa pessoa) {
        log.info("Persisting pessoa: {}", pessoa);
        return pessoaRepository.save(pessoa);
    }
}
