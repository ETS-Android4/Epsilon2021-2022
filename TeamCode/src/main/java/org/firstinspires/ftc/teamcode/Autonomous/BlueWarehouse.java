package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import Epsilon.OurRobot;
import Epsilon.Subsystems.Drivetrain;
import Epsilon.Subsystems.OpenCV;
import Epsilon.Subsystems.Outtake;

@Autonomous
public class BlueWarehouse extends LinearOpMode {

    public void runOpMode() throws InterruptedException{
        OurRobot.initialize(this);

        Outtake.PosASH scorePos = OpenCV.Pipeline.getAnalysis();
        telemetry.addData("OpenCV Pos: ", scorePos);
        telemetry.update();

        waitForStart();

        //drive to ASH
        OurRobot.drivetrain.Move(0.5, -29, Drivetrain.MoveType.STRAFE, this);
        OurRobot.drivetrain.Move(0.5, -18, Drivetrain.MoveType.DRIVE, this);

        //score ASH
        OurRobot.outtake.scoreASH(scorePos);

        //Move back and turn
        OurRobot.drivetrain.Move(0.5, 9, Drivetrain.MoveType.DRIVE, this);
        OurRobot.drivetrain.Move(0.3, 18, Drivetrain.MoveType.TURN, this);

        //Drive into warehouse
        OurRobot.drivetrain.Move(0.5,-55, Drivetrain.MoveType.DRIVE, this);

        /*
        Outtake.PosASH scorePos = OpenCV.Pipeline.getAnalysis();

        //Strafes right to shipping hub, turns 180, and pre loads
        OurRobot.drivetrain.Move(1, 10, Drivetrain.MoveType.STRAFE);
        OurRobot.imu.gyroTurn(0.5, 180);
        OurRobot.outtake.scoreASH(scorePos);

        //Drives back, turns 90, drives to warehouse
        OurRobot.drivetrain.Move(1, 8, Drivetrain.MoveType.DRIVE);
        OurRobot.imu.gyroTurn(0.5, 90);
        OurRobot.drivetrain.Move(1, 10, Drivetrain.MoveType.DRIVE);

         */
    }
}
