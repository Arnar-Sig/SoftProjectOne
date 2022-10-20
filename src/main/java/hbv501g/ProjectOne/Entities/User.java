package hbv501g.ProjectOne.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    private String username;
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Recipe> favoriteRecipes;

    public User(){}
    public User(String username, String password){
        this.username = username;
        this.password = password;
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
    public List<Recipe> getFavoriteRecipes() {
        return favoriteRecipes;
    }
    public void setFavoriteRecipes(List<Recipe> favoriteRecipes) {
        this.favoriteRecipes = favoriteRecipes;
    }
}
