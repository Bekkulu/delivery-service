package kg.mega.delivery_service.model.entity;

import jakarta.persistence.*;
import kg.mega.delivery_service.enums.ParcelStatus;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "parcels")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Parcel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    Long id;
    @Column(nullable = false)
    ParcelStatus status;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(nullable = false)
    Date beginDate;
    @Column(nullable = false)
    Long pickupAddressId;
    @Column(nullable = false)
    Long deliveryAddressId;
    @Column(nullable = false)
    Float weight;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "courier_id")
    private Courier courier;

    public Courier getCourier() {
        return courier;
    }

    public void setCourier(Courier courier) {
        this.courier = courier;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public ParcelStatus getStatus() {
        return status;
    }

    public void setStatus(ParcelStatus status) {
        this.status = status;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Long getPickupAddressId() {
        return pickupAddressId;
    }

    public void setPickupAddressId(Long pickupAddressId) {
        this.pickupAddressId = pickupAddressId;
    }

    public Long getDeliveryAddressId() {
        return deliveryAddressId;
    }

    public void setDeliveryAddressId(Long deliveryAddressId) {
        this.deliveryAddressId = deliveryAddressId;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Parcel(ParcelStatus status, Date beginDate, Long pickupAddressId, Long deliveryAddressId, Float weight) {

        this.status = status;
        this.beginDate = beginDate;
        this.pickupAddressId = pickupAddressId;
        this.deliveryAddressId = deliveryAddressId;
        this.weight = weight;
    }

    public Parcel(ParcelStatus status, Date beginDate, Long pickupAddressId, Long deliveryAddressId, Float weight, User user) {
        this.status = status;
        this.beginDate = beginDate;
        this.pickupAddressId = pickupAddressId;
        this.deliveryAddressId = deliveryAddressId;
        this.weight = weight;
        this.user = user;
    }

    public Parcel() {
    }

    ;
}
