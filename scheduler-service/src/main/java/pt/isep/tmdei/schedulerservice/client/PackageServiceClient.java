package pt.isep.tmdei.schedulerservice.client;

import pt.isep.tmdei.packagemanagement.CreatePackageRequest;
import pt.isep.tmdei.packagemanagement.CreatePackageResponse;

public interface PackageServiceClient {

    CreatePackageResponse createPackage(CreatePackageRequest request);

}
