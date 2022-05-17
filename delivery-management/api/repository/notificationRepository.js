import { Notification } from "../model/notification.js";

export class NotificationRepository {
  constructor() {
    this.model = Notification;
  }

  async save(notification) {
    const notificationToSave = new Notification(notification);
    return await notificationToSave.save();
  }
}
