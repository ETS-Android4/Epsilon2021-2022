package Epsilon.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import java.time.Year;

import Epsilon.Superclasses.Subsystem;

public class Odometry implements Subsystem {
    //public Dc<p>

    public DcMotor encoderX;
    public DcMotor encoderY;

    //public double lastEncoderXPos;
    public double lastEncoderYPos;
    public double lastEncoderXPos;
    public double lastAngle = 0;
    public double xPos;
    public double yPos;
    public double heading = 0;  //aka angle of bot in radians
    public double forwardOffSet = 5; //Distance (INCHES) from encoderX to center of bot

    IMU imu = new IMU();

    //created "opMode" for the run() method below
    LinearOpMode opMode;

    public void initialize(LinearOpMode opMode) {
        encoderX = opMode.hardwareMap.dcMotor.get("encoderX");
        encoderY = opMode.hardwareMap.dcMotor.get("encoderY");

        encoderX.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        encoderY.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        encoderX.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        encoderY.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        lastEncoderYPos = encoderY.getCurrentPosition();
        lastEncoderXPos = encoderX.getCurrentPosition();
    }

    public double encoderToInch(double ticks){
        double inches = ticks*(Math.PI/4096);
        return inches;
    }

    public void update(){
        //Find change in encoder position
        double YEncoderChange = encoderY.getCurrentPosition() - lastEncoderYPos;
        double XEncoderChange = encoderX.getCurrentPosition()- lastEncoderXPos;

        //calculate angle
        double changeInAngle = IMU.normalizeRadians(Math.toRadians(imu.angle()-lastAngle));

        //double horizontalDisplacement = (XEncoderChange - forwardOffSet)*changeInAngle;

        heading = IMU.normalizeRadians(heading + changeInAngle);

        //Find change in x and y position - ?
        //swapped XChange and YChange
        double YChange = YEncoderChange * Math.cos(heading) + XEncoderChange * Math.sin(heading);
        double XChange = YEncoderChange * Math.sin(heading) + XEncoderChange * Math.cos(heading);

        xPos += encoderToInch(XChange);
        yPos += encoderToInch(YChange);

        //update old angle
        lastAngle = imu.angle();

        lastEncoderXPos = encoderX.getCurrentPosition();
        lastEncoderYPos = encoderY.getCurrentPosition();
    }

}


