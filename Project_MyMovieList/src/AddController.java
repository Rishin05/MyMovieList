
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/*
 * @author Rishin Patel
 */

public class AddController {
    @FXML private TextField movieName;
    @FXML private TextField MovieLength;
    @FXML private TextField movieRating;
    @FXML private Button btnAdd;
    @FXML private Button btnCancel;
    @FXML private TextField genre;

    

    @FXML
    private void onActionCancel() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();

        stage.close(); //closes the window without adding data
    }
    @FXML 
    private void OnActionAdd() {
        //Adds the data which user types in the stage to the file
        File file = new File("Movies.txt");

        if (file != null) 
        {
            try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Movies.txt",true));
            writer.write("" +Integer.parseInt(MovieLength.getText())+ ",");
            writer.write(movieName.getText() + ",");
            writer.write(genre.getText()+ ",");
            writer.write("" + Double.parseDouble(movieRating.getText()) + "\n");
            writer.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
        movieName.clear();
        MovieLength.clear();
        movieRating.clear();
        genre.clear();
        
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }


    }

}
