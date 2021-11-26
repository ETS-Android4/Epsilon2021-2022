/*
Christopher Ward
11/11/21
*/

package Epsilon.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import Epsilon.Superclasses.Subsystem;

public class CarouselJank implements Subsystem {

    public DcMotor duckMotor;
    public static final int TICKS_PER_ROTATION = 300;       //this is not correct

    public void initialize(LinearOpMode opMode) {

        duckMotor = opMode.hardwareMap.dcMotor.get("duckMotor");

        duckMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void spin(int power, int rotations){
        duckMotor.setPower(power);
        duckMotor.setTargetPosition(rotations*TICKS_PER_ROTATION);

    }
}
