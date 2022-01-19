package Epsilon;

import android.graphics.Path;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import Epsilon.Subsystems.Drivetrain;
import Epsilon.Subsystems.CarouselJank;
import Epsilon.Subsystems.Intake;
import Epsilon.Subsystems.Odometry;
import Epsilon.Subsystems.OpenCV;
import Epsilon.Subsystems.Outtake;
import Epsilon.Subsystems.IMU;
import Epsilon.Superclasses.EpsilonRobot;
import Epsilon.Superclasses.Subsystem;

//"OurRobot" class creates instances of all the subsystem
//This class also contains most of the methods we'll use in auto

public class OurRobot {

    //Creates instances of all the subsystem
    public static Drivetrain drivetrain = new Drivetrain();
    public static CarouselJank carousel = new CarouselJank();
    public static Intake intake = new Intake();
    public static IMU imu = new IMU();
    public static Outtake outtake = new Outtake();
    public static Odometry Odometry = new Odometry();
    public static OpenCV OpenCV = new OpenCV();

    private static final Subsystem[] Subsystems = {
            outtake,
            drivetrain,
            intake,
            imu,
            //Odometry,
            OpenCV,
            carousel
    };    //Array for all the subsystems


    // "initialize" method runs the "initialize" method in all the subsystems
    // Essentially declares/initializes all the motors and stuff
    public static void initialize(LinearOpMode opMode) {
        opMode.telemetry.addLine("Hi");
        opMode.telemetry.update();
        for (int i = 0; i<Subsystems.length; i++){
            Subsystems[i].initialize(opMode);
        }
    }

    public static void wait(int milliseconds){
        ElapsedTime time = new ElapsedTime();
        while(time.milliseconds() < milliseconds){

        }
    }

}