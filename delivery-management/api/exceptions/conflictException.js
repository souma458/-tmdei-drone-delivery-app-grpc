import grpc from "@grpc/grpc-js";

export function ConflictException(message) {
  const error = new Error(message);
  error.status = grpc.status.FAILED_PRECONDITION;
  return error;
}
