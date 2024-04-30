package org.example.fullstackguide.aircraft;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.example.fullstackguide.pilot.CrewChief;
import org.example.fullstackguide.pilot.CrewChiefRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AircraftControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    AircraftRepository aircraftRepository;
    @Autowired
    CrewChiefRepository crewChiefRepository;

    @Test
    @Transactional
    void shouldCreateAircraft() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/aircraft")
                                          .contentType(APPLICATION_JSON)
                                          .content("{\"airframe\": \"Aircraft Airframe\", \"pilot\": \"Aircraft Pilot\"}"))
           .andExpect(status().isCreated())
           .andExpect(jsonPath("$.airframe").value("Aircraft Airframe"))
           .andExpect(jsonPath("$.pilot").value("Aircraft Pilot"))
           .andExpect(jsonPath("$.id").exists());
    }

    @Test
    @Transactional
    void shouldReturnAllAircraft() throws Exception {
        aircraftRepository.save(new Aircraft("Airframe 1", "Pilot 1"));
        aircraftRepository.save(new Aircraft("Airframe 2", "Pilot 2"));
        aircraftRepository.save(new Aircraft("Airframe 3", "Pilot 3"));

        mvc.perform(MockMvcRequestBuilders
                .get("/api/aircraft"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(3)))
                .andExpect(jsonPath("$.[0].airframe").value("Airframe 1"))
                .andExpect(jsonPath("$.[0].pilot").value("Pilot 1"));
    }

    @Test
    @Transactional
    void shouldReturnAircraftById() throws Exception {
        aircraftRepository.save(new Aircraft("Airframe 1", "Pilot 1"));
        Aircraft savedAircraft = aircraftRepository.save(new Aircraft("Airframe 2", "Pilot 2"));

        mvc.perform(MockMvcRequestBuilders
                   .get("/api/aircraft/{id}", savedAircraft.getId()))
           .andExpect(status().isOk())
           .andExpect(content().json(new ObjectMapper().writeValueAsString(savedAircraft)));
    }

//    @Test
//    void shouldWorkRelationship () throws Exception {
//        CrewChief crewChief = new CrewChief("Torres");
//        crewChiefRepository.save(crewChief);
//        Aircraft aircraft = new Aircraft("Skyhawk", "Mavrick", crewChief);
//        Aircraft aircraft1 = new Aircraft("Raptor", "Goose", crewChief);
//        aircraftRepository.save(aircraft);
//        aircraftRepository.save(aircraft1);
//
//        List<Aircraft> newAircraft = aircraftRepository.findAll();
//        List<CrewChief> crewChiefs = crewChiefRepository.findAll();
//
//        crewChiefs.get(0).showInfo();
//    }
}
