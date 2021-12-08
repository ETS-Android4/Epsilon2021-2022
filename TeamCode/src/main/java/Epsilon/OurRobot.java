package Epsilon;

import android.graphics.Path;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

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

public class OurRobot implements EpsilonRobot {

    //Creates instances of all the subsystem
    public Drivetrain drivetrain = new Drivetrain();
    public CarouselJank carousel = new CarouselJank();
    public Intake intake = new Intake();
    public IMU imu = new IMU();
    public Outtake outtake = new Outtake();
    public Odometry Odometry = new Odometry();
    public OpenCV OpenCV = new OpenCV();

    private final Subsystem[] Subsystems = {intake, drivetrain, imu, Odometry
            //carousel
    //        , carousel, intake, outtake
    };    //Array for all the subsystems

    @Override
    // "initialize" method runs the "initialize" method in all the subsystems
    // Essentially declares/initializes all the motors and stuff
    public void initialize(LinearOpMode opMode) {
        for (Subsystem x : Subsystems){
            x.initialize(opMode);
        }
    }


}