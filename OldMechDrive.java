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


        LF.setDirection(DcMotor.Direction.REVERSE);
        LB.setDirection(DcMotor.Direction.REVERSE);
        RF.setDirection(DcMotor.Direction.FORWARD);
        RB.setDirection(DcMotor.Direction.FORWARD);
        LF.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);
        LB.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);
        RF.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);
        RB.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);

        // Lets try running with and without encoders
        
        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {

            // Setup a variable for each drive wheel to save power level for telemetry
            double leftPower;
            double rightPower;

            //drive code
            double FORWARD = 1; //max
            double NEUTRAL = 0;
            double MOD = -1;
            
            /*
            I think I found a possible issue with Matt's code.  So essentially for the drive variable, it seems as though the power will be full throttle 
            if its not moving, because if the Left Stick Y value is at zero(when it isin't moved at all), it's subracting 1 from 0 at that point, then another zero.
            I'm also wondering why Matt decided to subtract everything by NEUTRAL, as it is literally zero and will do nothing to the variables, maybe just appearance?
            Strafe and rotate make sense, as when you move the left stick all the way to the left, you want a -1 for it to move, and vice verca.  Same with rotation,
            anything below 0 would be counterclockwise, and anything above 0 would be clockwise.
            */
            
            
            double drive = (FORWARD - gamepad1.left_stick_y) - NEUTRAL;
            double strafe = gamepad1.left_stick_x - NEUTRAL;
            double rotate = gamepad1.right_stick_x - NEUTRAL;

            double lfPower = drive + strafe + rotate + MOD;
            double rfPower = drive - strafe - rotate + MOD;
            double lbPower = drive - strafe + rotate + MOD;
            double rbPower = drive + strafe - rotate + MOD;

            // Send calculated power to wheels
            LF.setPower(lfPower);
            RF.setPower(rfPower);
            LB.setPower(lbPower);
            RB.setPower(rbPower);

            //robot reversals
            if (gamepad1.y) {
              LF.setDirection(DcMotor.Direction.FORWARD);
              RF.setDirection(DcMotor.Direction.REVERSE);
              LB.setDirection(DcMotor.Direction.FORWARD);
              RB.setDirection(DcMotor.Direction.REVERSE);
            } else if (gamepad1.b) {
              LF.setDirection(DcMotor.Direction.REVERSE);
              RF.setDirection(DcMotor.Direction.FORWARD);
              LB.setDirection(DcMotor.Direction.REVERSE);
              RB.setDirection(DcMotor.Direction.FORWARD);
            }
        }
    }
}
