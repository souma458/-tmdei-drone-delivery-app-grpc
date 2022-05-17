import { Confirmation } from "../model/confirmation.js";

export class ConfirmationRepository {
  constructor() {
    this.model = Confirmation;
  }

  async save(confirmation) {
    const confirmationToSave = new Confirmation(confirmation);
    return await confirmationToSave.save();
  }
}
