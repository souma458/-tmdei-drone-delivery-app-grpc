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

  async updateDeliveryStatus(call, callback) {
    try {
      await service.partiallyUpdateDelivery(call.request.delivery, {
        status: call.request.status,
      });
      callback(null, null);
    } catch (err) {
      handleError(callback, err);
    }
  }

  async updateDeliveryDrone(call, callback) {
    try {
      await service.partiallyUpdateDelivery(call.request.delivery, {
        drone: call.request.drone,
      });
      callback(null, null);
    } catch (err) {
      handleError(callback, err);
    }
  }
}
