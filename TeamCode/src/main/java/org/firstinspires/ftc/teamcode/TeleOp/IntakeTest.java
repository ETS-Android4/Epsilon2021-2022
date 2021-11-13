package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import Epsilon.OurRobot;
import Epsilon.Superclasses.EpsilonRobot;

@TeleOp
public class IntakeTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException{

        waitForStart();
        OurRobot robot = new OurRobot();    //creates instance of "OurRobot," giving it access to hardware/methods
        robot.initialize(this);

        while (opModeIsActive()){
            boolean x = gamepad1.x;
            boolean y = gamepad1.y;
            double speed = 0.5;

            if(x&&y)
                robot.intake.wheel.setPower(speed * -1);
            else if(x)
                robot.intake.wheel.setPower(speed);
        }
    }
}
