package br.com.register.api.repository;

import br.com.register.api.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    @Override
    Optional<Vehicle> findById(Long id);

}
