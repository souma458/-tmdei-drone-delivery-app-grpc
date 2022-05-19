package pt.isep.tmdei.dronemanagement.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import pt.isep.tmdei.dronemanagement.model.Drone;
import pt.isep.tmdei.dronemanagement.model.DroneStatus;

public interface DroneRepository extends CrudRepository<Drone, Long> {

    List<Drone> findByStatus(final DroneStatus status);

}
