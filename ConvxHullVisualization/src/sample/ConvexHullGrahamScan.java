package sample;

import java.util.ArrayList;
import java.util.Collections;

public class ConvexHullGrahamScan {

    public ArrayList<Vertex> makeHull(ArrayList<Vertex> vertices){
        ArrayList<Vertex> newVertices = new ArrayList<>(vertices);
        Collections.sort(newVertices);
        return makeHullPresorted(newVertices);
    }

    public ArrayList<Vertex> makeHullPresorted(ArrayList<Vertex> vertices){
        ArrayList<Vertex> upperHull = new ArrayList<>();
        for (Vertex v : vertices) {
            while (upperHull.size() >= 2) {
                Vertex q = upperHull.get(upperHull.size() - 1);
                Vertex r = upperHull.get(upperHull.size() - 2);
                if ((q.x - r.x) * (v.y - r.y) >= (q.y - r.y) * (v.x - r.x))
                    upperHull.remove(upperHull.size() - 1);
                else
                    break;
            }
            upperHull.add(v);
        }
        upperHull.remove(upperHull.size() - 1);

        ArrayList<Vertex> lowerHull = new ArrayList<>();
        for (int i = vertices.size() - 1; i >= 0; i--) {
            Vertex v = vertices.get(i);
            while (lowerHull.size() >= 2) {
                Vertex q = lowerHull.get(lowerHull.size() - 1);
                Vertex r = lowerHull.get(lowerHull.size() - 2);
                if ((q.x - r.x) * (v.y - r.y) >= (q.y - r.y) * (v.x - r.x))
                    lowerHull.remove(lowerHull.size() - 1);
                else
                    break;
            }
            lowerHull.add(v);
        }
        lowerHull.remove(lowerHull.size() - 1);

        if (!(upperHull.size() == 1 && upperHull.equals(lowerHull)))
            upperHull.addAll(lowerHull);
        return upperHull;
    }
    public ConvexHullGrahamScan() {
    }

}
