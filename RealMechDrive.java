package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="OldMechDrive", group="Linear Opmode")
public class OldMechDrive extends LinearOpMode {

    // Declare OpMode members.
    private DcMotor LF = null;
    private DcMotor RF = null;
    private DcMotor LB = null;
    private DcMotor RB = null;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();


        motorLeftFront = hardwareMap.get(DcMotor.class, "motorLeftFront");
        motorRightFront = hardwareMap.get(DcMotor.class, "motorRightFront");
        motorLeftBack = hardwareMap.get(DcMotor.class,"motorLeftBack");
        motorRightBack = hardwareMap.get(DcMotor.class, "motorRightBack");

        motorLeftFront.setDirection(DcMotor.Direction.REVERSE);
        motorRightFront.setDirection(DcMotor.Direction.FORWARD);
        motorLeftBack.setDirection(DcMotor.Direction.REVERSE);
        motorRightBack.setDirection(DcMotor.Direction.FORWARD);


        waitForStart();
        runtime.reset();
        while (opModeIsActive()) {
        
                
           double leftStickY = gamepad1.left_stick_y; // Remember, this is reversed!   -   I think that if we do this before above with the Direction.Reverse, we will be set.  Otherwise, I think we are initially reversing the right motors, but then reverse everything again so its backwards.  
           double leftStickX = gamepad1.left_stick_x;
           double rightStickX = gamepad1.right_stick_x;

           motorLeftFront.setPower(leftStickY + leftStickX + rightStickX);
           motorRightFront.setPower(leftStickY - leftStickX - rightStickX);
           motorLeftBack.setPower(leftStickY - leftStickX - rightStickX);
           motorRightBack.setPower(leftStickY + leftStickX + rightStickX);           
            }
        }
    }
}
