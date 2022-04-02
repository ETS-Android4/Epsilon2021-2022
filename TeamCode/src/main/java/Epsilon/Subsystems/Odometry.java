package Epsilon.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import java.time.Year;

import Epsilon.OurRobot;
import Epsilon.Superclasses.Subsystem;

public class Odometry implements Subsystem {
    //public Dc<p>

    public DcMotor encoderX;
    public DcMotor encoderY;

    //public double lastEncoderXPos;
    public double lastEncoderYPos;
    public double lastEncoderXPos;
    public double XChange;
    public double YChange;
    public double lastAngle = 0;
    public double xPos;
    public double yPos;
    public double heading = 0;  //aka angle of bot in radians
    public double forwardOffSet = 5; //Distance (INCHES) from encoderX to center of bot

    IMU imu = new IMU();

    //created "opMode" for the run() method below
    LinearOpMode opMode;

    public void initialize(LinearOpMode opMode) {
        encoderX = OurRobot.drivetrain.backLeft; //opMode.hardwareMap.get(DcMotor.class, "frontLeft");
        encoderY = OurRobot.drivetrain.frontLeft; //.hardwareMap.get(DcMotor.class, "backLeft");

        encoderX.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        encoderY.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        encoderX.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        encoderY.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        lastEncoderYPos = encoderY.getCurrentPosition();
        lastEncoderXPos = encoderX.getCurrentPosition();
        xPos = 0;
        yPos = 0;
    }

    public double encoderToInch(double ticks){
        //Inches to Encoder Counts Stuff
        //Diameter: 1.96 in
        //8192 ticks per rev
        //(8192 ticks / 1 rev) * (1 rev / 1.96pi in)
        double COUNTS_PER_INCH = 8192/(Math.PI*1.96);
        double inches = ticks/COUNTS_PER_INCH;
        return inches;
    }

    public void update(){
        //imu.update();
        //Find change in encoder position
        double YEncoderChange = encoderY.getCurrentPosition() - lastEncoderYPos;
        double XEncoderChange = encoderX.getCurrentPosition()- lastEncoderXPos;

        //calculate angle
        double changeInAngle = IMU.normalizeRadians(Math.toRadians(imu.angle()-lastAngle));

        //double horizontalDisplacement = (XEncoderChange - forwardOffSet)*changeInAngle;

        heading = IMU.normalizeRadians(heading + changeInAngle);

        //Find change in x and y position - ?
        //swapped XChange and YChange
        YChange = YEncoderChange * Math.cos(heading) + XEncoderChange * Math.sin(heading);
        XChange = YEncoderChange * Math.sin(heading) + XEncoderChange * Math.cos(heading);

        xPos = encoderToInch(encoderX.getCurrentPosition());
        yPos = encoderToInch(encoderY.getCurrentPosition());

        //update old angle
        lastAngle = imu.angle();

        lastEncoderXPos = encoderX.getCurrentPosition();
        lastEncoderYPos = encoderY.getCurrentPosition();
    }

}


