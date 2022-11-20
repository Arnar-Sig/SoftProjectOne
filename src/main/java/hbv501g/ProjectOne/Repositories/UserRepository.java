package hbv501g.ProjectOne.Repositories;

import hbv501g.ProjectOne.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * UserRepository interface. Contains the users.
 */
public interface UserRepository extends CrudRepository<User, Long> {
    /**
     * Saves a user into the database.
     * @param user - User to be saved into the database.
     */
    User save(User user);

    /**
     * Deletes a user from the database.
     * @param user - User to be deleted from the database.
     */
    void delete(User user);

    /**
     * Returns a list of all users.
     * @return - List of all users.
     */
    List<User> findAll();

    /**
     * Finds and returns a particular user by his username.
     * @param username - Username of user.
     * @return - User object corresponding the given username.
     */
    User findByUsername(String username);
}
