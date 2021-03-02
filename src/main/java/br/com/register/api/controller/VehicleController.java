package br.com.register.api.controller;

import br.com.register.api.dto.VehicleDTO;
import br.com.register.api.dto.VehicleDataDTO;
import br.com.register.api.entity.Vehicle;
import br.com.register.api.service.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehicles")
@CrossOrigin(origins = "*")
public class VehicleController {

    private static final Logger log = LoggerFactory.getLogger(VehicleController.class);

    @Autowired
    private VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody VehicleDTO vehicleDTO) {
        log.info("Post insert vehicleDTO: {}", vehicleDTO);

        return ResponseEntity.ok(VehicleDTO.buildDTO(vehicleService.persist(vehicleDTO.buildToEntity())));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @Valid @RequestBody VehicleDTO vehicleDTO) {
        log.info("Put update vehicleDTO: {}", vehicleDTO);
        Optional<Vehicle> vehicle = vehicleService.findById(id);
        if (!vehicle.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found vehicle for id: " + id);
        }
        vehicle.get().update(vehicleDTO);
        return ResponseEntity.ok(vehicleService.persist(vehicle.get()));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") long id) {
        log.info("Get searching vehicle by id: {}", id);

        Optional<Vehicle> vehicle = vehicleService.findById(id);
        if (!vehicle.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found vehicle for id: " + id);
        }
        return ResponseEntity.ok(VehicleDTO.buildDTO(vehicle.get()));
    }

    @GetMapping(value = "/filter")
    public ResponseEntity<List<?>> findByFilter(@RequestParam("filter") String filter) {
        log.info("Get searching vehicle by filter: {}", filter);
        List<Vehicle> vehicles = vehicleService.findByFilter(filter);
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping(value = "/vehicles")
    public ResponseEntity<?> vehicleData() {
        VehicleDataDTO vehicleDataDTO = vehicleService.populateVehicleData();
        return ResponseEntity.ok(vehicleDataDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete (@PathVariable("id") Long id){
        log.info("Get delete vehicle by id: {}", id);

        Optional<Vehicle> vehicle = vehicleService.findById(id);
        if (!vehicle.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found vehicle for id: " + id);
        }
        vehicleService.delete(vehicle.get());
        return ResponseEntity.ok().build();
    }

}
