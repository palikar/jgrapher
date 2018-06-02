package dev.arnaud;

import dev.arnaud.services.MainModule;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.ArrayList;

public class Main extends Application {

  @Override
  public void start(Stage stage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("MainController.fxml"));
    Scene scene = new Scene(root);
    stage.setTitle("JGrapher");
    stage.setMaximized(false);
    stage.setResizable(false);
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);

    System.out.println("done!");

  }
}
