package rs.raf.WebAvgust.repositories;

import rs.raf.WebAvgust.entities.User;

import java.util.List;

public interface UserRepository {

    public User addUser(User user);
    public List<User> allUsers();
    public User findUser(String email);
    public void deleteUser(String email);
    public void updateUser(User user);

}
