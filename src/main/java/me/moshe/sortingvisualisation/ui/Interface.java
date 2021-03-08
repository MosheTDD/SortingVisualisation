package me.moshe.sortingvisualisation.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import me.border.utilities.utils.URLUtils;

import java.io.IOException;

public class Interface extends Application {
    private Stage window;

    public void start(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.window = primaryStage;
        openMainMenu();
    }

    private void openMainMenu() throws IOException {
        window.setTitle("Sorting Visualisation");
        window.setResizable(false);
        FXMLLoader loader = new FXMLLoader(URLUtils.getURL("/view/SortingVisualisation.fxml"));
        Parent root = loader.load();
        window.setScene(new Scene(root, 1280, 720));
        window.show();
    }
}
