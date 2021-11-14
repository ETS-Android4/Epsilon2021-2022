package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import Epsilon.OurRobot;

public class AutEpsilonTM extends LinearOpMode {
    OurRobot robot = new OurRobot();    //creates instance of "OurRobot," giving it access to hardware/methods
    @Override
    public void runOpMode() throws InterruptedException {
        //Scan Duck or Cool Team Game Element
        robot.drivetrain.Move(1,69420,"STRAFE");
        for (int i = 0; i < 3; i++) {
            robot.drivetrain.Move(1,69420, "DRIVE");
            //Move Linear Slides and Drop into Shipping Hub
            robot.drivetrain.Move(-1,69420,"DRIVE");
            robot.drivetrain.Move(1, 69420, "TURN");
            robot.drivetrain.Move(1,69420, "DRIVE");
            //Intake Freight
            robot.drivetrain.Move(-1,69420,"DRIVE");
            robot.drivetrain.Move(-1, 69420, "TURN");
        }
        robot.drivetrain.Move(-1, 69420, "STRAFE");
        //Strafe into Carousel
        wait(69420);
        robot.drivetrain.Move(1, 69420, "DRIVE");
        //Park in Storage Unit
    }
}