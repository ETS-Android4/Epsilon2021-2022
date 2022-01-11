package Epsilon.Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import Epsilon.OurRobot;
import Epsilon.Superclasses.EpsilonRobot;

@TeleOp
public class IntakeTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException{


        OurRobot robot = new OurRobot();    //creates instance of "OurRobot," giving it access to hardware/methods
        robot.initialize(this);

        waitForStart();

        while (opModeIsActive()){
            boolean x = gamepad1.x;
            boolean y = gamepad1.y;
            double speed = 0.5;

            robot.intake.wheel.setPower(gamepad1.left_stick_y);
/*
            if(x)
                robot.intake.wheel.setPower(speed);
            else if(y)
                robot.intake.wheel.setPower(-speed);
            else
                robot.intake.wheel.setPower(0);
                */
        }
    }
}
