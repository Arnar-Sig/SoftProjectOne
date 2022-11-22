package hbv501g.ProjectOne.Services.Implementation;

import hbv501g.ProjectOne.Entities.User;
import hbv501g.ProjectOne.Repositories.UserRepository;
import hbv501g.ProjectOne.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Implementation of the UserService.
 */
@Service
public class UserServiceImplementation implements UserService {
    /**
     * Variables.
     */
    private UserRepository userRepository;

    /**
     * Constructor.
     */
    @Autowired
    public UserServiceImplementation(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    /**
     * Adds a user to the repository.
     * @param user - User to be added to the repository.
     */
    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    /**
     * Deletes a user from the repository.
     * @param user - User to be deleted from the repository.
     */
    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    /**
     * Deletes all users from the database.
     */
    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    /**
     * Finds and returns a list of all users in the repository.
     * @return - List of all users in the repository.
     */
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * Finds and returns a user by his username.
     * @param username - Username of the user.
     * @return - User object corresponding to the provided username.
     */
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Logs in a user.
     * @param user - User to be logged in.
     * @return - User that is logged in.
     */
    @Override
    public User login(User user) {
        User doesExist = findByUsername(user.getUsername());
        if(doesExist != null){
            if(doesExist.getPassword().equals(user.getPassword())){
                return doesExist;
            }
        }
        return null;
    }

    /**
     * Adds a recipe to a user's list of favourite recipes.
     * @param user - User to whose list of favourite recipes a recipe is to be added.
     * @param id - Id of the recipe which is to be added to the user's list of favourite recipes.
     */
    @Override
    public HashSet<Long> addToFavorites(User user, Long id) {
        user.addToFavoriteRecipes(id);
        save(user);

        for (Long i:user.getFavoriteRecipes()) {
            System.out.println(i);
        }
        return null;
    }


    /**
     * Checks if a recipe is in a user's list of favourite recipes.
     * @param user - User whose list of favourite recipes is being checked.
     * @param id - Id of the recipe which is being checked for membership in the user's list of favourite recipes.
     * @return - Boolean value, true if the recipe is in the list of favourites, false if it is not.
     */
    @Override
    public Boolean isFavorited(User user, Long id) {
        HashSet<Long> fav = user.getFavoriteRecipes();
        if(fav == null){
            return false;
        }
        return fav.contains(id);
    }

    /**
     * Removes a recipe from a user's list of favourite recipes.
     * @param user - User whose list of favourite recipes is to be changed.
     * @param id - Id of recipe to be removed from the user's list of favourite recipes.
     */
    @Override
    public HashSet<Long> removeFromFavourites(User user, Long id) {
        user.removeFromFavouriteRecipes(id);
        save(user);

        for (Long i:user.getFavoriteRecipes()) {
            System.out.println(i);
        }
        return null;
    }

    @Override
    public Boolean userExistsWithUsername(String username) {
        ArrayList<User> allUsers= (ArrayList<User>) userRepository.findAll();
        for (User u : allUsers) {
            if (u.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
}
