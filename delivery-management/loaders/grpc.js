import protoLoader from "@grpc/proto-loader";
import { DeliveryController } from "../api/controller/controller.js";

const DELIVERY_SERVICE_PROTO_PATH = "./proto/delivery_service.proto";

export default ({ server, grpc }) => {
  const controller = new DeliveryController();

  var packageDefinition = protoLoader.loadSync(DELIVERY_SERVICE_PROTO_PATH, {
    keepCase: true,
    longs: String,
    enums: String,
    defaults: true,
    oneofs: true,
  });
  const deliveryServiceProto = grpc.loadPackageDefinition(packageDefinition);

  server.addService(deliveryServiceProto.DeliveryService.service, {
    createDelivery: controller.createDelivery,
    updateDeliveryStatus: controller.updateDeliveryStatus,
    updateDeliveryDrone: controller.updateDeliveryDrone,
    pickupPackage: controller.pickupPackage,
  });
};
