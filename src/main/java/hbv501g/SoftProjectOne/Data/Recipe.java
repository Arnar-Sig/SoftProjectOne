package hbv501g.SoftProjectOne.Data;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Repository
public class Recipe {
    @Id
    @GeneratedValue
    private int id;
    private ArrayList<String> ingredients;
    private String imageName;

    public Recipe(ArrayList<String> ingredients, String imageName){
        this.ingredients = ingredients;
        this.imageName = imageName;
    }
    public Recipe(){
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", ingredients=" + ingredients +
                ", imageName='" + imageName + '\'' +
                '}';
    }
}
