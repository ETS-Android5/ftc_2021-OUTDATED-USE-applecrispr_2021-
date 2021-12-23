package org.firstinspires.ftc;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class hardwarePushbot {

    public DcMotor frontLeft;
    public DcMotor backLeft;
    public DcMotor frontRight;
    public DcMotor backRight;

    HardwareMap hwMap = null;

    public hardwarePushbot () {

    }

    public void init (HardwareMap ahwMap) {

        hwMap = ahwMap;

        frontLeft = hwMap.get(DcMotor.class, "leftFront");
        backLeft = hwMap.get(DcMotor.class, "leftRear");
        frontRight = hwMap.get(DcMotor.class, "rightFront");
        backRight = hwMap.get(DcMotor.class, "rightRear");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        backRight.setDirection(DcMotorSimple.Direction.FORWARD);


    }

}
