package org.example.fullstackguide.aircraft;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aircraft")
public class AircraftController {

    private final AircraftRepository aircraftRepository;

    public AircraftController(AircraftRepository aircraftRepository) {
        this.aircraftRepository = aircraftRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Aircraft createAircraft(@RequestBody Aircraft aircraft) {
        return aircraftRepository.save(aircraft);
    }

    @GetMapping
    public List<Aircraft> getAllAircraft() {
        return aircraftRepository.findAll();
    }

    @GetMapping("/{id}")
    public Aircraft getAircraftById(@PathVariable Long id) {
        return aircraftRepository.findById(id).orElseThrow();
    }
}
