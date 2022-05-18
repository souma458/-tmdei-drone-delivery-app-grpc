import grpcLoader from "./grpc.js";
import mongooseLoader from "./mongoose.js";
import Logger from "./logger.js";

export default async ({ server, grpc }) => {
  await mongooseLoader();
  Logger.info("DB loaded and connected!");

  await grpcLoader({ server: server, grpc: grpc });
  Logger.info("gRPC server loaded!");
};
