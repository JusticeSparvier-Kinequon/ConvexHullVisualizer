package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    MainView view;
    MainController controller;
    MainModel model;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        StackPane root = new StackPane();
        view = new MainView(1000,600);
        controller = new MainController();
        model = new MainModel();

        view.setController(controller);
        view.setModel(model);
        controller.setModel(model);
        model.addSubscriber(view);

        root.getChildren().add(view);
        primaryStage.setTitle("Convex Hull Visualizer");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
