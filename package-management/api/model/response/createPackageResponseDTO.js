export class CreatePackageResponseDTO {
  constructor(createdPackage) {
    this.package_id = createdPackage._id;
  }
}
