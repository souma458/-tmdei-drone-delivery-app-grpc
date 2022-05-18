import { TransportationRequestService } from "../service/service.js";
import { CreateTransportationRequestResponseDTO } from "../model/response/createTransportationRequestResponseDTO.js";
import { handleError } from "./errorHandler.js";

const service = new TransportationRequestService();

export class TransportationRequestController {
  async createTransportationRequest(call, callback) {
    try {
      const transportationRequest = await service.createTransportationRequest(
        call.request
      );
      callback(
        null,
        new CreateTransportationRequestResponseDTO(transportationRequest)
      );
    } catch (err) {
      handleError(callback, err);
    }
  }
}
