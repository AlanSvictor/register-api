package br.com.register.api.service;

import br.com.register.api.entity.Vehicle;

import java.util.Optional;

public interface VehicleService {

    Vehicle persist (Vehicle vehicle);

    Optional<Vehicle> findById(Long id);

    void delete(Vehicle vehicle);
}
