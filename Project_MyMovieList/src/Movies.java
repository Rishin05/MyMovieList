/*
 * @author Rishin Patel
 */

public class Movies {
    private String name;
    private double duration;
    private double rating;
    private String genre;

    Movies(String name, double length, double rating, String genre) {
        this.name = name;
        this.duration = length;
        this.rating = rating;
        this.genre = genre;
    }
    //setters
    public void setName(String name){
        this.name = name;
    }
    public void setDuration(double duration) {
        this.duration = duration;
    }
    public void setRating(double rating) {
        this.rating = rating;
    }
    public void setGenre(String genre){
        this.genre = genre;
    }

    //getters
    public String getName() {
        return name;
    }
    public double getDuration() {
        return duration;
    }
    public double getRating() {
        return rating;
    }

    public String getGenre() {
        return genre;
    }

    
    public String toString() {
        return name;
    }
    

}
