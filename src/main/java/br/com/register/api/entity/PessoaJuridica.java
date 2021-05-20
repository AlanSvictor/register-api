package br.com.register.api.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@ToString
@DiscriminatorValue(value = "J")
public class PessoaJuridica extends Pessoa {

    private String cnpj;
    private String razaoSocial;
    private String inscricaoEstadual;

    public PessoaJuridica (String cnpj, String razaoSocial, String inscricaoEstadual, String endereco, String telefone, String tipo) {
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.inscricaoEstadual = inscricaoEstadual;
        this.endereco = endereco;
        this.telefone = telefone;
        this.tipo = tipo;
    }
}
