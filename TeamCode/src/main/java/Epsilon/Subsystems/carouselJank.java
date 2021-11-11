/*
Christopher Ward
11/11/21
*/

package Epsilon.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import Epsilon.Superclasses.Subsystem;

public class carouselJank implements Subsystem {

    public DcMotor duckMotor;

    public void initialize(LinearOpMode opMode) {

        duckMotor = opMode.hardwareMap.dcMotor.get("duckMotor");

        duckMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }
}
