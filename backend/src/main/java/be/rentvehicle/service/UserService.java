package be.rentvehicle.service;

import be.rentvehicle.domain.User;
import be.rentvehicle.service.dto.UserDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.DoubleStream;

public interface UserService {

    /**
     * Save a user.
     *
     * @param userDTO the entity to save.
     * @return the persisted entity.
     */
    UserDTO registerUser(UserDTO userDTO);

    /**
     * Get all the users with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    List<UserDTO> findAllWithEagerRelationships();

    /**
     * Get all the users.
     *
     * @return the list of entities.
     */
    List<UserDTO> findAll();

    /**
     * Gets a list of all the roles.
     * @return a list of all the roles.
     */
    List<String> getRoles();


    /**
     * Gets a user.
     * @return a user .
     */
    Optional<User> getUserWithRoles();

    /**
     * Gets a user.
     * @return a user .
     */
    Optional<User> getUserWithRolesByUsername(String username);
}