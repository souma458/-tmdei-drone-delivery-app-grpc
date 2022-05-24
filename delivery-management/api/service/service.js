import { BadRequestException } from "../exceptions/badRequestException.js";
import { ConflictException } from "../exceptions/conflictException.js";
import { ConfirmDeliveryResponseDTO } from "../model/response/confirmDeliveryResponseDTO.js";
import { ConfirmationRepository } from "../repository/confirmationRepository.js";
import { DeliveryResponseDTO } from "../model/response/deliveryResponseDTO.js";
import { DeliveryRepository } from "../repository/deliveryRepository.js";
import { DeliveryStatus } from "../model/enums/deliveryStatus.js";
import { DeliveryNotFoundException } from "../exceptions/deliveryNotFound.js";
import { InvalidStatusChangeException } from "../exceptions/invalidStatusChange.js";
import { NotificationRepository } from "../repository/notificationRepository.js";

export class DeliveryService {
  constructor() {
    this.deliveryRepository = new DeliveryRepository();
    this.confirmationRepository = new ConfirmationRepository();
    this.notificationRepository = new NotificationRepository();
  }

  async get(deliveryId) {
    const delivery = await this.deliveryRepository.findById(deliveryId);
    if (!delivery) {
      throw new DeliveryNotFoundException(deliveryId);
    }
    return new DeliveryResponseDTO(delivery);
  }

  async getDeliveries(username) {
    const deliveries = await this.deliveryRepository.findByUsername(username);
    return { deliveries: deliveries.map((d) => new DeliveryResponseDTO(d)) };
  }

  async createDelivery(delivery) {
    if (
      !delivery.pickup_latitude ||
      !delivery.pickup_longitude ||
      !delivery.dropOff_latitude ||
      !delivery.dropOff_longitude ||
      !delivery.username
    ) {
      throw new BadRequestException(
        "Username and pickup/dropOff coordinates are required"
      );
    }
    var deliveryToSave = {
      pickup: {
        latitude: delivery.pickup_latitude,
        longitude: delivery.pickup_longitude,
      },
      dropOff: {
        latitude: delivery.dropOff_latitude,
        longitude: delivery.dropOff_longitude,
      },
      account: delivery.username,
    };
    const savedDelivery = await this.deliveryRepository.save(deliveryToSave);
    return new DeliveryResponseDTO(savedDelivery);
  }

  async partiallyUpdateDelivery(deliveryId, delivery) {
    return await this.deliveryRepository.update(deliveryId, delivery);
  }

  async readyDelivery(drone) {
    const delivery =
      await this.deliveryRepository.findOldestReadyToDeliverByDrone(drone);
    delivery.drone = drone;
    delivery.status = DeliveryStatus.DELIVERY_STATUS_HEADED_TO_DROP_OFF;
    const updatedDelivery = await this.deliveryRepository.update(
      delivery._id,
      delivery
    );
    return {
      delivery: updatedDelivery._id,
      pickup: updatedDelivery.pickup,
      dropOff: updatedDelivery.dropOff,
      status: updatedDelivery.status,
    };
  }

  async completeDelivery(delivery) {
    const dbDelivery = await this.deliveryRepository.findById(delivery);
    if (!dbDelivery) {
      throw new DeliveryNotFoundException(delivery);
    }
    await this.notificationRepository.save({
      delivery: delivery,
      message: `Delivery with id = ${delivery} has been completed`,
    });
    return await this.deliveryRepository.update(delivery, {
      status: DeliveryStatus.DELIVERY_STATUS_COMPLETED,
    });
  }

  async cancelDelivery(delivery) {
    const dbDelivery = await this.deliveryRepository.findById(delivery);
    if (!dbDelivery) {
      throw new DeliveryNotFoundException(delivery);
    }
    if (dbDelivery.status != DeliveryStatus.DELIVERY_STATUS_CREATED) {
      throw new InvalidStatusChangeException();
    }

    return await this.deliveryRepository.update(delivery, {
      status: DeliveryStatus.DELIVERY_STATUS_CANCELED,
    });
  }

  async createDeliveryConfirmation(confirmationInfo) {
    if (
      !confirmationInfo.delivery ||
      (!confirmationInfo.signature && !confirmationInfo.fingerPrint)
    ) {
      throw new BadRequestException(
        "The request is invalid. Confirm that both delivery and signature/fingerprint are defined."
      );
    }
    const dbDelivery = await this.deliveryRepository.findById(
      confirmationInfo.delivery
    );
    if (!dbDelivery) {
      throw new DeliveryNotFoundException(delivery);
    }
    if (dbDelivery.status != DeliveryStatus.DELIVERY_STATUS_COMPLETED) {
      throw new ConflictException(
        "Confirmation can only be done for completed deliveries"
      );
    }
    const savedConfirmation = await this.confirmationRepository.save({
      delivery: dbDelivery._id,
      signature: confirmationInfo.signature ? confirmationInfo.signature : null,
      fingerPrint: confirmationInfo.fingerPrint
        ? confirmationInfo.fingerPrint
        : null,
    });
    return new ConfirmDeliveryResponseDTO(savedConfirmation);
  }
}
