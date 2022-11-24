package hbv501g.ProjectOne.Services;

import hbv501g.ProjectOne.Entities.Recipe;
import hbv501g.ProjectOne.Entities.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Interface for Recipe service.
 */
public interface UserService {
    /**
     * Saves a user.
     * @param user - User to be saved.
     * @return - Saved user.
     */
    User save(User user);

    /**
     * Deletes a user from the database.
     * @param user - User to be deleted.
     */
    void delete(User user);

    /**
     * Deletes all users from the database.
     */
    void deleteAll();

    /**
     * Finds and returns all users.
     * @return - List of all users.
     */
    List<User> findAll();

    /**
     * Finds and returns all users by a given username.
     * @param username - Username used to find a user.
     * @return - User matching the given username.
     */
    User findByUsername(String username);

    /**
     * Logs in a user.
     * @param user - User to be logged in.
     * @return - Logged in user.
     */
    User login(User user);

    /**
     * Adds a recipe to a user's list of favourite recipes.
     * @param user - User to whose list of favourite recipes a recipe is to be added.
     * @param id - Id of recipe to be added to a user's list of favourite recipes.
     */
    HashSet<Long> addToFavorites(User user, Long id);

    /**
     * Checks whether a recipe is in a user's list of favourite recipes.
     * @param user - User whose list of favourite recipes is to be checked.
     * @param id - Id of recipe whose membership in the user's list of favourite recipes is to be checked.
     * @return - Boolean, true if recipe is in the user's list of favourite recipes, false if not.
     */
    Boolean isFavorited(User user, Long id);

    /**
     * Removes a recipe from a user's list of favourite recipes.
     * @param user - User whose list of favourite recipes is to be changed.
     * @param id   - Id of recipe to be removed from the user's list of favourite recipes.
     */
    void removeFromFavourites(User user, Long id);

    /**
     * Checks if a User already exists with the name provided.
     * @param username The name to check for.
     * @return Boolean value of whether there already exists a user with the name.
     */
    Boolean userExistsWithUsername(String username);

    /**
     * Checks if the user has any recipes favourited.
     * @param user The user to be checked.
     * @return Boolean value of whether the user has any recipes favourited.
     */
    Boolean hasFavourites(User user);

    /**
     * Gets the favourited recipes of a user.
     * @param user The user to be checked.
     * @return Arraylist of recipes which the user has added to favourites..
     */
    ArrayList<Recipe> getFavourites(User user);
}
