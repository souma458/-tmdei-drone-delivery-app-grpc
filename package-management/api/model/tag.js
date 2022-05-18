import mongoose from "mongoose";
const { Schema, model } = mongoose;

const TagSchema = new Schema({
  tag: { type: String, required: true },
});

export const Tag = model("Tag", TagSchema);
