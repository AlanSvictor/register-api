package br.com.register.api.resolver;

import br.com.register.api.entity.Vehicle;
import br.com.register.api.service.VehicleService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VehicleResolver implements GraphQLQueryResolver, GraphQLMutationResolver {

    private static final Logger log = LoggerFactory.getLogger(VehicleResolver.class);
    public static final int page = 0;
    public static final int totalPages = 10;

    @Autowired
    private VehicleService vehicleService;

    public List<Vehicle> findAllVehicle() {
        return new ArrayList<>(vehicleService.findAll());
    }

}
