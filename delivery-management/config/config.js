if (!process.env.MONGODB_URI) {
  throw new Error("Couldn't find MongoDB connection path");
}

export default {
  appName: "Delivery Management",
  host: process.env.HOST || "delivery-management",
  port: parseInt(process.env.PORT, 10) || 9090,
  databaseURL: process.env.MONGODB_URI,
};
