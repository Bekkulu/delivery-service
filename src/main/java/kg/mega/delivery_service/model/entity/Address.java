package kg.mega.delivery_service.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "addresses")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Column(nullable = false)
    String street;
    @Column(nullable = false)
    String city;
    @Column(nullable = false)
    String houseNumber;
    @Column(nullable = false)
    String postalCode;
    @Column(nullable = false)
    String apartment;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User user;

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


    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public Address(String street, String city, String houseNumber, String postalCode, String apartment) {
        this.street = street;
        this.city = city;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.apartment = apartment;
    }

    public Address(String street, String city, String houseNumber, String postalCode, String apartment, User user) {
        this.street = street;
        this.city = city;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.apartment = apartment;
        this.user = user;
    }

    public Address() {
    }

    ;
}
