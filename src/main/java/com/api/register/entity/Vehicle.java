package com.api.register.entity;

import com.api.register.dto.Brand;

//import org.hibernate.validator.constraints.NotBlank;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.Year;

@Entity
@Getter
@ToString
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    //@NotBlank
    //@Size(max = 100)
    @Column(name = "vehicle")
    private String vehicle;

    //@NotNull
    //@Size(max = 100)
    @Column(name = "brand")
    private Brand brand;

    //@NotNull
    @Column(name = "year")
    private Year year;

    @Column(name = "description")
    private String description;

    @Column(name = "created")
    private LocalDateTime created;

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
}
