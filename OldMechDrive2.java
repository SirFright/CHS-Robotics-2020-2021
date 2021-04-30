package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="OldMechDrive2", group="Linear Opmode")
public class OldMechDrive2 extends LinearOpMode {

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

        /*LF.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);
        LB.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);
        RF.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);
        RB.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);*/

        // Lets try running with and without encoders
        
        waitForStart();
        while (opModeIsActive()) {

            // Setup a variable for each drive wheel to save power level for telemetry
            double leftPower;
            double rightPower;

            //drive code
            double FORWARD = 1; //max
            double NEUTRAL = 0;
            double MOD = -1;
            
            
            double drive = (FORWARD - gamepad1.left_stick_y) - NEUTRAL;
            double strafe = gamepad1.left_stick_x - NEUTRAL;
            double rotate = gamepad1.right_stick_x - NEUTRAL;

            double lfPower = drive + strafe + rotate + MOD;
            double rfPower = drive - strafe - rotate + MOD;
            double lbPower = drive - strafe + rotate + MOD;
            double rbPower = drive + strafe - rotate + MOD;

            // Send calculated power to wheels
            motorFrontLeft.setPower(lfPower);
            motorFrontRight.setPower(rfPower);
            motorBackLeft.setPower(lbPower);
            motorBackRight.setPower(rbPower);

            //robot reversals
            /*if (gamepad1.y) {
              LF.setDirection(DcMotor.Direction.FORWARD);
              RF.setDirection(DcMotor.Direction.REVERSE);
              LB.setDirection(DcMotor.Direction.FORWARD);
              RB.setDirection(DcMotor.Direction.REVERSE);
            } else if (gamepad1.b) {
              LF.setDirection(DcMotor.Direction.REVERSE);
              RF.setDirection(DcMotor.Direction.FORWARD);
              LB.setDirection(DcMotor.Direction.REVERSE);
              RB.setDirection(DcMotor.Direction.FORWARD);
            }*/
        }
    }
}
