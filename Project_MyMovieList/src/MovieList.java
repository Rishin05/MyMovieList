import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/*
 * @author Rishin Patel
 */

public class MovieList {


    //takes data from file and stores it in an array form with help of Movies
    public  ArrayList<Movies> MovieData() {
        ArrayList<Movies> movie = new ArrayList<Movies>();
        try {BufferedReader reader = new BufferedReader(new FileReader("Movies.txt"));
        String line = reader.readLine();

        while(line != null) {
            String[] movieData = line.split(",");
            Movies movieL = new Movies(movieData[1], Double.parseDouble(movieData[0]), Double.parseDouble(movieData[3]),movieData[2]);
            movie.add(movieL);
            line = reader.readLine();
        }
        reader.close();
    } 
    catch (FileNotFoundException e) {
        e.printStackTrace();

    }
    catch (IOException e) {
        e.printStackTrace();
    }
    
    return movie;
    
    }
    
}
