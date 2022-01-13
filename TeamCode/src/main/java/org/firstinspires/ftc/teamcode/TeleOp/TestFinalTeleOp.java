package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import Epsilon.OurRobot;
import Epsilon.Subsystems.Outtake;
import Epsilon.Superclasses.EpsilonRobot;

@TeleOp
public class TestFinalTeleOp extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException{

        OurRobot robot = new OurRobot();    //creates instance of "OurRobot," giving it access to hardware/methods
        robot.initialize(this);

        double verticalPosition = OurRobot.outtake.top.getCurrentPosition();

        waitForStart();

        double speed = 0.7;

        while (opModeIsActive()){

            double y = gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x;
            double r = gamepad1.right_stick_x;

            //boolean intake = gamepad1.a;
            double outY = 90;
            //gamepad1.left_trigger;

            OurRobot.drivetrain.frontLeft.setPower(speed*(y-r-x));
            OurRobot.drivetrain.frontRight.setPower(speed*(y+r+x));
            OurRobot.drivetrain.backLeft.setPower(speed*(y-r+x));
            OurRobot.drivetrain.backRight.setPower(speed*(y+r-x));

            if (gamepad1.right_trigger>0)           //slowmode
                speed = 0.3;
            else if (gamepad1.left_trigger>0)       //fastmode
                speed = 1.0;
            else
                speed = 0.7;

            OurRobot.outtake.top.setPower(gamepad2.left_stick_y);
            double wheelSpeed = 1.0;
            if(gamepad1.right_bumper)
                wheelSpeed = 0.5;

            if(gamepad1.a)
                OurRobot.intake.wheel.setPower(-wheelSpeed);
            else if (gamepad1.b)
                OurRobot.intake.wheel.setPower(wheelSpeed);
            else
                OurRobot.intake.wheel.setPower(0.0);

            if(gamepad2.dpad_up) {
                OurRobot.outtake.top.setPower(0.85);
                verticalPosition = OurRobot.outtake.top.getCurrentPosition();
            } else if(gamepad2.dpad_down) {
                OurRobot.outtake.top.setPower(-0.3);
                verticalPosition = OurRobot.outtake.top.getCurrentPosition();
            } else OurRobot.outtake.top.setPower(OurRobot.outtake.PID(verticalPosition));

            if (gamepad2.x)
                OurRobot.outtake.scoreASH(Outtake.PosASH.TOP);
            else if (gamepad2.b)
                OurRobot.outtake.scoreASH(Outtake.PosASH.MID);
            else if (gamepad2.a)
                OurRobot.outtake.scoreASH(Outtake.PosASH.BOTTOM);

            if (gamepad2.dpad_left && OurRobot.outtake.top.getCurrentPosition() > 300) {
                OurRobot.outtake.bottom.setPosition(0.6);//PLACEHOLDER VALUE
            } else if (gamepad2.dpad_right) {
                OurRobot.outtake.bottom.setPosition(0);//PLACEHOLDER VALUE
            }

            if(gamepad2.y)
                OurRobot.outtake.door();

            telemetry.addData("Horizontal",OurRobot.outtake.bottom.getPosition());
            telemetry.addData("Vertical",OurRobot.outtake.top.getCurrentPosition());
            telemetry.update();


          /*  OurRobot.Odometry.update();
            telemetry.addData("XPosition", OurRobot.Odometry.xPos);
            telemetry.addData("YPosition", OurRobot.Odometry.yPos);
            telemetry.addData("Angle", Math.toDegrees(OurRobot.Odometry.heading));
            telemetry.update(); */
        }
    }
}
