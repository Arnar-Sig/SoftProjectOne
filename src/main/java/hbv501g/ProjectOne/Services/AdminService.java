package hbv501g.ProjectOne.Services;

/**
 * Interface for the AdminService.
 */
public interface AdminService {
    /**
     * Deletes all recipes from the database.
     */
    void deleteAllRecipes();

    /**
     * Deletes all recipes that were uploaded by users from the database.
     */
    void deleteAllCustomRecipes();

    /**
     * Deletes all comments from all recipes.
     */
    void deleteAllComments();

    /**
     * Deletes all user accounts.
     */
    void deleteAllUsers();

    /**
     * Resets all ratings to their original number.
     */
    void resetAllRatings();
}
