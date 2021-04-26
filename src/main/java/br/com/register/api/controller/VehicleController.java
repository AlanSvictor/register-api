package br.com.register.api.controller;

import br.com.register.api.dto.VehicleDTO;
import br.com.register.api.dto.VehicleDataDTO;
import br.com.register.api.entity.Vehicle;
import br.com.register.api.service.VehicleService;
import ch.qos.logback.core.boolex.EvaluationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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
    public ResponseEntity<?> save(@Valid @RequestBody VehicleDTO vehicleDTO, UriComponentsBuilder uriBuilder) {
        log.info("Post insert vehicleDTO: {}", vehicleDTO);

        Vehicle vehicle = vehicleDTO.buildToEntity();
        vehicleService.persist(vehicle);

        URI uri = uriBuilder.path("/api/vehicles/{id}").buildAndExpand(vehicle.getId()).toUri();
        return ResponseEntity.created(uri).body(VehicleDTO.buildDTO(vehicle));
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

    @GetMapping
    public ResponseEntity<Optional<Page<VehicleDTO>>> findAll(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                                              @RequestParam(value = "totalPages", defaultValue = "10", required = false) int totalPages,
                                                              @RequestParam(value = "order", defaultValue = "sold", required = false) String order,
                                                              @RequestParam(value = "sort", defaultValue = "ASC", required = false) String sort) {
        log.info("Get all vehicle");

        Pageable pageable = PageRequest.of(page, totalPages, Sort.Direction.ASC, order);

        Optional<Page<VehicleDTO>> vehicleDTOs = vehicleService.findAll(pageable);
        return ResponseEntity.ok(vehicleDTOs);
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
