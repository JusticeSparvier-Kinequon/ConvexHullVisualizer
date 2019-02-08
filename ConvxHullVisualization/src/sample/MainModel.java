package sample;

import java.util.ArrayList;
import java.util.function.Predicate;

public class MainModel {
    ArrayList<Vertex> allVertices;
    Vertex singleVertex;
    ArrayList<ModelListener> subscribers;

    public MainModel(){
        this.subscribers = new ArrayList<>();
        this.allVertices = new ArrayList<>();
    }

    public ArrayList<Vertex> getAllVertices(){
        return this.allVertices;
    }

    public void addVertex(double x, double y){
        this.allVertices.add(new Vertex(x, y));
        notifySubscribers();
    }

    public boolean checkHit(double x, double y){
        Predicate<Vertex> p1 = v -> v.checkHit(x, y);
        boolean result = allVertices.stream().anyMatch(p1);
        if(result){
            for (Vertex v : allVertices){
                if(v.checkHit(x, y))
                    singleVertex = v;
            }
        }
        return result;
    }


    public void moveVertex(double dX, double dY){
            singleVertex.move(dX, dY);
            notifySubscribers();
    }

    public void addSubscriber(ModelListener newSub){
        this.subscribers.add(newSub);
    }

    private void notifySubscribers(){
        this.subscribers.forEach(sub -> sub.modelChanged());
    }
}
