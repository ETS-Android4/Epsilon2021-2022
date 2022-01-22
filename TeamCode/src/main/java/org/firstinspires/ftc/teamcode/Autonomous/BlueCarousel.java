package org.firstinspires.ftc.teamcode.Autonomous;

import android.view.Surface;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import Epsilon.OurRobot;
import Epsilon.Subsystems.Drivetrain;
import Epsilon.Subsystems.OpenCV;
import Epsilon.Subsystems.Outtake;

@Autonomous
public class BlueCarousel extends LinearOpMode {
    public void runOpMode() throws InterruptedException {

        int topPos = 100;

        OurRobot.initialize(this);
        OurRobot.outtake.upMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        OurRobot.outtake.upMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Outtake.PosASH scorePos = Outtake.PosASH.TOP;
        //scan
        while (!isStarted()) {
            scorePos = OpenCV.Pipeline.getAnalysis();
            telemetry.addData("OpenCV Pos: ", scorePos);
            telemetry.update();
        }
        waitForStart();
        telemetry.addData("Encoder Counts ", OurRobot.outtake.upMotor.getCurrentPosition());
        telemetry.update();
        //drive to ASH
        OurRobot.drivetrain.Move(0.5, -25, Drivetrain.MoveType.STRAFE, this);
        //align with wall???
        OurRobot.drivetrain.Move(0.5, -16, Drivetrain.MoveType.DRIVE, this);

        OurRobot.outtake.scoreASH(scorePos);
        OurRobot.outtake.upMotor.setPower(0);
        /*
        //score
        OurRobot.outtake.upMotor.setPower(OurRobot.outtake.PID(topPos));
        OurRobot.wait(1500, this);

        while (OurRobot.outtake.upMotor.isBusy()) {

        }

        OurRobot.outtake.arm.setPosition(0.6);
        OurRobot.wait(1000, this);
        OurRobot.outtake.door.setPosition(1);
        OurRobot.wait(1000, this);
        OurRobot.outtake.door.setPosition(0);
        OurRobot.wait(1000, this);
        OurRobot.outtake.arm.setPosition(0);
        OurRobot.wait(1000, this);
        topPos = 0;
        OurRobot.outtake.upMotor.setPower(OurRobot.outtake.PID(topPos));
        OurRobot.wait(1500, this);
         */
        /*
        while (OurRobot.outtake.upMotor.isBusy()) {

        }
        */
        //OurRobot.CycleFreight(this);

        //drive back, go to carousel
        OurRobot.drivetrain.Move(0.5, 7, Drivetrain.MoveType.DRIVE, this);
        //OurRobot.drivetrain.Move(0.5,-15, Drivetrain.MoveType.DRIVE);
        //OurRobot.imu.gyroTurn(0.7,0,this);

        //Strafe stuff down below
        OurRobot.drivetrain.Move(0.5, 30, Drivetrain.MoveType.STRAFE, this);
        OurRobot.imu.gyroTurn(0.7,0,this);
        OurRobot.drivetrain.Move(0.5, 30, Drivetrain.MoveType.STRAFE, this);
        OurRobot.drivetrain.Move(0.5,4, Drivetrain.MoveType.DRIVE,this);
        //Turn stuff down below
        //OurRobot.imu.gyroTurn(0.7, 90, this);
        //OurRobot.drivetrain.Move(0.5, -62, Drivetrain.MoveType.DRIVE, this);
        //OurRobot.imu.gyroTurn(0.7, -90, this);

        OurRobot.carousel.duckMotor.setPower(-0.5);
        OurRobot.wait(5000, this);
        OurRobot.carousel.duckMotor.setPower(0.0);

        OurRobot.drivetrain.Move(0.5, -20, Drivetrain.MoveType.DRIVE, this);
        /*
        Outtake.PosASH scorePos = OpenCV.Pipeline.getAnalysis();

        //Strafes left to shipping hub, turns 180, and pre loads
        OurRobot.drivetrain.Move(1, -10, Drivetrain.MoveType.STRAFE);
        OurRobot.imu.gyroTurn(0.5, 180);
        OurRobot.outtake.scoreASH(scorePos);

        //Drives back, strafes to carousel, turns it, parks backwards
        OurRobot.drivetrain.Move(1, 8, Drivetrain.MoveType.DRIVE);
        OurRobot.drivetrain.Move(1, -20, Drivetrain.MoveType.STRAFE);
        OurRobot.carousel.spin(1, 3);
        OurRobot.drivetrain.Move(1, -5, Drivetrain.MoveType.DRIVE);
        */
    }
}
