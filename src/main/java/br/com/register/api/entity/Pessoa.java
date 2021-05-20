package br.com.register.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@DiscriminatorValue("P")
@DiscriminatorColumn(name = "tipo", length = 1, discriminatorType = DiscriminatorType.STRING)
public abstract class Pessoa {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    protected String endereco;
    protected String telefone;

    @Column(insertable=false, updatable=false)
    protected String tipo;
}

