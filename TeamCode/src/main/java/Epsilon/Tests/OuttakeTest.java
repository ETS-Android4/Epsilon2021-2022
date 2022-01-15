
package Epsilon.Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import Epsilon.OurRobot;
import Epsilon.Superclasses.EpsilonRobot;

@TeleOp
public class OuttakeTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException{


        //OurRobot robot = new OurRobot();    //creates instance of "OurRobot," giving it access to hardware/methods
        OurRobot.initialize(this);
        double power = 0.6;
        double servoPosition = 0.2;
        double verticalPosition = OurRobot.outtake.upMotor.getCurrentPosition();
        waitForStart();

        OurRobot.outtake.arm.setPosition(0.2);

        while (opModeIsActive()){

            if(gamepad1.dpad_up) {
                OurRobot.outtake.upMotor.setPower(power);
                verticalPosition = OurRobot.outtake.upMotor.getCurrentPosition();
            } else if(gamepad1.dpad_down) {
                OurRobot.outtake.upMotor.setPower(-power/2);
                verticalPosition = OurRobot.outtake.upMotor.getCurrentPosition();
            } else
                OurRobot.outtake.upMotor.setPower(OurRobot.outtake.PID(verticalPosition));

                if (gamepad1.dpad_left && OurRobot.outtake.upMotor.getCurrentPosition() > 300) {
                    servoPosition = 0.6;
                    OurRobot.outtake.arm.setPosition(servoPosition);//PLACEHOLDER VALUE
                } else if (gamepad1.dpad_right) {
                    servoPosition = 0;
                    OurRobot.outtake.arm.setPosition(servoPosition);//PLACEHOLDER VALUE
                }
            //else
            //    OurRobot.outtake.top.setPower(0.0);

            telemetry.addData("Horizontal Position", OurRobot.outtake.arm.getPosition());
            telemetry.addData("Vertical Position", OurRobot.outtake.upMotor.getCurrentPosition());
            telemetry.update();
            /*
            double speed = 0.5;
            double horizontal = 0.0;
            double vertical = 0.0;

            robot.outtake.bottom.setPower(gamepad1.left_stick_y);
            robot.outtake.top.setPower(gamepad1.left_stick_x);
            */
            //robot.outtake.extendo(horizontal, vertical, speed);


        }
    }
}
