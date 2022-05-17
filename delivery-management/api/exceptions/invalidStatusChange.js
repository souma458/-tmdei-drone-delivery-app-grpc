import grpc from "@grpc/grpc-js";

export function InvalidStatusChangeException() {
  const error = new Error("The required change in status is invalid");
  error.status = grpc.status.FAILED_PRECONDITION;
  return error;
}
