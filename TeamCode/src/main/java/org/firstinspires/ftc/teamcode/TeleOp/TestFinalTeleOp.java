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

        waitForStart();

        double speed = 0.7;

        while (opModeIsActive()){
            double y = gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x;
            double r = gamepad1.right_stick_x;

            //boolean intake = gamepad1.a;
            double outY = 90;

            double verticalPosition = OurRobot.outtake.top.getCurrentPosition();

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
            if(gamepad1.a)
                OurRobot.intake.wheel.setPower(-1);
            else if (gamepad1.b)
                OurRobot.intake.wheel.setPower(1);
            else
                OurRobot.intake.wheel.setPower(0.0);

            if (gamepad2.dpad_up)
                OurRobot.outtake.vertical(0.7,0.5);
            else if (gamepad2.dpad_down)
                OurRobot.outtake.vertical(-0.7,0.5);
            else if (gamepad2.dpad_left)
                OurRobot.outtake.horizontal(outY);

            if (gamepad2.x)
                OurRobot.outtake.scoreASH(Outtake.PosASH.TOP);
            else if (gamepad2.b)
                OurRobot.outtake.scoreASH(Outtake.PosASH.MID);
            else if (gamepad2.a)
                OurRobot.outtake.scoreASH(Outtake.PosASH.BOTTOM);
            else{
                OurRobot.outtake.top.setPower(OurRobot.outtake.PID(verticalPosition));
            }

          /*  OurRobot.Odometry.update();
            telemetry.addData("XPosition", OurRobot.Odometry.xPos);
            telemetry.addData("YPosition", OurRobot.Odometry.yPos);
            telemetry.addData("Angle", Math.toDegrees(OurRobot.Odometry.heading));
            telemetry.update(); */
        }
    }
}
