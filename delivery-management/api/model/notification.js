import mongoose from "mongoose";
const { Schema, model } = mongoose;

const NotificationSchema = new Schema({
  date: { type: Date, required: true, default: new Date() },
  message: { type: String, required: true },
  delivery: { type: Schema.Types.ObjectId, ref: "Delivery", required: true },
});

export const Notification = model("Notification", NotificationSchema);
