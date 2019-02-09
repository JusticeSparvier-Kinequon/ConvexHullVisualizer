package sample;

public class Vertex implements Comparable<Vertex> {
    double x, y;
    double width, height;
    boolean isConvex;

    public Vertex(double newX, double newY){
        this.x = newX;
        this.y = newY;
        this.width = 25;
        this.height = 25;
        this.isConvex = false;
    }

    public boolean checkHit(double clickX, double clickY){
        return clickX >= this.x && clickX <= this.x+this.width && clickY >= this.y && clickY <= this.y+this.height;
    }

    public void move(double dX, double dY){
        x += dX;
        y += dY;
    }

    public void setisConvex(boolean value){
        this.isConvex = value;
    }
    @Override
    public int compareTo(Vertex compareVertex){
        double compareX = compareVertex.x;
        double compareY = compareVertex.x;
        if(this.x - compareX == 0)
            if (this.y - compareY < 0)
                return -1;
            else if (this.y - compareY > 0)
                return 1;
            else
                return 0;
        else if (this.x - compareX < 0)
            return -1;
        else if (this.x - compareX > 0)
            return 1;
        else
            return 0;
    }
}
