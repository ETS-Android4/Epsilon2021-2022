package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import Epsilon.OurRobot;
import Epsilon.Subsystems.Outtake;
import Epsilon.Superclasses.EpsilonRobot;

@TeleOp
public class FieldCentricTeleOp extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException{

        OurRobot robot = new OurRobot();    //creates instance of "OurRobot," giving it access to hardware/methods
        robot.initialize(this);

        //robot.imu.setZero(-robot.imu.angle());
        double speed = 0.7;
        double outY = 90;

        waitForStart();

        while (opModeIsActive()){

            /*
            set angle before
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
                robot.imu.setZero(-90);
            }
            */

            //robot.imu.update();
            double angle = OurRobot.imu.imu.getAngularOrientation().firstAngle;

            double verticalPosition = OurRobot.outtake.top.getCurrentPosition();

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
                verticalPosition = OurRobot.outtake.top.getCurrentPosition();
            }
        }
    }
}
