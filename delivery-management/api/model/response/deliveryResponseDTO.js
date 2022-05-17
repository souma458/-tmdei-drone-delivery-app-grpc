import { Firestore } from "@google-cloud/firestore";

export class DeliveryResponseDTO {
  constructor(delivery) {
    this.username = delivery.account;
    this.delivery = delivery._id;
    this.pickup_latitude = delivery.pickup.latitude;
    this.pickup_longitude = delivery.pickup.longitude;
    this.dropOff_latitude = delivery.dropOff.latitude;
    this.dropOff_longitude = delivery.dropOff.longitude;
    this.created = Firestore.Timestamp.fromDate(new Date(delivery.created));
    this.status = delivery.status;
    this.expedited = delivery.expedited;
    this.drone = delivery.drone ? delivery.drone : null;
    this.transportationRequest = delivery.transportationRequest
      ? delivery.transportationRequest
      : null;
  }
}
