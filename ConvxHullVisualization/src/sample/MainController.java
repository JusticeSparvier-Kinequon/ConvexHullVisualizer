package sample;

import javafx.scene.input.MouseEvent;

public class MainController {
    MainModel model;
    double prevX, prevY;

    private enum State {
        READY, DRAGGING, VERTEX_PREPARE, BACKGROUND_DRAG, VERTEX_REMOVE
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
                    if(event.isSecondaryButtonDown())
                        this.currentState = State.VERTEX_REMOVE;
                }
                else {
                    this.currentState = State.VERTEX_PREPARE;
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
                break;
            case VERTEX_PREPARE:
                this.currentState = State.BACKGROUND_DRAG;
                break;
            case VERTEX_REMOVE:
                this.currentState = State.BACKGROUND_DRAG;
                break;
            case BACKGROUND_DRAG:
                break;
        }
    }

    public void handleRelease(MouseEvent event){
        switch(this.currentState){
            case VERTEX_PREPARE:
                model.addVertex(event.getX(), event.getY());
                this.currentState = State.READY;
                break;
            case VERTEX_REMOVE:
                model.removeVertex(event.getX(), event.getY());
        }
        this.currentState = State.READY;
    }

}
