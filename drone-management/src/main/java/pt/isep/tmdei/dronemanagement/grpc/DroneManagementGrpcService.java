package pt.isep.tmdei.dronemanagement.grpc;

import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import pt.isep.tmdei.dronemanagement.BookDroneRequest;
import pt.isep.tmdei.dronemanagement.BookDroneResponse;
import pt.isep.tmdei.dronemanagement.DroneServiceGrpc;
import pt.isep.tmdei.dronemanagement.IdleDroneRequest;
import pt.isep.tmdei.dronemanagement.IdleDroneResponse;
import pt.isep.tmdei.dronemanagement.service.DroneService;

@GrpcService
@RequiredArgsConstructor
public class DroneManagementGrpcService extends DroneServiceGrpc.DroneServiceImplBase {

    private final DroneService service;

    @Override
    public void bookDrone(BookDroneRequest request, io.grpc.stub.StreamObserver<BookDroneResponse> responseObserver) {
        responseObserver.onNext(service.bookAvailableDrone());
        responseObserver.onCompleted();
    }

    @Override
    public void idleDrone(IdleDroneRequest request, io.grpc.stub.StreamObserver<IdleDroneResponse> responseObserver) {
        responseObserver.onNext(service.idleDrone(request.getDrone()));
        responseObserver.onCompleted();
    }

}