package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import Epsilon.OurRobot;
import Epsilon.Superclasses.EpsilonRobot;

@TeleOp
public class testTeleOp extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException{

        waitForStart();
        OurRobot robot = new OurRobot();    //creates instance of "OurRobot," giving it access to hardware/methods
        robot.initialize(this);

        while (opModeIsActive()){
           double y = -gamepad1.left_stick_y;
           double x = gamepad1.left_stick_x;
           double r = gamepad1.right_stick_x;

           robot.drivetrain.frontLeft.setPower(y+r);
           robot.drivetrain.frontRight.setPower(y-r);
           robot.drivetrain.backLeft.setPower(y+r);
           robot.drivetrain.backRight.setPower(y-r);
        }
    }
}
