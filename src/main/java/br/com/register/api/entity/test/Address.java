package br.com.register.api.entity.test;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AddressSequence")
    @SequenceGenerator(name = "AddressSequence", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String zipCode;

    @Column(nullable = false)
    private String street;

    private String number;

    private String complement;

    private String neighborhood;

    @OneToOne
    private AddressType addressType;

    @OneToOne
    private City city;

    @OneToOne
    private State state;

    @NotNull
    private LocalDateTime created;

    @NotNull
    private LocalDateTime updated;

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
