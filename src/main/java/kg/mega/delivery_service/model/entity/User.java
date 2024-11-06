package kg.mega.delivery_service.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Data
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User implements UserDetails {

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
    @ManyToOne
    @JoinColumn(name = "roles_id")
    private Role role;
    @Column(nullable = false)
    @OneToMany(mappedBy = "user")
    Set<Parcel> parcels;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role
                .getPermission()
                .stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getPermissionName()))
                .collect(Collectors.toList());
    }
}
