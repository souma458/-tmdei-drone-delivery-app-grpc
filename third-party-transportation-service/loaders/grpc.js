import protoLoader from "@grpc/proto-loader";
import { TransportationRequestController } from "../api/controller/controller.js";

const THIRD_PARTY_TRANSPORTATION_SERVICE_PROTO_PATH =
  "./proto/third_party_transportation_service.proto";

export default ({ server, grpc }) => {
  const controller = new TransportationRequestController();

  var packageDefinition = protoLoader.loadSync(
    THIRD_PARTY_TRANSPORTATION_SERVICE_PROTO_PATH,
    {
      keepCase: true,
      longs: String,
      enums: String,
      defaults: true,
      oneofs: true,
    }
  );
  const thirdPartyTransportationService =
    grpc.loadPackageDefinition(packageDefinition);

  server.addService(
    thirdPartyTransportationService.ThirdPartyTransportationService.service,
    {
      createTransportationRequest: controller.createTransportationRequest,
    }
  );
};
