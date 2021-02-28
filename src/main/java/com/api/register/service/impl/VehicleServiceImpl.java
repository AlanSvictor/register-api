package com.api.register.service.impl;

import com.api.register.entity.Vehicle;
import com.api.register.repository.VehicleRepository;
import com.api.register.service.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
