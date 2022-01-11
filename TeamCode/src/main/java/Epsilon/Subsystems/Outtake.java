package Epsilon.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import Epsilon.Superclasses.Subsystem;

//Initializes all the motors/hardware for the drivetrain
public class Outtake implements Subsystem {

    public final double TICKS_PER_ROTATION = 357.7;       //this is correct
    double circumference = 1.8897637795;                             //circumference in inches
    public final double TICKS_PER_INCH = TICKS_PER_ROTATION/circumference;      //temporary numbers lmao please fix later
    public DcMotor top;
    public Servo bottom;

    public void initialize(LinearOpMode opMode) {
        top = opMode.hardwareMap.dcMotor.get("top");
        bottom = opMode.hardwareMap.servo.get("bottom");

        top.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    public double PID(double targetPos) {
        double kP = 0.05;
        double power;
        double error = targetPos - top.getCurrentPosition();
        power = error * kP;
        return power;
    }
    public void extendo(double degrees, double vDistance, double power){
        vertical(power,vDistance);
        horizontal(degrees);
    }
    public void vertical(double power, double inches){
        power = Range.clip(power, -0.3, 0.3);
        top.setPower(power);
        top.setTargetPosition((int)(inches*TICKS_PER_INCH));
    }
    public void horizontal(double degrees) {
        degrees = Range.clip(degrees, 90, 135);
        bottom.setPosition(degrees);
    }
    public enum PosASH {
        TOP,
        MID,
        BOTTOM
    }
    public void scoreASH(PosASH pos) {
        if (pos == PosASH.BOTTOM) {
            extendo(150, 3, 0.75);
            extendo(150, 3, -0.75);
        } else if (pos == PosASH.MID) {
            extendo(150, 8, 0.75);
            extendo(150, 8, -0.75);
        } else {
            extendo(150, 14, 0.75);
            extendo(150, 14, -0.75);
        }
    }
}
