package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

public class MainView extends Pane implements ModelListener {
    Canvas myCanvas;
    GraphicsContext gc;
    double width, height;
    MainModel model;
    MainController controller;

    public MainView (int newWidth, int newHeight){
        this.width = newWidth;
        this.height = newHeight;
        myCanvas = new Canvas(this.width, this.height);
        gc = myCanvas.getGraphicsContext2D();
        getChildren().add(myCanvas);
    }

    public void setModel(MainModel newModel){
        this.model = newModel;
    }

    public void setController(MainController newController){
        this.controller = newController;
        myCanvas.setOnMousePressed(controller::handlePressed);
        myCanvas.setOnMouseDragged(controller::handleDrag);
        myCanvas.setOnMouseReleased(controller::handleRelease);
    }

    public void draw() {
        gc.clearR
    }
    public void modelChanged(){

    }
}
