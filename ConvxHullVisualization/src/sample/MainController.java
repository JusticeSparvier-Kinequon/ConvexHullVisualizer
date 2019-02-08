package sample;

import javafx.scene.input.MouseEvent;

public class MainController {
    MainModel model;
    double prevX, prevY;

    private enum State {
        READY, DRAGGING
    }

    private State currentState;

    public MainController(){
        this.currentState = State.READY;
    }

    public void setModel(MainModel newModel){
        this.model = newModel;
    }

    public void handlePressed(MouseEvent event){
        switch(this.currentState){
            case READY:
                boolean hit = model.checkHit(event.getX(), event.getY());
                if (hit){
                    this.prevX = event.getX();
                    this.prevY = event.getY();
                    this.currentState = State.DRAGGING;
                }
                break;
        }
    }

    public void handleDrag(MouseEvent event){
        switch(this.currentState){
            case DRAGGING:
                double dX = event.getX() - prevX;
                double dY = event.getY() - prevY;
                this.prevX = event.getX();
                this.prevY = event.getY();
                this.model.moveVertex(dX, dY);
        }
    }

    public void handleRelease(MouseEvent event){
        this.currentState = State.READY;
    }
}
