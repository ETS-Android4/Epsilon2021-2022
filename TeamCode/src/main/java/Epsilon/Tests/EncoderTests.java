package Epsilon.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import Epsilon.OurRobot;
//import Epsilon.Subsystems.Odometry;

@TeleOp
public class EncoderTests extends LinearOpMode{

    @Override
    public void runOpMode() throws InterruptedException{

        OurRobot robot = new OurRobot();    //creates instance of "OurRobot," giving it access to hardware/methods
        robot.initialize(this);
        robot.drivetrain.frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        robot.drivetrain.backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        robot.drivetrain.frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        robot.drivetrain.backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        waitForStart();
        while (opModeIsActive()){
            //robot.drivetrain.Move(0,10);
            //telemetry.addData("EncoderX Position", robot.Odometry.xPos);

            telemetry.addData("frontLeft Position (TICKS)", robot.drivetrain.frontLeft.getCurrentPosition());
            telemetry.addData("frontLeft Position (INCH)", robot.Odometry.encoderToInch(robot.drivetrain.frontLeft.getCurrentPosition()));
            telemetry.addData("yPos", robot.Odometry.yPos);
            telemetry.addData("Y change", OurRobot.Odometry.YChange);

            telemetry.addData("backLeft Position (TICKS)", robot.drivetrain.backLeft.getCurrentPosition());
            telemetry.addData("backLeft Position (INCH)", robot.Odometry.encoderToInch(robot.drivetrain.backLeft.getCurrentPosition()));
            telemetry.addData("xPos", robot.Odometry.xPos);
            telemetry.addData("X change", OurRobot.Odometry.XChange);

            //telemetry.addData("IMU HEADING", robot.Odometry.heading);
            //imu heading is always zero bruh


            //telemetry.addData("EncoderX change", OurRobot.Odometry.XChange);
            robot.Odometry.update();
            telemetry.update();


        }
    }
}
