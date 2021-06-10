package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="AutonomousTesting")
public class WorkingAutonomous extends LinearOpMode {
    
    HardwarePushbotMechanum robot   = new HardwarePushbotMechanum();
    private ElapsedTime     runtime = new ElapsedTime();

    static final double     COUNTS_PER_MOTOR_REV    = 4;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 72.0 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 3.1250 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
                                                      (WHEEL_DIAMETER_INCHES * Math.PI);
    static final double     driveSpeed             = 1;
    static final double     turnSpeed              = 0.5;
        
                
    public int inchToDegree(int inches){
    int degrees;
    degrees = (int)((1/0.0175) * inches);
    telemetry.addData("Degrees : ", degrees);
    telemetry.update();
    return degrees;
    }
    
    public int degreeToInch(int degrees){
    int inches;
    inches = (int)(((0.0175/1) * 3.9785) * degrees * Math.PI);
    telemetry.addData("Inches : ", inches);
    telemetry.update();
    return inches;
    }
    
    public void resetEncoders(){
        robot.motorFrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorFrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorBackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorBackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    
    @Override
    public void runOpMode() {
        //Initalize variables, done in initalize function in hardware map file
        robot.initalizeHardwareMap(hardwareMap);
        resetEncoders();

        // YOU PRESS THE PLAY BUTTON ON PHONE
        waitForStart();

/*
        Right = positive, negative 
        Left = negative, postive
        1 degree = 0.0175 in 

*/

        //Reverse/turning = using negative distance
        driveStraight(driveSpeed, 48, 1000);  // S1: Forward 47 Inches with 5 Sec timeout
        sleep(10000);
        driveTurnLeft(driveSpeed, 180, 4);  // S2: Turn Right 12 Inches with 4 Sec timeout
        sleep(5000);
        driveStraight(driveSpeed, 96, 1000);  // S1: Forward 47 Inches with 5 Sec timeout
        sleep(15000);
        driveTurnRight(driveSpeed, 180, 4);  // S2: Turn Right 12 Inches with 4 Sec timeout
        sleep(5000);
        //driveReverse(driveSpeed, -24, 4.0);  // S3: Reverse 24 Inches with 4 Sec timeout
        //sleep(1000);

        telemetry.addData("Path", "Complete");
        telemetry.update();
    }
    
    
    public void driveStraight(double speed, double inches, double timeoutS) {
        resetEncoders();
        
        int newFrontLeftTarget;
        int newFrontRightTarget;
        int newBackLeftTarget;
        int newBackRightTarget;
        
        if (opModeIsActive()) {
            //Calculate new target
            newFrontLeftTarget = robot.motorFrontLeft.getCurrentPosition() + (int)(inches * COUNTS_PER_INCH);
            newFrontRightTarget = robot.motorFrontRight.getCurrentPosition() + (int)(inches * COUNTS_PER_INCH);
            newBackLeftTarget = robot.motorBackLeft.getCurrentPosition() + (int)(inches * COUNTS_PER_INCH);
            newBackRightTarget = robot.motorBackRight.getCurrentPosition() + (int)(inches * COUNTS_PER_INCH);
            
            //Set new target
            robot.motorFrontLeft.setTargetPosition(newFrontLeftTarget);
            robot.motorBackRight.setTargetPosition(newBackRightTarget);
            robot.motorFrontRight.setTargetPosition(newFrontRightTarget);
            robot.motorBackLeft.setTargetPosition(newBackLeftTarget);
            
            // Turn On RUN_TO_POSITION
            robot.motorFrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorFrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorBackLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorBackRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);            

            // Reset the timeout time and start motion.
            runtime.reset();
            robot.motorFrontLeft.setPower(Math.abs(speed));
            robot.motorFrontRight.setPower(Math.abs(speed));
            robot.motorBackLeft.setPower(Math.abs(speed));
            robot.motorBackRight.setPower(Math.abs(speed));            
        }
    }
    
