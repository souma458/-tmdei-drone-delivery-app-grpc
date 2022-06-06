import { Delivery } from "../model/delivery.js";
import { DeliveryStatus } from "../model/enums/deliveryStatus.js";

export class DeliveryRepository {
  constructor() {
    this.model = Delivery;
  }

  async save(delivery) {
    const deliveryToSave = new Delivery(delivery);
    return await deliveryToSave.save();
  }

  async findById(id) {
    const delivery = await this.model
      .findById(id)
      .populate("pickup")
      .populate("dropOff");
    return delivery;
  }

  async update(deliveryId, newDelivery) {
    return await this.model.findByIdAndUpdate(deliveryId, newDelivery, {
      new: true,
    });
  }

  async findOldestReadyToDeliverByDrone(drone) {
    const deliveries = await this.model
      .find({
        drone: drone,
        status: DeliveryStatus.DELIVERY_STATUS_CREATED,
      })
      .sort({ created: 1 });
    if (deliveries.length > 0) {
      return deliveries[0];
    }
    return null;
  }

  async findByUsername(username) {
    const deliveries = await this.model
      .find({ username: username })
      .populate("pickup")
      .populate("dropOff");
    return deliveries;
  }
}
