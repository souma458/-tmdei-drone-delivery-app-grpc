package pt.isep.tmdei.schedulerservice.grpc;

import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import pt.isep.tmdei.schedulerservice.CancelDeliveryRequest;
import pt.isep.tmdei.schedulerservice.CancelDeliveryResponse;
import pt.isep.tmdei.schedulerservice.CompleteDeliveryRequest;
import pt.isep.tmdei.schedulerservice.CompleteDeliveryResponse;
import pt.isep.tmdei.schedulerservice.CreateDeliveryRequest;
import pt.isep.tmdei.schedulerservice.CreateDeliveryResponse;
import pt.isep.tmdei.schedulerservice.GetDeliveryEtaRequest;
import pt.isep.tmdei.schedulerservice.GetDeliveryEtaResponse;
import pt.isep.tmdei.schedulerservice.PickupPackageRequest;
import pt.isep.tmdei.schedulerservice.PickupPackageResponse;
import pt.isep.tmdei.schedulerservice.SchedulerServiceGrpc;
import pt.isep.tmdei.schedulerservice.service.SchedulerService;

@GrpcService
@RequiredArgsConstructor
public class SchedulerGrpcService extends SchedulerServiceGrpc.SchedulerServiceImplBase {

    private final SchedulerService schedulerService;

    @Override
    public void scheduleDelivery(CreateDeliveryRequest request,
            io.grpc.stub.StreamObserver<CreateDeliveryResponse> responseObserver) {
        responseObserver.onNext(schedulerService.scheduleDelivery(request));
        responseObserver.onCompleted();
    }

    @Override
    public void pickupPackage(PickupPackageRequest request,
            io.grpc.stub.StreamObserver<PickupPackageResponse> responseObserver) {
        responseObserver.onNext(schedulerService.pickupPackage(request));
        responseObserver.onCompleted();
    }

    @Override
    public void completeDelivery(CompleteDeliveryRequest request,
            io.grpc.stub.StreamObserver<CompleteDeliveryResponse> responseObserver) {
        responseObserver.onNext(schedulerService.completeDelivery(request));
        responseObserver.onCompleted();
    }

    @Override
    public void cancelDelivery(CancelDeliveryRequest request,
            io.grpc.stub.StreamObserver<CancelDeliveryResponse> responseObserver) {
        responseObserver.onNext(schedulerService.cancelDelivery(request));
        responseObserver.onCompleted();
    }

    @Override
    public void getDeliveryEta(GetDeliveryEtaRequest request,
            io.grpc.stub.StreamObserver<GetDeliveryEtaResponse> responseObserver) {
        responseObserver.onNext(schedulerService.getDeliveryEta(request));
        responseObserver.onCompleted();
    }

}
