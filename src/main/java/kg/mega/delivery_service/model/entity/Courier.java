package kg.mega.delivery_service.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Table(name = "couriers")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Courier {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    Long id;
    @Column(nullable = false)
    String userName;
    @Column(nullable = false)
    String email;
    @Column(nullable = false)
    String phone;

    @OneToMany(mappedBy = "courier")
    Set<Parcel> parcels;

    public Set<Parcel> getParcels() {
        return parcels;
    }

    public void setParcels(Set<Parcel> parcels) {
        this.parcels = parcels;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Courier(String userName, String email, String phone) {
        this.userName = userName;
        this.email = email;
        this.phone = phone;
    }

    public Courier(String userName, String email, String phone, Set<Parcel> parcels) {
        this.userName = userName;
        this.email = email;
        this.phone = phone;
        this.parcels = parcels;
    }

    public Courier() {
    }

    ;
}
