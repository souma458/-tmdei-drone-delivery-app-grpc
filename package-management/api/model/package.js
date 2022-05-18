import mongoose from "mongoose";
const { Schema, model } = mongoose;

const PackageSchema = new Schema({
  weight: { type: Number, required: true },
  height: { type: Number, required: true },
  width: { type: Number, required: true },
  senderRef: { type: Schema.Types.ObjectId, ref: "Tag" },
  delivery: { type: String, required: true },
});

export const Package = model("Package", PackageSchema);
