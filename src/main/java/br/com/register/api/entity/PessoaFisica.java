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
@DiscriminatorValue(value = "F")
public class PessoaFisica extends Pessoa {

    private String cpf;
    private Integer idade;
    private String sexo;

    public PessoaFisica(String cpf, Integer idade, String sexo, String endereco, String telefone, String tipo) {
        this.cpf = cpf;
        this.idade = idade;
        this.sexo = sexo;
        this.endereco = endereco;
        this.telefone = telefone;
        this.tipo = tipo;
    }
}
