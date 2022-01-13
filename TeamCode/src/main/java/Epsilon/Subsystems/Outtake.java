package Epsilon.Subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import Epsilon.Superclasses.Subsystem;

//Initializes all the motors/hardware for the drivetrain
public class Outtake implements Subsystem {

    public final double TICKS_PER_ROTATION = 357.7;       //this is correct
    double circumference = 1.8897637795;                             //circumference in inches
    public final double TICKS_PER_INCH = TICKS_PER_ROTATION/circumference;      //temporary numbers lmao please fix later
    public DcMotor top;
    public Servo bottom;
    public Servo door;

    public void initialize(LinearOpMode opMode) {
        top = opMode.hardwareMap.dcMotor.get("top");
        bottom = opMode.hardwareMap.servo.get("bottom");
        door = opMode.hardwareMap.servo.get("door");

        top.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    public double PID(double targetPos) {
        double kP = 0.05;
        double power;
        double error = targetPos - top.getCurrentPosition();
        power = error * kP;
        return power;
    }

    public void extendo(double position, int verticalTicks, double power){
        top.setTargetPosition(verticalTicks);
        top.setPower(power);
        Timer(1000);
        bottom.setPosition(position);
    }
    public void door(){
        door.setPosition(1.0);
        Timer(1000);
        door.setPosition(0.0);
    }

    public void reset(){
        bottom.setPosition(0.0);
        Timer(1000);
        top.setTargetPosition(350);
        top.setPower(-0.3);
    }
    public void vertical(double power, double inches){
        power = Range.clip(power, -0.3, 0.3);
        top.setPower(power);
        top.setTargetPosition((int)(inches*TICKS_PER_INCH));
    }
    public void horizontal(double degrees) {
        degrees = Range.clip(degrees, 0, 1);
        bottom.setPosition(degrees);
    }
    public enum PosASH {
        TOP,
        MID,
        BOTTOM
    }
    public void scoreASH(PosASH pos){
        if (pos == PosASH.BOTTOM) {
            extendo(0.6, 411, 0.5);

        } else if (pos == PosASH.MID) {
            extendo(0.6, 625, 0.5);

        } else if (pos == PosASH.TOP){
            extendo(0.6, 893, 0.5);
        }
        Timer(1000);
        reset();
    }

    public void Timer(double milliseconds){
        ElapsedTime time = new ElapsedTime();
        while(time.milliseconds() < milliseconds){}
    }

}
