package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import Epsilon.OurRobot;
import Epsilon.Subsystems.Drivetrain;
import Epsilon.Subsystems.OpenCV;

@TeleOp
public class AutEpsilonTM extends LinearOpMode {
    OurRobot robot = new OurRobot();    //creates instance of "OurRobot," giving it access to hardware/methods
    @Override
    public void runOpMode() throws InterruptedException {
        //Scan Duck or Cool Team Game Element
        robot.OpenCV.initialize(this);
        OpenCV.Pipeline.ObjectPos duckPosition = OpenCV.Pipeline.getAnalysis();
        robot.drivetrain.Move(18, Drivetrain.MoveType.STRAFE);
        robot.drivetrain.Move(9, Drivetrain.MoveType.DRIVE);
        if (duckPosition == OpenCV.Pipeline.ObjectPos.LEFT) {
            robot.outtake.extendo(7,0, 0.75);
            //move shuttle arm
            robot.outtake.extendo(7,0,-0.75);
        } else if (duckPosition == OpenCV.Pipeline.ObjectPos.CENTER) {
            robot.outtake.extendo(7,8, 0.75);
            //move shuttle arm
            robot.outtake.extendo(7,8,-0.75);
        } else {
            robot.outtake.extendo(7, 14, 0.75);
            //move shuttle arm
            robot.outtake.extendo(7,14,-0.75);
        }
        for (int i = 0; i < 3; i++) {
            robot.drivetrain.Move(-4, Drivetrain.MoveType.DRIVE);
            robot.drivetrain.Move(90, Drivetrain.MoveType.TURN); //  :/
            robot.drivetrain.Move(-5.5,  Drivetrain.MoveType.STRAFE);
            robot.drivetrain.Move(21, Drivetrain.MoveType.DRIVE);
            robot.intake.eat(1, 360);
            robot.drivetrain.Move(-21, Drivetrain.MoveType.DRIVE);
            robot.drivetrain.Move(5,  Drivetrain.MoveType.STRAFE);
            robot.drivetrain.Move(-90, Drivetrain.MoveType.TURN); //  :/
            robot.drivetrain.Move(4, Drivetrain.MoveType.DRIVE);
            robot.outtake.extendo(7, 14, 0.75);
            //move shuttle arm
            robot.outtake.extendo(7,14,-0.75);
        }
        robot.drivetrain.Move(-4, Drivetrain.MoveType.DRIVE);
        robot.drivetrain.Move(90, Drivetrain.MoveType.TURN); //  :/
        robot.drivetrain.Move(-5.5,  Drivetrain.MoveType.STRAFE);
        robot.drivetrain.Move(-36,  Drivetrain.MoveType.DRIVE);
        //might have to move around carousel, positioning
        wait(1500);
        robot.drivetrain.Move(18,  Drivetrain.MoveType.STRAFE);
        //Park in Storage Unit
    }
}
