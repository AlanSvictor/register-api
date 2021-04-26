package br.com.register.api.service.impl;

import br.com.register.api.dto.VehicleDTO;
import br.com.register.api.dto.VehicleDataDTO;
import br.com.register.api.entity.Vehicle;
import br.com.register.api.repository.VehicleRepository;
import br.com.register.api.service.VehicleService;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;

@Service
public class VehicleServiceImpl implements VehicleService {

    private static final Logger log = LoggerFactory.getLogger(VehicleServiceImpl.class);

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public Vehicle persist(Vehicle vehicle) {
        log.info("Persisting vehicle: {}", vehicle);
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Optional<Vehicle> findById(Long id) {
        return vehicleRepository.findById(id);
    }

    @Override
    public void delete(Vehicle vehicle) {
        vehicleRepository.delete(vehicle);
    }

    public List<Vehicle> findByFilter(String filter) {

        if (Strings.isNullOrEmpty(filter)) {
            return emptyList();
        }
        return vehicleRepository.findAllByVehicleContainingIgnoreCase(filter);
    }

    @Override
    public VehicleDataDTO populateVehicleData() {

        VehicleDataDTO vehicleDataDTO = new VehicleDataDTO();
        vehicleDataDTO.setUnsoldVehicles(vehicleRepository.countVehicleBySoldIsFalse());

        final List<Vehicle> vehicles = vehicleRepository.findAll();
        Map<Integer, Long> countingByDecade = vehicles.stream().collect(Collectors.groupingBy(Vehicle::getDecade, Collectors.counting()));
        vehicleDataDTO.setVehiclesPerDecade(countingByDecade);
        Map<String, Long> countingByBrand = vehicles.stream().collect(Collectors.groupingBy(Vehicle::getBrandName, Collectors.counting()));
        vehicleDataDTO.setVehicleByManufacturer(countingByBrand);


        LocalDateTime now = LocalDateTime.now();
        LocalDateTime previousWeekDay = LocalDateTime.now().minus(1, ChronoUnit.WEEKS);
        List<VehicleDTO> vehiclesRegistered = vehicles.stream()
                .filter(v -> v.getCreated().isAfter(previousWeekDay) && v.getCreated().isBefore(now))
                .map(VehicleDTO::buildDTO)
                .collect(Collectors.toList());

        vehicleDataDTO.setVehiclesRegisteredLastWeek(vehiclesRegistered);

        return vehicleDataDTO;
    }

    @Override
    public Optional<Page<VehicleDTO>> findAll(Pageable pageable) {
        Page<Vehicle> all = vehicleRepository.findAll(pageable);
        return Optional.of(all.map(VehicleDTO::buildDTO));
    }

    @Override
    public List<Vehicle> findAll() {
        return new ArrayList<>(vehicleRepository.findAll());
    }
}
