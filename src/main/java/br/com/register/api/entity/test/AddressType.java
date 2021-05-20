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
public class AddressType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AddressTypeSequence")
    @SequenceGenerator(name = "AddressTypeSequence", allocationSize = 1)
    private Long id;

    @NotNull
    private String description;

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
