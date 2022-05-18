import { Delivery } from "../model/delivery.js";

export class DeliveryRepository {
  constructor() {
    this.model = Delivery;
  }

  async save(delivery) {
    const deliveryToSave = new Delivery(delivery);
    return await deliveryToSave.save();
  }

  async findById(id) {
    const delivery = await this.model.findById(id);
    return delivery;
  }
}
