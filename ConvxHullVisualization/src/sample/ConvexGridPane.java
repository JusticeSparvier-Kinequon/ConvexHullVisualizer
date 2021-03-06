package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class ConvexGridPane extends Pane {

    Canvas mainCanvas;
    GraphicsContext gc;
    double size;

    /*
     * Constructor for ConvexGridPane
     * Creates a canvas with it's width and height and fills it with a gray rectangle.
     * Then adds the canvas as the ConvexGridPane's children
     */

    public ConvexGridPane(double width, double height){
        mainCanvas = new Canvas(width,height);
        gc = mainCanvas.getGraphicsContext2D();
        gc.setFill(Color.GRAY);
        gc.fillRect(0,0,width,height);
        this.getChildren().addAll(mainCanvas);
    }

    /*
     * Redraws the canvas to the height
     */
    private void draw(){
        gc.setFill(Color.GRAY);
        gc.fillRect(0,0,mainCanvas.getWidth(), mainCanvas.getHeight());

    }
    protected void layoutChildren(){
        mainCanvas.setWidth(this.getWidth());
        mainCanvas.setHeight(this.getHeight());
        draw();
    }
}
