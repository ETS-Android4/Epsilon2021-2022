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
    public final double ARM_EXTEND = 0.6;
    public final double ARM_RETRACT = 0.0;
    public final int ASH_BOTTOM = 300;
    public final int ASH_MID = 600;
    public final int ASH_TOP = 893;
    public final int FLOOR = 150;

    public DcMotor upMotor;
    public Servo arm;
    public Servo door;

    public void initialize(LinearOpMode opMode) {
        upMotor = opMode.hardwareMap.dcMotor.get("top");
        arm = opMode.hardwareMap.servo.get("bottom");
        door = opMode.hardwareMap.servo.get("door");

        upMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    public double PID(double targetPos) {
        double kP = 0.04;
        double power;
        double error = targetPos - upMotor.getCurrentPosition();
        power = error * kP;
        return power;
    }

    public void extendo(double position, int verticalTicks, double power){
        upMotor.setTargetPosition(verticalTicks);
        upMotor.setPower(power);
        arm.setPosition(position);
    }
    public void openDoor(){
        door.setPosition(1.0);
    }

    public void closeDoor(){
        door.setPosition(0.0);
    }

    public void reset(){
        arm.setPosition(0.0);
        upMotor.setTargetPosition(350);
        upMotor.setPower(-0.3);
    }
    public void setVertical(double power, int ticks){
        power = Range.clip(power, -0.3, 0.6);
        upMotor.setPower(power);
        upMotor.setTargetPosition(ticks);
    }
    public void setHorizontal(double position) {
        position = Range.clip(position, 0, 1);
        arm.setPosition(position);
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
        reset();
    }


}
