package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import java.io.File;

@TeleOp(name="Podracer TeleOp")
public class PodracerTeleOp extends OpMode {
    boolean shouldSaveConstants = true;
    boolean rightStickSteers = true;
    File constantsFile = new File("podracerConstants.txt");
    double leftModifier;
    double rightModifier;


    PodracerConstants constants;
    Drivetrain drivetrain;

    public void init() {
        constants = new PodracerConstants(constantsFile);
        drivetrain = new Drivetrain(hardwareMap);

        leftModifier = Double.parseDouble(constants.getPodracerConstant("leftModifier"));
        rightModifier = Double.parseDouble(constants.getPodracerConstant("rightModifier"));
    }

    /* Telemetry:
    Constants will save when OpMode is stopped

    Hold LB or RB and move right stick up or down to change wheel modifiers
    Click either stick to swap drive and steer sticks
    Hold LB and RB and press X to disable constant saving

    -- Constants --
    leftModifier:404
    rightModifier:404

    -- Controls --
    Drive Stick:
    Steer Stick:

     */

    public void loop() {
        double steer;
        double drive;

        // Bunch of if statements
        if (gamepad1.rightStickButtonWasPressed() || gamepad1.leftStickButtonWasPressed())
            rightStickSteers = !rightStickSteers;

        // Figure out which stick to steer & drive with
        if (rightStickSteers) {
            steer = gamepad1.right_stick_x;
            drive = -gamepad1.left_stick_y;
        } else {
            steer = gamepad1.left_stick_x;
            drive = -gamepad1.right_stick_y;
        }

        // Constant modification
        if (gamepad1.right_bumper)
            rightModifier -= gamepad1.right_stick_y*0.1;
        if (gamepad1.left_bumper)
            leftModifier -= gamepad1.left_stick_y*0.1;

        if (gamepad1.left_bumper && gamepad1.right_bumper && gamepad1.xWasPressed())
            shouldSaveConstants = !shouldSaveConstants;

        // Actual drive stuff
        drivetrain.setWheelPower(
                drive + steer * leftModifier,
                drive - steer * rightModifier
        );

        // Telemetry Stuff
        if (shouldSaveConstants)
            telemetry.addLine("Constants will save when OpMode is stopped");
        else
            telemetry.addLine("Constants will NOT save when OpMode is stopped");

        telemetry.addLine("\nHold LB or RB and move right stick up or down to change wheel modifiers");
        telemetry.addLine("Click either stick to swap drive and steer sticks");
        telemetry.addLine("Hold LB and RB and press X to disable saving of constants");

        telemetry.addLine("\n-- Constants --");
        telemetry.addData("leftModifier",leftModifier);
        telemetry.addData("rightModifier",rightModifier);

        telemetry.addLine("\n-- Controls --");
        if (rightStickSteers)
            telemetry.addLine("Steer Stick: Right Stick\nDrive Stick: Left Stick");
        else
            telemetry.addLine("Steer Stick: Left\nDrive Stick: Right Stick");
    }

    public void stop() {
        if (!shouldSaveConstants) return;

        constants.setPodracerConstant("leftModifier",String.valueOf(leftModifier));
        constants.setPodracerConstant("rightModifier",String.valueOf(rightModifier));

        constants.saveConstants();
    }
}
