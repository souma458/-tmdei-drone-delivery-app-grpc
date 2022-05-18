import mongoose from "mongoose";
const { Schema, model } = mongoose;
import { RequestStatus } from "./enums/requestStatus.js";

const TransportationRequestSchema = new Schema({
  requestDate: { type: Date, required: true, default: new Date() },
  status: {
    type: String,
    enum: RequestStatus,
    required: true,
    default: RequestStatus.REQUEST_STATUS_WAITING,
  },
  delivery: { type: Schema.Types.ObjectId, ref: "Delivery", required: true },
});

export const TransportationRequest = model(
  "TransporationRequest",
  TransportationRequestSchema
);
