package sample;

import java.util.ArrayList;
import java.util.function.Predicate;

public class MainModel {
    ArrayList<Vertex> allVertices;
    Vertex singleVertex;
    ArrayList<ModelListener> subscribers;
    ConvexHullGrahamScan convexHull;
    ArrayList<Vertex> allConvexPoints;

    public MainModel(){
        this.subscribers = new ArrayList<>();
        this.allVertices = new ArrayList<>();
    }

    public ArrayList<Vertex> getAllVertices(){
        return this.allVertices;
    }

    public ArrayList<Vertex> getAllConvexPoints(){ return this.allConvexPoints; }

    public void addVertex(double x, double y){
        this.allVertices.add(new Vertex(x, y));
        if(allVertices.size() >= 3){
            for (Vertex v2 : allVertices){
                v2.setisConvex(false);
            }

            convexHull = new ConvexHullGrahamScan();
            allConvexPoints = convexHull.makeHull(allVertices);

            for(Vertex v1 : allConvexPoints){
                for (Vertex v2 : allVertices){
                    if(v1 == v2){
                        v2.setisConvex(true);
                    }
                }
            }
        }
        notifySubscribers();
    }

    public void removeVertex(double x, double y){
        this.allVertices.remove(singleVertex);
        if(allVertices.size() >= 3){
            for (Vertex v2 : allVertices){
                v2.setisConvex(false);
            }

            convexHull = new ConvexHullGrahamScan();
            allConvexPoints = convexHull.makeHull(allVertices);
            for(Vertex v1 : allConvexPoints){
                for (Vertex v2 : allVertices){
                    if(v1 == v2){
                        v2.setisConvex(true);
                    }
                }
            }
        }
        else{
            for (Vertex v2 : allVertices){
                v2.setisConvex(false);
            }
        }
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
        if(allVertices.size() >= 3){
            for (Vertex v2 : allVertices){
                v2.setisConvex(false);
            }

            convexHull = new ConvexHullGrahamScan();
            allConvexPoints = convexHull.makeHull(allVertices);
            for(Vertex v1 : allConvexPoints){
                for (Vertex v2 : allVertices){
                    if(v1 == v2){
                        v2.setisConvex(true);
                    }
                }
            }
        }
        notifySubscribers();
    }

    public void addSubscriber(ModelListener newSub){
        this.subscribers.add(newSub);
    }

    private void notifySubscribers(){
        this.subscribers.forEach(sub -> sub.modelChanged());
    }
}
