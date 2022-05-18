import mongoose from "mongoose";
const { Schema, model } = mongoose;

const DeliverySchema = new Schema({
  externalRef: { type: String, required: true },
});

export const Delivery = model("Delivery", DeliverySchema);
