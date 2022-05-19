package pt.isep.tmdei.schedulerservice.grpc;

import org.springframework.beans.factory.annotation.Autowired;

import net.devh.boot.grpc.client.inject.GrpcClient;
import net.devh.boot.grpc.server.service.GrpcService;
import pt.isep.tmdei.dronemanagement.DeliveryServiceGrpc;
import pt.isep.tmdei.schedulerservice.CreateDeliveryRequest;
import pt.isep.tmdei.schedulerservice.CreateDeliveryResponse;
import pt.isep.tmdei.schedulerservice.SchedulerServiceGrpc;
import pt.isep.tmdei.schedulerservice.model.mapper.DeliveryMapper;

@GrpcService
public class SchedulerService extends SchedulerServiceGrpc.SchedulerServiceImplBase {

    @Autowired
    private DeliveryMapper deliveryMapper;

    @GrpcClient("delivery-management")
    private DeliveryServiceGrpc.DeliveryServiceBlockingStub deliveryManagementStub;

    @Override
    public void scheduleDelivery(CreateDeliveryRequest request,
            io.grpc.stub.StreamObserver<CreateDeliveryResponse> responseObserver) {

        var delivery = deliveryManagementStub.createDelivery(deliveryMapper.mapCreateDeliveryRequest(request));

        CreateDeliveryResponse response = CreateDeliveryResponse.newBuilder().setDelivery(delivery.getDelivery())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }

}
