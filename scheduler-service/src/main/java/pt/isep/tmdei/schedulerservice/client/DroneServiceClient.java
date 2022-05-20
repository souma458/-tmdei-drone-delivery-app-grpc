package pt.isep.tmdei.schedulerservice.client;

import pt.isep.tmdei.dronemanagement.BookDroneRequest;
import pt.isep.tmdei.dronemanagement.BookDroneResponse;

public interface DroneServiceClient {

    BookDroneResponse bookDrone(BookDroneRequest request);

}
