package be.rentvehicle.dao;

import be.rentvehicle.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Spring Data JPA repository for the {@link User} entity.
 */
public interface UserDAO extends JpaRepository<User, UUID> {

    Optional<User> findOneByEmailIgnoreCase(String email);

    Optional<User> findOneByUsername(String username);

    @EntityGraph(attributePaths = "roles")
    Optional<User> findOneWithRolesByUsername(String username);

    @EntityGraph(attributePaths = "roles")
    Optional<User> findOneWithRolesByEmailIgnoreCase(String email);

    Optional<User> findOneByActivationKey(String activationKey);

    List<User> findAllByActivatedIsFalseAndActivationKeyIsNotNullAndCreatedDateBefore(Instant dateTime);

    List<User> findAllByCreatedDateBetween(Instant dateTime1, Instant dateTime2);

    Page<User> findAllByUsernameNot(Pageable pageable, String username);

    @Query(""" 
                select distinct user from User user 
                left join fetch user.roles 
                join fetch user.address 
                order by user.username 
            """)
    List<User> findAllWithEagerRelationships();
}
