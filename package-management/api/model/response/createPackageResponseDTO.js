export class CreatePackageResponseDTO {
  constructor(createdPackage) {
    this.packageId = createdPackage._id;
  }
}
