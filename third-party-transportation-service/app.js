import config from "./config/config.js";
import grpc from "@grpc/grpc-js";
import loaders from "./loaders/loaders.js";
import Logger from "./loaders/logger.js";

async function startServer() {
  const server = new grpc.Server();

  await loaders({ server: server, grpc: grpc });

  server.bindAsync(
    config.host + ":" + config.port,
    grpc.ServerCredentials.createInsecure(),
    (error, port) => {
      Logger.info(`
      ######################################################
      ${config.appName} Server listening on port: ${config.port}
      ######################################################
      `);
      server.start();
    }
  );
}

startServer();
