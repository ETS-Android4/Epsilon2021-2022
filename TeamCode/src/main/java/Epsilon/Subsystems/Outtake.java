package Epsilon.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

import Epsilon.Superclasses.Subsystem;

//Initializes all the motors/hardware for the drivetrain
public class Outtake implements Subsystem {

    public final double TICKS_PER_ROTATION = 300;       //this is not correct
    public final double TICKS_PER_INCH = 50/6;      //temporary numbers lmao please fix later
    public DcMotor top;
    public DcMotor bottom;

    public void initialize(LinearOpMode opMode) {
        top = opMode.hardwareMap.dcMotor.get("top");
        bottom = opMode.hardwareMap.dcMotor.get("bottom");

        top.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bottom.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }
    public void extendo(double hDistance, double vDistance, double power){
        vertical(power,vDistance);
        horizontal(power,hDistance);

    }
    public void vertical(double power, double inches){
        power = Range.clip(power, -0.5, 0.5);
        top.setPower(power);
        top.setTargetPosition((int)(inches*TICKS_PER_INCH));

    }
    public void horizontal(double power, double inches){
        power = Range.clip(power, -0.5, 0.5);
        bottom.setPower(power);
        bottom.setTargetPosition((int)(inches*TICKS_PER_INCH));

    }
}
