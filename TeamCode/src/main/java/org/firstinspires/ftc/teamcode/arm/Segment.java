package org.firstinspires.ftc.teamcode.arm;

public class Segment {
    public Vector a = new Vector();
    public Vector b = new Vector();
    public double length;
    public double angle = 0;
    public double showAngle = 0;
    public Segment child;
    public Segment(double length){

        //set length
        this.length = length;

    }
    public void follow(Vector target){
        Vector dir = Vector.sub(target, a);
        angle = dir.heading();

        dir.setMagnitude(length);
        dir.mult(-1);
        a = Vector.add(target, dir);
    }
    public void follow(){
        Vector target = child.a;
        follow(target);
    }

    public void calculateB() {
        double dx = length * Math.cos(angle);
        double dy = length * Math.sin(angle);
        b.set(a.x + dx, a.y + dy);
    }

    public void setA(Vector vector){
        a = vector;
        calculateB();
    }
    public void update(){
        calculateB();

        if(child != null){
            child.showAngle = child.angle-angle;
        }

        System.out.println(Math.round(radToDeg(showAngle)));
        showAngle = angle;
    }
    public double radToDeg(double radians)
    {
        double pi = Math.PI;
        return radians * (180/pi);
    }
}
