package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class MainView extends Pane implements ModelListener {
    Canvas myCanvas;
    GraphicsContext gc;
    MainModel model;
    MainController controller;

    public MainView (int newWidth, int newHeight){
        this.myCanvas = new Canvas(newWidth, newHeight);
        this.gc = this.myCanvas.getGraphicsContext2D();
        gc.setFill(Color.GRAY);
        gc.fillRect(0,0,newWidth,newHeight);
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
        this.gc.fillRect(0,0,myCanvas.getWidth(), myCanvas.getHeight());
        for (Vertex v : model.getAllVertices()){
            if (v.isConvex){
                gc.setFill(Color.ORANGE);
                gc.fillOval(v.x, v.y, v.width, v.height);
            }
            else {
                gc.setFill(Color.BLUE);
                gc.fillOval(v.x, v.y, v.width, v.height);
            }
            gc.setStroke(Color.BLACK);
            gc.strokeOval(v.x, v.y, v.width, v.height);
        }
    }

    public void layoutChildren(){
        this.myCanvas.setWidth(this.getWidth());
        this.myCanvas.setHeight(this.getHeight());
        draw();
    }

    public void modelChanged(){
        draw();
    }
}
