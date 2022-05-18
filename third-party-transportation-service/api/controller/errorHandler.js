import grpc from "@grpc/grpc-js";

export const handleError = (callback, err) => {
  return callback({
    code: err.status || grpc.status.INTERNAL,
    message: err.message,
  });
};
