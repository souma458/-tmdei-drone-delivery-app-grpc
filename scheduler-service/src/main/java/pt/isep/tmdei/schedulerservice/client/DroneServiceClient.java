package pt.isep.tmdei.schedulerservice.client;

import pt.isep.tmdei.dronemanagement.BookDroneRequest;
import pt.isep.tmdei.dronemanagement.BookDroneResponse;
import pt.isep.tmdei.dronemanagement.IdleDroneRequest;
import pt.isep.tmdei.dronemanagement.IdleDroneResponse;

public interface DroneServiceClient {

    BookDroneResponse bookDrone(BookDroneRequest request);

    IdleDroneResponse idleDrone(IdleDroneRequest request);

}
