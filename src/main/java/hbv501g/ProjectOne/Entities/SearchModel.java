package hbv501g.ProjectOne.Entities;

import hbv501g.ProjectOne.Entities.Recipe;

import java.util.ArrayList;

public class SearchModel {

    private long id;
    private String content;

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }

    private ArrayList<Recipe> recipes;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}