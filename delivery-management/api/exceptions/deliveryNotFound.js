import grpc from "@grpc/grpc-js";

export function DeliveryNotFoundException(delivery) {
  const error = new Error("There is no delivery with id = " + delivery);
  error.status = grpc.status.NOT_FOUND;
  return error;
}