    public void driveReverse(double speed, double inches, double timeoutS) {
        resetEncoders();
        
        int newFrontLeftTarget;
        int newFrontRightTarget;
        int newBackLeftTarget;
        int newBackRightTarget;
        inches = (inches * -1);
        if (opModeIsActive()) {
            //Calculate new target
            newFrontLeftTarget = robot.motorFrontLeft.getCurrentPosition() + (int)(inches * COUNTS_PER_INCH);
            newFrontRightTarget = robot.motorFrontRight.getCurrentPosition() + (int)(inches * COUNTS_PER_INCH);
            newBackLeftTarget = robot.motorBackLeft.getCurrentPosition() + (int)(inches * COUNTS_PER_INCH);
            newBackRightTarget = robot.motorBackRight.getCurrentPosition() + (int)(inches * COUNTS_PER_INCH);
            
            //Set new target
            robot.motorFrontLeft.setTargetPosition(newFrontLeftTarget);
            robot.motorBackRight.setTargetPosition(newBackRightTarget);
            robot.motorFrontRight.setTargetPosition(newFrontRightTarget);
            robot.motorBackLeft.setTargetPosition(newBackLeftTarget);
            
            // Turn On RUN_TO_POSITION
            robot.motorFrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorFrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorBackLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorBackRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);            

            // Reset the timeout time and start motion.
            runtime.reset();
            robot.motorFrontLeft.setPower(Math.abs(speed));
            robot.motorFrontRight.setPower(Math.abs(speed));
            robot.motorBackLeft.setPower(Math.abs(speed));
            robot.motorBackRight.setPower(Math.abs(speed));            
        }
    }
    
    public void driveTurnLeft(double speed, int degreesNew, double timeoutS) {
        int newFrontLeftTarget;
        int newFrontRightTarget;
        int newBackLeftTarget;
        int newBackRightTarget;
        
        //int degreesNew = inchToDegree(inchesNew);
        int inchesNew = degreeToInch(degreesNew);

            //Calculate new target
            newFrontLeftTarget = robot.motorFrontLeft.getCurrentPosition() + (int)(-inchesNew * COUNTS_PER_INCH);
            newFrontRightTarget = robot.motorFrontRight.getCurrentPosition() + (int)(inchesNew * COUNTS_PER_INCH);
            newBackLeftTarget = robot.motorBackLeft.getCurrentPosition() + (int)(-inchesNew * COUNTS_PER_INCH);
            newBackRightTarget = robot.motorBackRight.getCurrentPosition() + (int)(inchesNew * COUNTS_PER_INCH);
            
            //Set new target
            robot.motorFrontLeft.setTargetPosition(newFrontLeftTarget);
            robot.motorBackRight.setTargetPosition(newBackRightTarget);
            robot.motorFrontRight.setTargetPosition(newFrontRightTarget);
            robot.motorBackLeft.setTargetPosition(newBackLeftTarget);
            
            // Turn On RUN_TO_POSITION
            robot.motorFrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorFrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorBackLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorBackRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);            

            // Reset the timeout time and start motion.
            runtime.reset();
            robot.motorFrontLeft.setPower(Math.abs(speed));
            robot.motorFrontRight.setPower(Math.abs(speed));
            robot.motorBackLeft.setPower(Math.abs(speed));
            robot.motorBackRight.setPower(Math.abs(speed));            
        
    }
    
        public void driveTurnRight(double speed, int degreesNew, double timeoutS) {
        int newFrontLeftTarget;
        int newFrontRightTarget;
        int newBackLeftTarget;
        int newBackRightTarget;
        
        //int degreesNew = inchToDegree(inchesNew);
        int inchesNew = degreeToInch(degreesNew);

            //Calculate new target
            newFrontLeftTarget = robot.motorFrontLeft.getCurrentPosition() + (int)(inchesNew * COUNTS_PER_INCH);
            newFrontRightTarget = robot.motorFrontRight.getCurrentPosition() + (int)(-inchesNew * COUNTS_PER_INCH);
            newBackLeftTarget = robot.motorBackLeft.getCurrentPosition() + (int)(inchesNew * COUNTS_PER_INCH);
            newBackRightTarget = robot.motorBackRight.getCurrentPosition() + (int)(-inchesNew * COUNTS_PER_INCH);
            
            //Set new target
            robot.motorFrontLeft.setTargetPosition(newFrontLeftTarget);
            robot.motorBackRight.setTargetPosition(newBackRightTarget);
            robot.motorFrontRight.setTargetPosition(newFrontRightTarget);
            robot.motorBackLeft.setTargetPosition(newBackLeftTarget);
            
            // Turn On RUN_TO_POSITION
            robot.motorFrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorFrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorBackLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorBackRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);            

            // Reset the timeout time and start motion.
            runtime.reset();
            robot.motorFrontLeft.setPower(Math.abs(speed));
            robot.motorFrontRight.setPower(Math.abs(speed));
            robot.motorBackLeft.setPower(Math.abs(speed));
            robot.motorBackRight.setPower(Math.abs(speed));            
        
    }
}
