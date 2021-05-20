package br.com.register.api.service;

import br.com.register.api.entity.Pessoa;

public interface PessoaService {

    Pessoa persist(Pessoa pessoa);
}
