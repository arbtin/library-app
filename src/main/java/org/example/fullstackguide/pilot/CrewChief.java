package org.example.fullstackguide.pilot;

import jakarta.persistence.*;
import org.example.fullstackguide.aircraft.Aircraft;
import java.util.List;

@Entity
public class CrewChief {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String last_name;

    @OneToMany(mappedBy = "crew_chief_id", fetch = FetchType.EAGER)
    private List<Aircraft> aircraft_id;

    public CrewChief() {

    }

    public CrewChief(String lastName) {
        this.last_name = lastName;
    }

    public CrewChief(String lastName, List<Aircraft> aircraft) {
        this.last_name = lastName;
        this.aircraft_id = aircraft;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public List<Aircraft> getAircraft() {
        return aircraft_id;
    }

    public void setAircraft(List<Aircraft> aircraft) {
        this.aircraft_id = aircraft;
    }

    public void showInfo() {
        System.out.println("\n\n\n\nCrew Chief: " + last_name);
        System.out.println("Airframes:");
        for (Aircraft aircraft : aircraft_id) {
            System.out.println("    " + aircraft.getAirframe());
            System.out.println("    " + aircraft.getPilot());
        }
        System.out.println("\n\n\n\n");
    }
}
