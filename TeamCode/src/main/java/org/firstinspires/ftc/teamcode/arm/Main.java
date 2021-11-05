package org.firstinspires.ftc.teamcode.arm;

public class Main {
    public static Arm arm;
    public static void main(String[] args) {
        arm = new Arm(2, 100);

        //set a new target position
        arm.setTarget(new Vector(100,0));

        for(int i = 0; i < 100; i++){
            arm.update();
            arm.setTarget(new Vector(100+(i*0.1),0));
        }
    }
}
