package sample;

public class Vertex {
    double x, y;
    double width, height;

    public Vertex(double newX, double newY){
        this.x = newX;
        this.y = newY;
        this.width = 25;
        this.height = 25;
    }

    public boolean checkHit(double clickX, double clickY){
        return clickX >= this.x && clickX <= this.x+this.width && clickY >= this.y && clickY <= this.y+this.height;
    }

    public void move(double dX, double dY){
        x += dX;
        y += dY;
    }
}
