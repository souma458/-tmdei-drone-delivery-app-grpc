import mongoose from "mongoose";
const { Schema, model } = mongoose;

const ConfirmationSchema = new Schema({
  signature: { type: String },
  fingerPrint: { type: String },
  delivery: { type: Schema.Types.ObjectId, ref: "Delivery" },
});

export const Confirmation = model("Confirmation", ConfirmationSchema);
