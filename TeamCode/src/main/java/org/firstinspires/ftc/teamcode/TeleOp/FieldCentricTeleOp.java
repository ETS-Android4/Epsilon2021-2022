package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import Epsilon.OurRobot;
import Epsilon.Superclasses.EpsilonRobot;

@TeleOp
public class FieldCentricTeleOp extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException{

        OurRobot robot = new OurRobot();    //creates instance of "OurRobot," giving it access to hardware/methods
        robot.initialize(this);

        waitForStart();

        while (opModeIsActive()){
            if(gamepad1.dpad_up) {
                robot.imu.setZero(0);
            }
            if(gamepad1.dpad_right) {
                robot.imu.setZero(90);
            }
            if(gamepad1.dpad_down) {
                robot.imu.setZero(180);
            }
            if(gamepad1.dpad_left) {
                robot.imu.setZero(270);
            }

            robot.imu.update();
            double angle = -robot.imu.angle();

            telemetry.addData("zero", robot.imu.zero());
            telemetry.addData("angle", angle);
            telemetry.update();

            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x;
            double r = gamepad1.right_stick_x;

            double rot_x = x * Math.cos(Math.toRadians(angle)) - y * Math.sin(Math.toRadians(angle));
            double rot_y = x * Math.sin(Math.toRadians(angle)) + y * Math.cos(Math.toRadians(angle));

            robot.drivetrain.frontLeft.setPower(rot_y+r+rot_x);
            robot.drivetrain.frontRight.setPower(rot_y-r-rot_x);
            robot.drivetrain.backLeft.setPower(rot_y+r-rot_x);
            robot.drivetrain.backRight.setPower(rot_y-r+rot_x);
        }
    }
}
