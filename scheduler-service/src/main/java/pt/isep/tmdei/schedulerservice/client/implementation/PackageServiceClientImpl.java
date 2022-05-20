package pt.isep.tmdei.schedulerservice.client.implementation;

import org.springframework.stereotype.Component;

import net.devh.boot.grpc.client.inject.GrpcClient;
import pt.isep.tmdei.packagemanagement.CreatePackageRequest;
import pt.isep.tmdei.packagemanagement.CreatePackageResponse;
import pt.isep.tmdei.packagemanagement.PackageServiceGrpc;
import pt.isep.tmdei.schedulerservice.client.PackageServiceClient;

@Component
public class PackageServiceClientImpl implements PackageServiceClient {

    @GrpcClient("package-management")
    private PackageServiceGrpc.PackageServiceBlockingStub packageManagementStub;

    @Override
    public CreatePackageResponse createPackage(CreatePackageRequest request) {
        return packageManagementStub.createPackage(request);
    }

}
