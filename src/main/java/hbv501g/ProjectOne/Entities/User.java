package hbv501g.ProjectOne.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    private String username;
    private String password;

    //@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private HashSet<Long> favoriteRecipes;

    public User(){}
    public User(String username, String password){
        this.username = username;
        this.password = password;
        favoriteRecipes = new HashSet<>();
    }
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

    public void addToFavoriteRecipes(Long id) {
        if(favoriteRecipes == null){
            favoriteRecipes = new HashSet<>();
        }
        favoriteRecipes.add(id);
    }
}


