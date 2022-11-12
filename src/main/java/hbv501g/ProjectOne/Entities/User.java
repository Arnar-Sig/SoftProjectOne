package hbv501g.ProjectOne.Entities;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.*;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * User object. Contains information pertaining to a particular user.
 */
@Entity
@Table(name = "users")
public class User {
    /**
     * Variables.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    private String username;
    private String password;

    //@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private HashSet<Long> favoriteRecipes;

    /**
     * Constructor without arguments.
     */
    public User(){}

    /**
     * Constructor with arguments.
     * @param username - Name of the user.
     * @param password - Password of the user.
     */
    public User(String username, String password){
        this.username = username;
        this.password = password;
        favoriteRecipes = new HashSet<>();
    }

    /**
     * Getters and setters.
     */
    public long getID() {
        return ID;
    }
    public void setID(long ID) {
        this.ID = ID;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public HashSet<Long> getFavoriteRecipes() {
        if(favoriteRecipes == null){
            favoriteRecipes = new HashSet<>();
        }
        return favoriteRecipes;
    }
    public void setFavoriteRecipes(HashSet<Long> favoriteRecipes) {
        this.favoriteRecipes = favoriteRecipes;
    }

    /**
     * Adds a recipe to a user's list of favourite recipes.
     * @param id - Id of recipe to be added to the user's list of favourite recipes.
     */
    public void addToFavoriteRecipes(Long id) {
        if(favoriteRecipes == null){
            favoriteRecipes = new HashSet<>();
        }
        favoriteRecipes.add(id);
    }

    /**
     * Removes a recipe from a user's list of favourite recipes.
     * @param id - Id of recipe to be added to the user's list of favourite recipes.
     */
    public void removeFromFavouriteRecipes(Long id) {
        favoriteRecipes.remove(id);
    }

}