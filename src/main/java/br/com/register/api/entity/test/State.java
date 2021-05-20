package br.com.register.api.entity.test;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "StateSequence")
    @SequenceGenerator(name = "StateSequence", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Length(max = 2)
    @Column(nullable = false)
    private String uf;

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
