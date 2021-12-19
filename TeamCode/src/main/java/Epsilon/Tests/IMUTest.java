package Epsilon.Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import Epsilon.OurRobot;

@TeleOp
public class IMUTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        OurRobot robot = new OurRobot();    //creates instance of "OurRobot," giving it access to hardware/methods
        robot.initialize(this);

        waitForStart();
        while(opModeIsActive()){
            telemetry.addData("IMU test", OurRobot.imu.angle());

            telemetry.addData("IMU first angle", OurRobot.imu.imu.getAngularOrientation().firstAngle);
            telemetry.addData("IMU second angle", OurRobot.imu.imu.getAngularOrientation().secondAngle);
            telemetry.addData("IMU third angle", OurRobot.imu.imu.getAngularOrientation().thirdAngle);

            telemetry.update();
        }

    }
}
