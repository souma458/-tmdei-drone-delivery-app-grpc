import mongoose from "mongoose";
const { Schema, model } = mongoose;
import { DeliveryStatus } from "./enums/deliveryStatus.js";
import { LocationSchema as Location } from "./location.js";

const DeliverySchema = new Schema({
  expedited: { type: Boolean, required: true, default: false },
  pickup: { type: Location, required: true },
  dropOff: { type: Location, required: true },
  drone: { type: Number },
  transportationRequest: { type: String },
  created: { type: Date, required: true, default: new Date() },
  account: { type: String, required: true },
  status: {
    type: String,
    enum: DeliveryStatus,
    required: true,
    default: DeliveryStatus.DELIVERY_STATUS_CREATED,
  },
});

export const Delivery = model("Delivery", DeliverySchema);
