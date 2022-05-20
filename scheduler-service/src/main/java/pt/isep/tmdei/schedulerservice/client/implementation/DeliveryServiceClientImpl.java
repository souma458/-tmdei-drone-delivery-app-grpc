package pt.isep.tmdei.schedulerservice.client.implementation;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import pt.isep.tmdei.deliverymanagement.CreateDeliveryResponse;
import pt.isep.tmdei.deliverymanagement.DeliveryServiceGrpc;
import pt.isep.tmdei.deliverymanagement.UpdateDeliveryDroneRequest;
import pt.isep.tmdei.deliverymanagement.UpdateDeliveryDroneResponse;
import pt.isep.tmdei.deliverymanagement.UpdateDeliveryStatusRequest;
import pt.isep.tmdei.deliverymanagement.UpdateDeliveryStatusResponse;
import pt.isep.tmdei.schedulerservice.CreateDeliveryRequest;
import pt.isep.tmdei.schedulerservice.client.DeliveryServiceClient;
import pt.isep.tmdei.schedulerservice.model.mapper.DeliveryMapper;

@Component
@RequiredArgsConstructor
public class DeliveryServiceClientImpl implements DeliveryServiceClient {

    @GrpcClient("delivery-management")
    private DeliveryServiceGrpc.DeliveryServiceBlockingStub deliveryManagementStub;

    private final DeliveryMapper deliveryMapper;

    @Override
    public CreateDeliveryResponse createDelivery(CreateDeliveryRequest request) {
        return deliveryManagementStub.createDelivery(deliveryMapper.mapCreateDeliveryRequest(request));
    }

    @Override
    public UpdateDeliveryStatusResponse updateDeliveryStatus(UpdateDeliveryStatusRequest request) {
        return deliveryManagementStub.updateDeliveryStatus(request);
    }

    @Override
    public UpdateDeliveryDroneResponse updateDeliveryDrone(UpdateDeliveryDroneRequest request) {
        return deliveryManagementStub.updateDeliveryDrone(request);
    }

}
