import grpc from "@grpc/grpc-js";

export function BadRequestException(message) {
  const error = new Error(message);
  error.status = grpc.status.INVALID_ARGUMENT;
  return error;
}
