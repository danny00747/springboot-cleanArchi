package be.rentvehicle.web.rest;

import be.rentvehicle.service.dto.UserDTO;
import be.rentvehicle.service.impl.errors.EmailAlreadyUsedException;
import be.rentvehicle.service.impl.errors.UsernameAlreadyUsedException;
import be.rentvehicle.web.rest.vm.LoginVM;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * REST controller for managing the current user's account.
 */

@Controller
public interface AccountResource {

    /**
     * {@code POST  /register} : register the user.
     *
     * @param userDTO the managed user View Model.
     * @throws EmailAlreadyUsedException {@code 409 (CONFLICT)} if the email is already used.
     * @throws UsernameAlreadyUsedException {@code 409 (CONFLICT)} if the username is already used.
     */
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<UserDTO> registerAccount(@Valid @RequestBody UserDTO userDTO);

    /**
     * {@code POST  /authenticate} : authenticate the user.
     *
     * @param loginVM the managed login View Model.
     * @throws EmailAlreadyUsedException {@code 409 (CONFLICT)} if the email is already used.
     * @throws UsernameAlreadyUsedException {@code 409 (CONFLICT)} if the username is already used.
     */
    @PostMapping("/authenticate")
    ResponseEntity<Map<String, String>> authorize(@Valid @RequestBody LoginVM loginVM);

    /**
     * {@code GET  /users} : get all the users.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of users in body.
     */
    @GetMapping("/admin/users")
    ResponseEntity<List<UserDTO>> getAllUsers(@RequestParam(required = false, defaultValue = "false") boolean eagerload);

    /**
     * Gets a list of all roles.
     * @return a string list of all roles.
     */
    @GetMapping("/users/roles")
    ResponseEntity<List<String>> getRoles();

    /**
     * {@code GET  /account} : get the current user.
     *
     * @return the current user.
     * @throws RuntimeException {@code 404 (Not Found)} if the user couldn't be returned.
     */
    @GetMapping("/account")
    ResponseEntity<UserDTO> getAccount();

    /**
     * {@code GET /user/:username} : get the "username" user.
     *
     * @param username the username of the user to find.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the "username" user, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user/{username}")
    ResponseEntity<UserDTO> getUser(@Valid @PathVariable String username);
}