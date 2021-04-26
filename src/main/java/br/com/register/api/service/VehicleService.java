package br.com.register.api.service;

import br.com.register.api.dto.VehicleDTO;
import br.com.register.api.dto.VehicleDataDTO;
import br.com.register.api.entity.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface VehicleService {

    Vehicle persist (Vehicle vehicle);

    Optional<Vehicle> findById(Long id);

    void delete(Vehicle vehicle);

    List<Vehicle> findByFilter(String filter);

    VehicleDataDTO populateVehicleData();

    Optional<Page<VehicleDTO>> findAll(Pageable pageable);

    List<Vehicle> findAll();

}
