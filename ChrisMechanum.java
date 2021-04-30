package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="RealMechDrive", group="Linear Opmode")
public class RealMechDrive extends LinearOpMode {

    // Declare OpMode members.
    private DcMotor motorFrontLeft = null;
    private DcMotor motorFrontRight = null;
    private DcMotor motorBackLeft = null;
    private DcMotor motorBackRight = null;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();


        motorFrontLeft = hardwareMap.get(DcMotor.class, "motorLeftFront");
        motorFrontRight = hardwareMap.get(DcMotor.class, "motorRightFront");
        motorBackLeft = hardwareMap.get(DcMotor.class,"motorLeftBack");
        motorBackRight = hardwareMap.get(DcMotor.class, "motorRightBack");

        motorFrontLeft.setDirection(DcMotor.Direction.REVERSE);
        motorFrontRight.setDirection(DcMotor.Direction.FORWARD);
        motorBackLeft.setDirection(DcMotor.Direction.REVERSE);
        motorBackRight.setDirection(DcMotor.Direction.FORWARD);



        waitForStart();
        while (opModeIsActive()) {
        
                
           double leftStickY = gamepad1.left_stick_y; // Remember, this is reversed!   -   I think that if we do this before above with the Direction.Reverse, we will be set.  Otherwise, I think we are initially reversing the right motors, but then reverse everything again so its backwards.  
           double leftStickX = gamepad1.left_stick_x;
           double rightStickX = gamepad1.right_stick_x;

           motorFrontLeft.setPower(leftStickY + leftStickX + rightStickX);
           motorFrontRight.setPower(leftStickY - leftStickX - rightStickX);
           motorBackLeft.setPower(leftStickY - leftStickX - rightStickX);
           motorBackRight.setPower(leftStickY + leftStickX + rightStickX);           
            }
        }
    }

