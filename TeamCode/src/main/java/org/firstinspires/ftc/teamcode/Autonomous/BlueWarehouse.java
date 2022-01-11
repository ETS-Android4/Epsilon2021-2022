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

        //Strafes right to shipping hub, turns 180, and pre loads
        OurRobot.drivetrain.Move(1, 10, Drivetrain.MoveType.STRAFE);
        OurRobot.imu.gyroTurn(0.5, 180);
        OurRobot.outtake.scoreASH(scorePos);

        //Drives back, turns 90, drives to warehouse
        OurRobot.drivetrain.Move(1, 8, Drivetrain.MoveType.DRIVE);
        OurRobot.imu.gyroTurn(0.5, 90);
        OurRobot.drivetrain.Move(1, 10, Drivetrain.MoveType.DRIVE);
    }
}
