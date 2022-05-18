import { Firestore } from "@google-cloud/firestore";

export class CreateTransportationRequestResponseDTO {
  constructor(transportationRequest) {
    this.requestId = transportationRequest._id;
    this.status = transportationRequest.status;
    this.requestDate = Firestore.Timestamp.fromDate(
      new Date(transportationRequest.requestDate)
    );
  }
}
