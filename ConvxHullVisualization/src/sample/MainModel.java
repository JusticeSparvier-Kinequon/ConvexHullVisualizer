package sample;

import java.util.ArrayList;

public class MainModel {
    Vertex singleVertex;
    ArrayList<ModelListener> subscribers;

    public MainModel(){
        this.subscribers = new ArrayList<>();
        this.singleVertex = new Vertex(100, 100, 25,25);
    }

    public Vertex getSingleVertex(){
        return this.singleVertex;
    }

    public boolean checkHit(double x, double y){
        return singleVertex.checkHit(x, y);
    }

    public void moveVertex(double dX, double dY){
        this.singleVertex.move(dX,dY);
        notifySubscribers();
    }

    public void addSubscriber(ModelListener newSub){
        this.subscribers.add(newSub);
    }

    private void notifySubscribers(){
        this.subscribers.forEach(sub -> sub.modelChanged());
    }
}
