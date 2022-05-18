import { PackageRepository } from "../repository/packageRepository.js";
import { TagRepository } from "../repository/tagRepository.js";

export class PackageService {
  constructor() {
    this.repository = new PackageRepository();
    this.tagRepository = new TagRepository();
  }

  async createPackage(packageToDeliver) {
    return await this.repository.save(packageToDeliver);
  }

  async createTagForPackage(packageId, tagInfo) {
    const dbPackage = await this.repository.findById(packageId);
    const tag = await this.tagRepository.save(tagInfo);
    dbPackage.senderRef = tag._id;
    await this.repository.save(dbPackage);
    return tag;
  }
}
