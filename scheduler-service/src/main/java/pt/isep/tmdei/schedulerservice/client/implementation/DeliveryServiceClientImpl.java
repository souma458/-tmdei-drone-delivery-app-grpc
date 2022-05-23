package pt.isep.tmdei.schedulerservice.client.implementation;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import pt.isep.tmdei.deliverymanagement.CreateDeliveryRequest;
import pt.isep.tmdei.deliverymanagement.CreateDeliveryResponse;
import pt.isep.tmdei.deliverymanagement.DeliveryServiceGrpc;
import pt.isep.tmdei.deliverymanagement.PickupPackageRequest;
import pt.isep.tmdei.deliverymanagement.PickupPackageResponse;
import pt.isep.tmdei.deliverymanagement.UpdateDeliveryDroneRequest;
import pt.isep.tmdei.deliverymanagement.UpdateDeliveryDroneResponse;
import pt.isep.tmdei.deliverymanagement.UpdateDeliveryStatusRequest;
import pt.isep.tmdei.deliverymanagement.UpdateDeliveryStatusResponse;
import pt.isep.tmdei.schedulerservice.client.DeliveryServiceClient;

@Component
@RequiredArgsConstructor
public class DeliveryServiceClientImpl implements DeliveryServiceClient {

    @GrpcClient("delivery-management")
    private DeliveryServiceGrpc.DeliveryServiceBlockingStub deliveryManagementStub;

    @Override
    public CreateDeliveryResponse createDelivery(CreateDeliveryRequest request) {
        return deliveryManagementStub.createDelivery(request);
    }

    @Override
    public UpdateDeliveryStatusResponse updateDeliveryStatus(UpdateDeliveryStatusRequest request) {
        return deliveryManagementStub.updateDeliveryStatus(request);
    }

    @Override
    public UpdateDeliveryDroneResponse updateDeliveryDrone(UpdateDeliveryDroneRequest request) {
        return deliveryManagementStub.updateDeliveryDrone(request);
    }

    @Override
    public PickupPackageResponse pickupPackage(PickupPackageRequest request) {
        return deliveryManagementStub.pickupPackage(request);
    }

}
