package br.com.register.api.entity;

import br.com.register.api.dto.Brand;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.YearMonth;

@Entity
@Getter
@ToString
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Column(name = "vehicle")
    private String vehicle;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "brand")
    private Brand brand;

    @NotNull
    @Column(name = "year")
    private int year;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "created")
    private LocalDateTime created;

    @NotNull
    @Column(name = "updated")
    private LocalDateTime updated;

    @PreUpdate
    private void preUpdate() {
        updated = LocalDateTime.now();
    }

    @PrePersist
    private void prePersist() {
       final LocalDateTime now = LocalDateTime.now();
        updated = now;
        created = now;
    }

    public Vehicle (String vehicle, Brand brand, int year, String description) {
        this.vehicle = vehicle;
        this.brand = brand;
        this.year = year;
        this.description = description;
    }
}
