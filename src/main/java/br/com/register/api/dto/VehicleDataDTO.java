package br.com.register.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class VehicleDataDTO {

    private int unsoldVehicles;
    private Map<Integer, Long> vehiclesPerDecade;
    private Map<String, Long> vehicleByManufacturer;
    private List<VehicleDTO> vehiclesRegisteredLastWeek;
}
