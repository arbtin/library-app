package org.example.fullstackguide;

import org.example.fullstackguide.aircraft.Aircraft;
import org.example.fullstackguide.aircraft.AircraftRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class FullStackGuideApplicationTests {

    @Autowired
    AircraftRepository aircraftRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void confirmRepositorySetupIsCorrect() {
        // This test can be deleted, but you can use it to confirm everything is working
        // correctly so far without needing to do a ./gradlew bR over and over
        List<Aircraft> all = aircraftRepository.findAll();
        assertThat(all).hasSize(0);
    }
}
