package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import Epsilon.OurRobot;
import Epsilon.Superclasses.EpsilonRobot;

@TeleOp
public class ChrisLeech extends LinearOpMode{

    @Override
    public void runOpMode() throws InterruptedException{

        OurRobot robot = new OurRobot();    //creates instance of "OurRobot," giving it access to hardware/methods
        robot.initialize(this);

        waitForStart();

        while (opModeIsActive()){

            boolean aButton = gamepad1.a;
            telemetry.addData("A",aButton);
            telemetry.update();

            if(aButton)
                robot.carousel.duckMotor.setPower(-0.5);
            else
                robot.carousel.duckMotor.setPower(0.0);
        }
    }
}
