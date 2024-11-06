package kg.mega.delivery_service.model.entity;

import jakarta.persistence.*;
import kg.mega.delivery_service.enums.Role;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Data
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    Long id;
    @Column(nullable = false)
    String username;
    @Column(nullable = false, unique = true)
    String email;
    @Column(nullable = false)
    String password;
    @Column(nullable = false)
    String phone;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    Role role;
    @Column(nullable = false)
    @OneToMany(mappedBy = "user")
    Set<Parcel> parcels;


}
