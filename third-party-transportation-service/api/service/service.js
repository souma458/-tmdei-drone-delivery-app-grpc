import { TransportationRequestRepository } from "../repository/transportationRequestRepository.js";
import { DeliveryRepository } from "../repository/deliveryRepository.js";

export class TransportationRequestService {
  constructor() {
    this.transportationRequestRepository =
      new TransportationRequestRepository();
    this.deliveryRepository = new DeliveryRepository();
  }

  async createTransportationRequest(transportationRequest) {
    const delivery = await this.deliveryRepository.save({
      externalRef: transportationRequest.delivery,
    });
    return await this.transportationRequestRepository.save({
      delivery: delivery._id,
    });
  }
}
