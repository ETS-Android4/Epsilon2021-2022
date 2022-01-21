package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import Epsilon.OurRobot;
import Epsilon.Subsystems.Drivetrain;
import Epsilon.Subsystems.OpenCV;
import Epsilon.Subsystems.Outtake;

@Autonomous
public class RedCarousel extends LinearOpMode {

    public void runOpMode() throws InterruptedException{
/*
        OurRobot.initialize(this);

        waitForStart();

        OurRobot.drivetrain.Move(0.5, -8, Drivetrain.MoveType.STRAFE);
        OurRobot.drivetrain.Move(0.5,29, Drivetrain.MoveType.DRIVE);
        OurRobot.wait(2000);
        OurRobot.carousel.duckMotor.setPower(-0.5);
        OurRobot.wait(5000);
        OurRobot.carousel.duckMotor.setPower(0.0);
        OurRobot.drivetrain.Move(0.5,-22, Drivetrain.MoveType.STRAFE);

        /*
        Outtake.PosASH scorePos = OpenCV.Pipeline.getAnalysis();

        //Strafes right to shipping hub, turns 180, and pre loads
        OurRobot.drivetrain.Move(1, 10, Drivetrain.MoveType.STRAFE);
        OurRobot.imu.gyroTurn(0.5, 180);
        OurRobot.outtake.scoreASH(scorePos);

        //Drives back, strafes to carousel, turns it, parks backwards
        OurRobot.drivetrain.Move(1, 8, Drivetrain.MoveType.DRIVE);
        //Will change based on orientation of carousel spinner
        OurRobot.drivetrain.Move(1, 20, Drivetrain.MoveType.STRAFE);
        OurRobot.carousel.spin(1, 3);
        OurRobot.drivetrain.Move(1, 5, Drivetrain.MoveType.DRIVE);

         */
    }
}
