package hbv501g.ProjectOne.Repositories;

import hbv501g.ProjectOne.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
public interface UserRepository extends CrudRepository<User, Long> {
    User save(User user);
    void delete(User user);
    List<User> findAll();
    User findByUsername(String username);
}
