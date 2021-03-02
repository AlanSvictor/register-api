package br.com.register.api.controller;

import br.com.register.api.RegisterApplication;
import br.com.register.api.dto.Brand;
import br.com.register.api.dto.VehicleDTO;
import br.com.register.api.entity.Vehicle;
import br.com.register.api.service.VehicleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = RegisterApplication.class)
@SpringBootTest
@ActiveProfiles("test")
public class VehicleControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mvc;

    @Autowired
    private VehicleService vehicleService;

    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void validatePost() throws Exception {
        VehicleDTO vehicleDTO = getFakeVehicleDTO();

        mvc.perform(MockMvcRequestBuilders.post("/api/vehicles")
                .content(asJsonString(vehicleDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        Optional<Vehicle> vehicle = vehicleService.findById(1L);
        Assert.assertTrue(vehicle.isPresent());
    }

    @Test
    public void validateGet() throws Exception {

        VehicleDTO vehicleDTO = getFakeVehicleDTO();
        Vehicle vehicle = vehicleService.persist(vehicleDTO.buildToEntity());

        mvc.perform(MockMvcRequestBuilders.get("/api/vehicles/" + vehicle.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.vehicle").value(vehicleDTO.getVehicle()));

    }

    @Test
    public void validatePut() throws Exception {

        VehicleDTO vehicleDTO = getFakeVehicleDTO();
        vehicleService.persist(vehicleDTO.buildToEntity());

        mvc.perform(MockMvcRequestBuilders.put("/api/vehicles/"+ 1)
                .content(asJsonString(vehicleDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.vehicle").value(vehicleDTO.getVehicle()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(vehicleDTO.getDescription()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brand").value(vehicleDTO.getBrand().name()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.year").value(vehicleDTO.getYear()));
    }


    @Test
    public void validateDelete() throws Exception
    {
        VehicleDTO vehicleDTO = getFakeVehicleDTO();
        Vehicle vehicle = vehicleService.persist(vehicleDTO.buildToEntity());


        mvc.perform(MockMvcRequestBuilders.delete("/api/vehicles/"+ vehicle.getId()))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private VehicleDTO getFakeVehicleDTO() {
        VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.setVehicle("S10");
        vehicleDTO.setSold(false);
        vehicleDTO.setBrand(Brand.CHEVROLET);
        vehicleDTO.setDescription("test");
        vehicleDTO.setYear(2021);
        return vehicleDTO;
    }

}
