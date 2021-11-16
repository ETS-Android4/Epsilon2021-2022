package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import Epsilon.OurRobot;
import Epsilon.Superclasses.EpsilonRobot;

public class FieldCentricTeleOp extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException{

        waitForStart();
        OurRobot robot = new OurRobot();    //creates instance of "OurRobot," giving it access to hardware/methods
        robot.initialize(this);

        while (opModeIsActive()){
            robot.imu.update();
            double angle = robot.imu.angle;

            double y = -gamepad1.left_stick_y * Math.cos(angle);
            double x = gamepad1.left_stick_x * Math.sin(angle);
            double r = gamepad1.right_stick_x;

            robot.drivetrain.frontLeft.setPower(y+r+x);
            robot.drivetrain.frontRight.setPower(y-r-x);
            robot.drivetrain.backLeft.setPower(y+r-x);
            robot.drivetrain.backRight.setPower(y-r+x);
        }
    }
}
