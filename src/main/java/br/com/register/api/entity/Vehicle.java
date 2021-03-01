package br.com.register.api.entity;

import br.com.register.api.dto.Brand;

import br.com.register.api.dto.VehicleDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;

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
@NoArgsConstructor
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

    @NotNull
    @Column(name = "sold")
    @Value("false")
    private boolean isSold;

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

    public Vehicle (String vehicle, Brand brand, int year, String description, boolean isSold) {
        this.vehicle = vehicle;
        this.brand = brand;
        this.year = year;
        this.description = description;
        this.isSold = isSold;
    }

    public void update (VehicleDTO vehicleDTO){
        this.vehicle = vehicleDTO.getVehicle();
        this.description = vehicleDTO.getDescription();
        this.year = vehicleDTO.getYear();
        this.brand = vehicleDTO.getBrand();

    }
}
