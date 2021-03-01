package br.com.register.api.service.impl;

import br.com.register.api.dto.Brand;
import br.com.register.api.entity.Vehicle;
import br.com.register.api.exception.BrandNotFoundException;
import br.com.register.api.repository.VehicleRepository;
import br.com.register.api.service.VehicleService;
import org.apache.commons.lang3.EnumUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class VehicleServiceImpl implements VehicleService {

    private static final Logger log = LoggerFactory.getLogger(VehicleServiceImpl.class);

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public Vehicle persist(Vehicle vehicle) {
        log.info("Persisting vehicle: {}", vehicle);

        EnumUtils.isValidEnum(Brand.class, vehicle.getBrand().name());

        if (EnumUtils.isValidEnum(Brand.class, vehicle.getBrand().name())) {
            try {
                throw new BrandNotFoundException(vehicle.getBrand().name());
            } catch (BrandNotFoundException e) {
                e.printStackTrace();
            }
        }

        return vehicleRepository.save(vehicle);
    }
}
