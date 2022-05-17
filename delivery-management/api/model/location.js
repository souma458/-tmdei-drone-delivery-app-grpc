import mongoose from "mongoose";
const { Schema } = mongoose;

export const LocationSchema = new Schema({
  latitude: { type: Number },
  longitude: { type: Number },
});
