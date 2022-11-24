package hbv501g.ProjectOne.Entities;

/**
 * Object used for passing search parameters to html-templates.
 */
public class SearchModel {
    /**
     * Variables.
     */
    private long id;
    private String content;

    /**
     * Getters and setters.
     */
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