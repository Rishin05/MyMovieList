import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MyMovieList.fxml"));
        
        Scene scene = new Scene(root);  // Create scene containing the FXML root node
        stage.setScene(scene);  // Place the scene in the stage
        stage.show();
        
    }
    public static void main(String[] args) {
        launch(args);//launches the fxml
    }

}