import protoLoader from "@grpc/proto-loader";
import { PackageController } from "../api/controller/controller.js";

const PACKAGE_SERVICE_PROTO_PATH = "./proto/package_service.proto";

export default ({ server, grpc }) => {
  const controller = new PackageController();

  var packageDefinition = protoLoader.loadSync(PACKAGE_SERVICE_PROTO_PATH, {
    keepCase: true,
    longs: String,
    enums: String,
    defaults: true,
    oneofs: true,
  });
  const packageService = grpc.loadPackageDefinition(packageDefinition);

  server.addService(packageService.PackageService.service, {
    createPackage: controller.createPackage,
  });
};
