
package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import Epsilon.OurRobot;
import Epsilon.Superclasses.EpsilonRobot;

@TeleOp
public class OuttakeTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException{


        OurRobot robot = new OurRobot();    //creates instance of "OurRobot," giving it access to hardware/methods
        robot.initialize(this);

        waitForStart();

        while (opModeIsActive()){
            double speed = 0.5;
            double horizontal = 0.0;
            double vertical = 0.0;

            if(gamepad1.dpad_right) horizontal = 1.0;
            if(gamepad1.dpad_left) horizontal = 0.0;
            if(gamepad1.dpad_up) vertical = 1.0;
            if(gamepad1.dpad_down) vertical = 0.0;

            robot.outtake.extendo(horizontal, vertical, speed);


        }
    }
}
