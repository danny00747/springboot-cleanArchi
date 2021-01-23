package be.rentvehicle.domain;

import be.rentvehicle.config.Constants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "id")
    private UUID id;

    @NotNull
    @Pattern(regexp = Constants.USERNAME_REGEX)
    @Size(min = 4, max = 25)
    @Column(length = 25, unique = true, nullable = false)
    private String username;

    @NotNull
    @Pattern(regexp = Constants.EMAIL_REGEX)
    @Column(length = 25, unique = true, nullable = false)
    private String email;

    @JsonIgnore
    @NotNull
    private String password;

    /*
       @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Set<Roles> roles = new HashSet<>();
     */

    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_name", referencedColumnName = "name")})
    private Set<Roles> roles = new HashSet<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }

    public User roles(Set<Roles> roles) {
        this.roles = roles;
        return this;
    }

    public User addRoles(Roles roles) {
        this.roles.add(roles);
        roles.setUser(this);
        return this;
    }

    public User removeRoles(Roles roles) {
        this.roles.remove(roles);
        roles.setUser(null);
        return this;
    }


    @Override
    public boolean equals(Object obj) {
        return this == obj;
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                "}";
    }
}
