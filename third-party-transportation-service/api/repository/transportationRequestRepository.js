import { TransportationRequest } from "../model/transportationRequest.js";

export class TransportationRequestRepository {
  constructor() {
    this.model = TransportationRequest;
  }

  async save(transportationRequest) {
    const transportationRequestToSave = new TransportationRequest(
      transportationRequest
    );
    return await transportationRequestToSave.save();
  }
}
