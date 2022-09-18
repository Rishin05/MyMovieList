
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SearchController {

    @FXML private Button btnClose;
    @FXML private Button btnSearch;
    @FXML private TextArea textArea;
    @FXML private TextField searchField;


    static MovieList movieList = new MovieList();

    @FXML
    private void onActionClose() {
        Stage stage = (Stage) btnClose.getScene().getWindow();

        stage.close(); //closes the window without adding data
    }

    @FXML
    private void onActionSearch() {

        //Searches for the input user types from the file
        File file = new File("Movies.txt");
        String[] words = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String s;
            String input = searchField.getText();
            int count=0;
            while ((s=reader.readLine()) != null){
                words = s.split(",");
                for (String word : words) {
                    if (word.equals(input)) {
                        count++;
                    }
                }

            }
            if (count !=0) {
                //if searched input is there
                textArea.setText("Here is your search Result: " + input);
                textArea.setEditable(false);
            } else {
                //if searched input is not present
                textArea.setText("OOPS!!!     No Result Found :(");
                textArea.setEditable(false);
            }
            reader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
