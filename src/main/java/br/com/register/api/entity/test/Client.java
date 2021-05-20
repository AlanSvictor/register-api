package br.com.register.api.entity.test;

import br.com.register.api.entity.RoleStatus;
import br.com.register.api.entity.TypePerson;
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
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ClientSequence")
	@SequenceGenerator(name = "ClientSequence", allocationSize = 1)
	private Long id;

	@NotNull
	@Enumerated(EnumType.STRING)
	private TypePerson typePerson;

	private String fullName;

	@CPF
	@Length(max = 14)
	private String cpf;

	@CNPJ
	@Length(max = 18)
	private String cnpj;

	private String companyName;

	private String telephone;

	@Email
	private String email;

	private Long kwh;

	private String tradeRepresentative;

	@ManyToOne
	private Address address;

	@NotNull
	private LocalDateTime created;

	@NotNull
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
