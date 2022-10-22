package hbv501g.ProjectOne.Services;

import hbv501g.ProjectOne.Entities.User;

import java.util.HashSet;
import java.util.List;

public interface UserService {
    User save(User user);
    void delete(User user);
    List<User> findAll();
    User findByUsername(String username);
    User login(User user);
    HashSet<Long> addToFavorites(User user, Long id);
    Boolean isFavorited(User user, Long id);
}
