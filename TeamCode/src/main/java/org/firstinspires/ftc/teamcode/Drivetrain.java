package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Drivetrain {
    HardwareMap hardwareMap;

    DcMotor leftMotor;
    DcMotor rightMotor;

    double rightMotorModifier = 1;
    double leftMotorModifier = 1;

    Drivetrain(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;

        leftMotor = hardwareMap.get(DcMotor.class, "LeftMotor");
        rightMotor = hardwareMap.get(DcMotor.class, "RightMotor");
    }

    void setWheelPower(double leftWheel, double rightWheel) {
        leftMotor.setPower(leftWheel * rightMotorModifier);
        rightMotor.setPower(rightWheel * rightMotorModifier);
    }


}
