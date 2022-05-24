package pt.isep.tmdei.schedulerservice.service;

import java.util.Random;

import org.springframework.stereotype.Service;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import lombok.RequiredArgsConstructor;
import pt.isep.tmdei.deliverymanagement.GetDeliveryResponse;
import pt.isep.tmdei.deliverymanagement.UpdateDeliveryDroneRequest;
import pt.isep.tmdei.deliverymanagement.UpdateDeliveryStatusRequest;
import pt.isep.tmdei.dronemanagement.BookDroneRequest;
import pt.isep.tmdei.schedulerservice.GetDeliveryEtaRequest;
import pt.isep.tmdei.schedulerservice.client.DeliveryServiceClient;
import pt.isep.tmdei.schedulerservice.client.DroneServiceClient;
import pt.isep.tmdei.schedulerservice.client.ThirdPartyTransportationServiceClient;
import pt.isep.tmdei.schedulerservice.model.data.TransportationDTO;
import pt.isep.tmdei.transportationservice.CreateTransportationRequest;

@Service
@RequiredArgsConstructor
public class TransportationService {

    private final DroneServiceClient droneServiceClient;
    private final ThirdPartyTransportationServiceClient thirdPartyTransportationServiceClient;
    private final DeliveryServiceClient deliveryServiceClient;

    private static final int LOW_ESTIMATE = 120;
    private static final int HIGH_ESTIMATE = 1440;

    public TransportationDTO scheduleDeliveryTransport(final String deliveryId) {
        var transportation = new TransportationDTO();

        try {
            var bookDroneResponse = droneServiceClient.bookDrone(BookDroneRequest.newBuilder().build());
            transportation.setDroneId(bookDroneResponse.getDrone());
            deliveryServiceClient.updateDeliveryDrone(UpdateDeliveryDroneRequest.newBuilder().setDelivery(deliveryId)
                    .setDrone(bookDroneResponse.getDrone()).build());
        } catch (StatusRuntimeException ex) {
            if (ex.getStatus() == Status.NOT_FOUND) {
                var createTransportationRequestResponse = thirdPartyTransportationServiceClient
                        .createTransportationRequest(
                                CreateTransportationRequest.newBuilder().setDelivery(deliveryId).build());
                transportation.setTransportationRequestId(createTransportationRequestResponse.getRequestId());
            } else {
                throw ex;
            }
        }

        return transportation;
    }

    public int calculateEta(GetDeliveryResponse getDeliveryResponse) {
        // random value is returned to simulate that an ETA is calculated
        Random random = new Random();
        return random.nextInt(HIGH_ESTIMATE - LOW_ESTIMATE) + LOW_ESTIMATE;
    }

}
