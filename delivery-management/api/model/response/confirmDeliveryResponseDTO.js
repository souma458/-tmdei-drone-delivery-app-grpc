export class ConfirmDeliveryResponseDTO {
  constructor(confirmation) {
    this.confirmation = confirmation._id;
    this.delivery = confirmation.delivery;
    this.finger_print = confirmation.fingerPrint
      ? confirmation.fingerPrint
      : null;
    this.signature = confirmation.signature ? confirmation.signature : null;
  }
}
