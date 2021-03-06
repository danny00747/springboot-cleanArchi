package be.rentvehicle.domain;

import be.rentvehicle.config.Constants;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * A user entity.
 */
@Entity
@Table(name = "users")
public class User extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "id")
    private UUID id;

    @NotNull
    @Size(min = 4, max = 25)
    @Column(length = 25, unique = true, nullable = false)
    private String username;

    @NotNull
    @Pattern(regexp = Constants.EMAIL_REGEX)
    @Column(length = 25, unique = true, nullable = false)
    private String email;

    @NotNull
    @Column(nullable = false)
    private boolean activated = false;

    @Size(max = 36)
    @Column(name = "activation_key", length = 36)
    private String activationKey;

    @Column(name = "phone_number", length = 12)
    private String phoneNumber;

    @Column(name = "verification_phone_code")
    private Integer verificationPhoneCode;

    @NotNull
    private String password;

    @Column(name = "reset_key")
    private String resetKey;

    @Column(name = "reset_date")
    private Instant resetDate;

    @OneToOne(mappedBy = "user")
    private Address address;

    @OneToOne(mappedBy = "user")
    private Booking booking;

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

    public boolean isActivated() {
        return activated;
    }

    public Integer getVerificationPhoneCode() {
        return verificationPhoneCode;
    }

    public void setVerificationPhoneCode(Integer verificationPhoneCode) {
        this.verificationPhoneCode = verificationPhoneCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getActivationKey() {
        return activationKey;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    public void setResetKey(String resetKey) {
        this.resetKey = resetKey;
    }

    public void setResetDate(Instant resetDate) {
        this.resetDate = resetDate;
    }

    public String getResetKey() {
        return resetKey;
    }

    public Instant getResetDate() {
        return resetDate;
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

    public Address getAddress() {
        return address;
    }

    public User address(Address address) {
        this.address = address;
        return this;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
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
