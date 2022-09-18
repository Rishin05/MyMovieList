import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/*
 * @author Rishin Patel
 */

public class MainController implements Initializable{
    @FXML private ListView<Movies> MovieView;
    @FXML private Button BtnAdd;
    @FXML private Button BtnSearch;
    @FXML private Button BtnDelete;
    @FXML private Button BtnEdit;
    @FXML private Button btnShow;
    @FXML private TextField nameField;
    @FXML private TextField durationField;
    @FXML private TextField genre;
    @FXML private TextField rating;

    static MovieList movieList = new MovieList();


    

    @FXML 
    private void OnActionAdd() {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("Add.fxml"));
            Scene scene = new Scene(root);
            Stage secondStage = new Stage();
            secondStage.setScene(scene);
            secondStage.initModality(Modality.APPLICATION_MODAL);   // Use this so you have to close the 2nd window to 
                                                                    //return to main window
            secondStage.showAndWait();

            //clears the textfields on the right side of pane
            nameField.clear();
            durationField.clear();
            rating.clear();
            genre.clear();

        } catch(IOException e){ 
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updatedata(); // changes and updates the ListView 
        BtnEdit.setDisable(true); // disable's edit button when user haven't selected show data button to edit the data
    }

    @FXML 
    private void OnActionDelete() throws IOException {

        int selectedMoive = MovieView.getSelectionModel().getSelectedIndex(); //selects the number of line from listview 
        String deleteLine = Files.readAllLines(Paths.get("Movies.txt")).get(selectedMoive); //selects the same line from the file

        MovieView.getItems().remove(selectedMoive); //removes selected item from listview

        //removes slected data form the file
        File inputFile = new File("Movies.txt");
        File tempFile = new File("tempfile.txt");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String ltr = deleteLine;
            String currentLine;

            while((currentLine = reader.readLine()) != null) {
                String trimmedLine = currentLine.trim();
                if(trimmedLine.equals(ltr)) continue;
                writer.write(currentLine + "\n");
            }
            reader.close();
            writer.close();
            boolean successful = true;
            if (successful){
                tempFile.renameTo(inputFile);
            }

            nameField.clear();
            durationField.clear();
            rating.clear();
            genre.clear();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    @FXML
    private void OnActionShow() {

        int n = MovieView.getSelectionModel().getSelectedIndex(); //gets the int of the line which user selected to show

        try{ 
            //gets data from file and displays on the right hand side of the console
            String line = Files.readAllLines(Paths.get("Movies.txt")).get(n);
            String[] data = line.split(",");
            nameField.setText("" + data[1]);
            durationField.setText("" + data[0]);
            genre.setText("" + data[2]);
            rating.setText("" + data[3]);
            BtnEdit.setDisable(false);


          } 
          catch(IOException e){
            e.printStackTrace();
          }
        

    }
    
    @FXML
    private void OnActionEdit() throws IOException {
        int n = MovieView.getSelectionModel().getSelectedIndex(); // gets the line user wants to edit the data
        String Dataline = Files.readAllLines(Paths.get("Movies.txt")).get(n); //gets the same line on the file

        //changes the existing data with new data
        File file = new File("Movies.txt");
        String oldContent = Dataline;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();

            while (line != null) {
                oldContent = oldContent + line + System.lineSeparator();
                line = reader.readLine();
            }
            String newContent = oldContent.replaceAll(oldContent, "" + durationField.getText() + "," + nameField.getText() + "," + genre.getText() + "," + rating.getText());
            FileWriter writer = new FileWriter(file,true);
            
            writer.write("\n"+ newContent);
            writer.close();
            reader.close();

            //clears the textfields on the right side of pane
            nameField.clear();
            durationField.clear();
            rating.clear();
            genre.clear();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    @FXML
    private void onActionSearch() {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("Search.fxml"));
            Scene scene = new Scene(root);
            Stage thirdStage = new Stage();
            thirdStage.setScene(scene);
            thirdStage.initModality(Modality.APPLICATION_MODAL);  // Use this so you have to close the 2nd window to return to main window
            thirdStage.showAndWait();

            //clears the textfields on the right side of pane
            nameField.clear();
            durationField.clear();
            rating.clear();
            genre.clear();

        } catch(IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    private void updatedata() {
        //Adds the data from file into listview
        if (movieList != null) {
            MovieView.getItems().addAll(movieList.MovieData());
        }
    }

 
}
