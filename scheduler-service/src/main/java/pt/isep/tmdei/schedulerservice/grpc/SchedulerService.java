package pt.isep.tmdei.schedulerservice.grpc;

import net.devh.boot.grpc.server.service.GrpcService;
import pt.isep.tmdei.schedulerservice.CreateDeliveryRequest;
import pt.isep.tmdei.schedulerservice.CreateDeliveryResponse;
import pt.isep.tmdei.schedulerservice.SchedulerServiceGrpc;

@GrpcService
public class SchedulerService extends SchedulerServiceGrpc.SchedulerServiceImplBase {

    @Override
    public void scheduleDelivery(CreateDeliveryRequest request,
            io.grpc.stub.StreamObserver<CreateDeliveryResponse> responseObserver) {
        // TODO: Implement schedule delivery logic

        CreateDeliveryResponse response = CreateDeliveryResponse.newBuilder().setDelivery("12345").build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }

}
