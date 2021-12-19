
package Epsilon.Tests;

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

            robot.outtake.bottom.setPower(gamepad1.left_stick_y);
            robot.outtake.top.setPower(gamepad1.left_stick_x);

            //robot.outtake.extendo(horizontal, vertical, speed);


        }
    }
}
