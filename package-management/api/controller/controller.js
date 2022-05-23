import { PackageService } from "../service/service.js";
import { CreatePackageResponseDTO } from "../model/response/createPackageResponseDTO.js";
import { handleError } from "./errorHandler.js";

const service = new PackageService();

export class PackageController {
  async createPackage(call, callback) {
    try {
      const createdPackage = await service.createPackage(call.request);
      callback(null, new CreatePackageResponseDTO(createdPackage));
    } catch (err) {
      handleError(callback, err);
    }
  }

  async createTagForPackage(call, callback) {
    try {
      const createdTag = await service.createTagForPackage(
        call.request.package_id,
        { tag: call.request.tag }
      );
      callback(null, { tag_id: createdTag._id, tag: createdTag.tag });
    } catch (err) {
      handleError(callback, err);
    }
  }
}
