import { DeliveryService } from "../service/service.js";
import { handleError } from "./errorHandler.js";

const service = new DeliveryService();

export class DeliveryController {
  async createDelivery(call, callback) {
    try {
      const delivery = await service.createDelivery(call.request);
      callback(null, delivery);
    } catch (err) {
      handleError(callback, err);
    }
  }
}
