package br.com.register.api.entity;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Customer {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TypePerson type;

    @NotNull
    private String cep;

    @NotNull
    private String city;

    @NotNull
    private String address;

    private String addressComplement;

    @NotNull
    @Length(max = 2)
    private String uf;

    @NotNull
    private Integer number;

    private String telephone;

    @Email
    private String email;

    private Long kwh;

    private String tradeRepresentative;

    //private EnergyBill energyBill;

    @CPF
    @Length(max = 14)
    private String cpf;

    private String name;

    @CNPJ
    @Length(max = 18)
    private String cnpj;

    private String companyName;

    @NotNull
    @Column(name = "created")
    private LocalDateTime created;

    @NotNull
    @Column(name = "updated")
    private LocalDateTime updated;

    @Enumerated(EnumType.STRING)
    private RoleStatus status;

    @PrePersist
    private void prePersist() {
        final LocalDateTime now = LocalDateTime.now();
        updated = now;
        created = now;
    }

    @PreUpdate
    private void preUpdate() {
        updated = LocalDateTime.now();
    }
}
