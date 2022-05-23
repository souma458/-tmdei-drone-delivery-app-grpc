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

  async pickupPackage(call, callback) {
    try {
      const delivery = await service.readyDelivery(call.request.drone);
      callback(null, delivery);
    } catch (err) {
      handleError(callback, err);
    }
  }

  async completeDelivery(call, callback) {
    try {
      await service.completeDelivery(call.request.delivery);
      callback(null, null);
    } catch (err) {
      handleError(callback, err);
    }
  }

  async getDelivery(call, callback) {
    try {
      const delivery = await service.get(call.request.delivery);
      callback(null, delivery);
    } catch (err) {
      handleError(callback, err);
    }
  }
}
