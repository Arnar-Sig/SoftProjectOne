package hbv501g.ProjectOne.Entities;

public class Rating {
    private int rating;
    public Rating(){}

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        if (rating <= 10) {
            this.rating = rating;
        }
    }
}
