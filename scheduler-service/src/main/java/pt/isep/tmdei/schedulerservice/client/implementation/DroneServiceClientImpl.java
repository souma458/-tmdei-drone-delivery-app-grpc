package pt.isep.tmdei.schedulerservice.client.implementation;

import org.springframework.stereotype.Component;

import net.devh.boot.grpc.client.inject.GrpcClient;
import pt.isep.tmdei.dronemanagement.BookDroneRequest;
import pt.isep.tmdei.dronemanagement.BookDroneResponse;
import pt.isep.tmdei.dronemanagement.DroneServiceGrpc;
import pt.isep.tmdei.schedulerservice.client.DroneServiceClient;

@Component
public class DroneServiceClientImpl implements DroneServiceClient {

    @GrpcClient("drone-management")
    private DroneServiceGrpc.DroneServiceBlockingStub droneManagementStub;

    @Override
    public BookDroneResponse bookDrone(BookDroneRequest request) {
        return droneManagementStub.bookDrone(request);
    }

}
