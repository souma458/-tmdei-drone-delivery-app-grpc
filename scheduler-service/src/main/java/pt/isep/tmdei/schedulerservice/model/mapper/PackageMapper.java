package pt.isep.tmdei.schedulerservice.model.mapper;

import org.springframework.stereotype.Component;

import pt.isep.tmdei.packagemanagement.CreatePackageRequest;

@Component
public class PackageMapper {

    public CreatePackageRequest mapPackageResponse(Integer weight, Integer height, Integer width, String delivery) {
        return CreatePackageRequest.newBuilder().setWeight(weight).setHeight(height).setWidth(width)
                .setDelivery(delivery).build();
    }

}
