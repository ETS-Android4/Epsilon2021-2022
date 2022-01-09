package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import Epsilon.OurRobot;
import Epsilon.Superclasses.EpsilonRobot;

@TeleOp
public class TestFinalTeleOp extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException{

        OurRobot robot = new OurRobot();    //creates instance of "OurRobot," giving it access to hardware/methods
        robot.initialize(this);

        waitForStart();

        while (opModeIsActive()){
            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x;
            double r = gamepad1.right_stick_x;

            boolean intake = gamepad2.a;
            double outX = 5;
            double outY = 90;

            OurRobot.drivetrain.frontLeft.setPower(y+r+x);
            OurRobot.drivetrain.frontRight.setPower(y-r-x);
            OurRobot.drivetrain.backLeft.setPower(y+r-x);
            OurRobot.drivetrain.backRight.setPower(y-r+x);

            if(intake) OurRobot.intake.eat(1,1);

            OurRobot.outtake.top.setPower(outY);
            OurRobot.outtake.bottom.setPosition(outX);

            OurRobot.Odometry.update();
            telemetry.addData("XPosition", OurRobot.Odometry.xPos);
            telemetry.addData("YPosition", OurRobot.Odometry.yPos);
            telemetry.addData("Angle", Math.toDegrees(OurRobot.Odometry.heading));
            telemetry.update();
        }
    }
}
