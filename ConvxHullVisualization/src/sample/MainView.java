package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class MainView extends Pane implements ModelListener {
    Canvas myCanvas;
    GraphicsContext gc;
    double width, height;
    MainModel model;
    MainController controller;

    public MainView (int newWidth, int newHeight){
        this.width = newWidth;
        this.height = newHeight;
        this.myCanvas = new Canvas(this.width, this.height);
        this.gc = this.myCanvas.getGraphicsContext2D();
        getChildren().add(this.myCanvas);
    }

    public void setModel(MainModel newModel){
        this.model = newModel;
    }

    public void setController(MainController newController){
        this.controller = newController;
        this.myCanvas.setOnMousePressed(controller::handlePressed);
        this.myCanvas.setOnMouseDragged(controller::handleDrag);
        this.myCanvas.setOnMouseReleased(controller::handleRelease);
    }

    public void draw() {
        this.gc.setFill(Color.GRAY);
        this.gc.fillRect(0,0,this.width, this.height);
        Vertex v = model.getSingleVertex();
        gc.setFill(Color.BLUE);
        gc.fillOval(v.x, v.y, v.width, v.height);
    }

    public void layoutChildren(){
        this.width = this.getWidth();
        this.height = this.getHeight();
        this.myCanvas.setWidth(width);
        this.myCanvas.setHeight(height);
        draw();
    }

    public void modelChanged(){
        draw();
    }
}
