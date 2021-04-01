package br.com.register.api.dto;

import br.com.register.api.entity.Vehicle;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class VehicleDTO {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss");

    @JsonIgnore
    private Long id;

    @NotNull
    @NotEmpty
    @Length(min = 2, max = 200)
    private String vehicle;

    @NotNull(message = "Vehicle brand cannot be empty")
    private Brand brand;

    @NotNull(message = "Vehicle year cannot be empty")
    private int year;

    private String description;

    @JsonIgnore
    private String created;

    @JsonIgnore
    private String updated;

    @NotNull
    private boolean isSold;

    public static VehicleDTO buildDTO(Vehicle vehicle) {
        VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.setId(vehicle.getId());
        vehicleDTO.setVehicle(vehicle.getVehicle());
        vehicleDTO.setBrand(vehicle.getBrand());
        vehicleDTO.setYear(vehicle.getYear());
        vehicleDTO.setDescription(vehicleDTO.getDescription());
        vehicleDTO.setCreated(vehicle.getCreated().format(formatter));
        vehicleDTO.setUpdated(vehicle.getUpdated().format(formatter));
        vehicleDTO.setSold(vehicle.isSold());
        return vehicleDTO;
    }

    public Vehicle buildToEntity () {
        return new Vehicle(this.vehicle, this.brand, this.year, this.description, this.isSold);
    }
}
