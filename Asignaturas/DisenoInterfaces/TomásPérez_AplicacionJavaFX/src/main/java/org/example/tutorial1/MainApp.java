package org.example.tutorial1;

import java.io.IOException;
import java.time.LocalDate;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    /**
     * Datos como una lista observable de Personas.
     */
    private ObservableList<Person> personData = FXCollections.observableArrayList();

    public MainApp() {
        // Añadimos datos con meses variados para que el gráfico tenga barras
        Person p1 = new Person("Hans", "Muster");
        p1.setBirthday(LocalDate.of(1990, 1, 15)); // Enero
        personData.add(p1);

        Person p2 = new Person("Ruth", "Mueller");
        p2.setBirthday(LocalDate.of(1995, 3, 20)); // Marzo
        personData.add(p2);

        Person p3 = new Person("Heinz", "Kurz");
        p3.setBirthday(LocalDate.of(2000, 5, 5)); // Mayo
        personData.add(p3);

        Person p4 = new Person("Álvaro", "Guy");
        p4.setBirthday(LocalDate.of(1990, 2, 15)); // Febrero
        personData.add(p4);

        Person p5 = new Person("Tomás", "Pérez");
        p5.setBirthday(LocalDate.of(1995, 4, 20)); // abril
        personData.add(p5);

        Person p6 = new Person("Alejandro", "Sandoval");
        p6.setBirthday(LocalDate.of(2000, 7, 5)); // julio
        personData.add(p6);

        Person p7 = new Person("Jose", "Paco");
        p7.setBirthday(LocalDate.of(1990, 8, 15)); // agosto
        personData.add(p7);

        Person p8 = new Person("Borja", "Pérez");
        p8.setBirthday(LocalDate.of(1995, 12, 20)); // diciembre
        personData.add(p8);

        Person p9 = new Person("Alejandro", "Fernandez");
        p9.setBirthday(LocalDate.of(2000, 3, 5)); // Marzo
        personData.add(p9);

        Person p10 = new Person("Paco", "Fernandez");
        p10.setBirthday(LocalDate.of(2000, 11, 5)); // noviembre
        personData.add(p10);

    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Tomás Pérez");

        this.primaryStage.getIcons().add(
                new Image(getClass().getResourceAsStream("/images/location_2149438.png"))
        );

        initRootLayout();
        showPersonOverview();
    }

    /**
     * Inicializa el root layout.
     */
    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("rootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            // Esto conecta el menú con la lógica
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Muestra la vista de estadísticas de cumpleaños.
     * Este es el método nuevo requerido por el ejercicio.
     */
    public void showBirthdayStatistics() {
        try {
            // Cargar el archivo fxml y crear un nuevo stage para el diálogo.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("BirthdayStatistics.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Birthday Statistics");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Pasar los datos de las personas al controlador.
            BirthdayStatisticsController controller = loader.getController();
            controller.setPersonData(personData);

            dialogStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Abre un diálogo para editar los detalles de la persona especificada.
     */
    public boolean showPersonEditDialog(Person person) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("PersonEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            PersonEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(person);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Muestra la vista general de personas dentro del root layout.
     */
    public void showPersonOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("PersonOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            rootLayout.setCenter(personOverview);

            PersonOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public ObservableList<Person> getPersonData() {
        return personData;
    }
}