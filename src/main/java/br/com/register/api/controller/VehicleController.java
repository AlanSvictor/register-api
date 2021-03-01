package br.com.register.api.controller;

import br.com.register.api.dto.VehicleDTO;
import br.com.register.api.service.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/vehicles")
@CrossOrigin(origins = "*")
public class VehicleController {

    private static final Logger log = LoggerFactory.getLogger(VehicleController.class);

    @Autowired
    private VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<?> post(@Valid @RequestBody VehicleDTO vehicleDTO) {
        log.info("Post insert vehicleDTO: {}", vehicleDTO);

        return ResponseEntity.ok(VehicleDTO.buildDTO(vehicleService.persist(vehicleDTO.buildToEntity())));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> put(@PathVariable("id") Long id, @Valid @RequestBody VehicleDTO vehicleDTO) {
        log.info("Put update vehicleDTO: {}", vehicleDTO);

        return null;
    }

}
