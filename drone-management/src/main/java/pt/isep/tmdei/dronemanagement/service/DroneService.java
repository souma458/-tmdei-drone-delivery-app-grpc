package pt.isep.tmdei.dronemanagement.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pt.isep.tmdei.dronemanagement.BookDroneResponse;
import pt.isep.tmdei.dronemanagement.IdleDroneResponse;
import pt.isep.tmdei.dronemanagement.model.DroneStatus;
import pt.isep.tmdei.dronemanagement.repository.DroneRepository;
import pt.isep.tmdei.dronemanagement.repository.ModelRepository;
import pt.isep.tmdei.dronemanagement.service.exception.DroneNotFoundException;
import pt.isep.tmdei.dronemanagement.service.exception.NoDroneAvailableException;

@Service
@RequiredArgsConstructor
public class DroneService {

    private final DroneRepository droneRepository;
    private final ModelRepository modelRepository;

    public BookDroneResponse bookAvailableDrone() {
        var drones = droneRepository.findByStatus(DroneStatus.IDLE);
        if (drones.isEmpty()) {
            throw new NoDroneAvailableException("There are no drones available for package delivery");
        }

        drones.get(0).setStatus(DroneStatus.ACTIVE);
        droneRepository.save(drones.get(0));
        var model = modelRepository.findById(drones.get(0).getModel()).get();

        var response = BookDroneResponse.newBuilder().setDrone(drones.get(0).getId()).setModel(model.getName()).build();

        return response;
    }

    public IdleDroneResponse idleDrone(Long droneId) {
        var drone = droneRepository.findById(droneId).orElseThrow(() -> new DroneNotFoundException(droneId));
        drone.setStatus(DroneStatus.IDLE);
        droneRepository.save(drone);
        return IdleDroneResponse.newBuilder().build();
    }

}
