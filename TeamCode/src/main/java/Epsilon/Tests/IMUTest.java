package Epsilon.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import Epsilon.OurRobot;
import Epsilon.Subsystems.Drivetrain;

@Autonomous
public class IMUTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        OurRobot robot = new OurRobot();    //creates instance of "OurRobot," giving it access to hardware/methods
        robot.initialize(this);
        waitForStart();
        OurRobot.drivetrain.Move(0.35, 18, Drivetrain.MoveType.STRAFE, this);
    }
}
