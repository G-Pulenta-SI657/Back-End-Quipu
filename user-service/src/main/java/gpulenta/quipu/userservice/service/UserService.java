package gpulenta.quipu.userservice.service;

import gpulenta.quipu.userservice.model.User;

import java.util.List;

public interface UserService {
    public List<User> findAll();

    public User createUser(User user);

    public User getUserByUsername(String username);

    public User getUserByEmailAndPassword(String email, String password);

    public User updateUser(User user);

    public void deleteUser(Long id);

    public User findById(Long id);
}
