package edu.metrostate;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;  // Use VBox instead of StackPane
import javafx.stage.Stage;
import edu.metrostate.views.NotificationView;
import edu.metrostate.controllers.NotificationController;
import edu.metrostate.models.Notification;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        NotificationView notificationView = new NotificationView();
        NotificationController notificationController = new NotificationController(new Notification(), notificationView);

        // Get the VBox root pane from NotificationView
        VBox root = notificationView.getRootPane();  // Correctly using VBox here
        Scene scene = new Scene(root, 800, 600);

        primaryStage.setTitle("Appliance Management System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
