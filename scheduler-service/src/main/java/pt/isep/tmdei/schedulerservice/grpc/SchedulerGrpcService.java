package pt.isep.tmdei.schedulerservice.grpc;

import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import pt.isep.tmdei.schedulerservice.CreateDeliveryRequest;
import pt.isep.tmdei.schedulerservice.CreateDeliveryResponse;
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

}
