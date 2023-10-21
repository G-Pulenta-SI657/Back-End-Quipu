package gpulenta.quipu.user.controller;

import gpulenta.quipu.user.model.User;
import gpulenta.quipu.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Find all users
    @Operation(summary = "Get all users", description = "Get all users details")
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        if (userService.findAll().isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    // Find user by username
    @Operation(summary = "Get user by username", description = "Get a user details by username")
    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(String username) {
        if (userService.getUserByUsername(username) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(userService.getUserByUsername(username), HttpStatus.OK);
    }

    // Find user by email and password
    @Operation(summary = "Get a user by email and password", description = "Get a user's details by their email and password")
    @GetMapping("/by-email-and-password")
    public ResponseEntity<User> getUserByEmailAndPassword(@RequestParam String email, @RequestParam String password) {
        if (userService.getUserByEmailAndPassword(email, password) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(userService.getUserByEmailAndPassword(email, password), HttpStatus.OK);
    }

    // Create user
    @Operation(summary = "Create user", description = "Create a new user")
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    // Update user
    @Operation(summary = "Update user by ID", description = "Update an existing user's information by their ID")
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
        return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
    }

    // Delete user
    @Operation(summary = "Delete user", description = "Delete an existing user")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // Find user by id
    @Operation(summary = "Get user by id", description = "Get a user details by ID")
    @GetMapping("/findbyid/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
