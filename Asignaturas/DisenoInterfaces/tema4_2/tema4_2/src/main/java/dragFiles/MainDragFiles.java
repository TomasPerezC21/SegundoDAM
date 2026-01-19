package dragFiles;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainDragFiles extends Application {

    private AnchorPane rootLayout;

    @Override
    public void start(Stage primaryStage){

        try{

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainDragFiles.class.getResource("dragFiles.fxml"));
            rootLayout = (AnchorPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Ejemplo sobre drag and drop");
            primaryStage.show();


        }catch(Exception e){
            e.printStackTrace();
        }


    }



}
