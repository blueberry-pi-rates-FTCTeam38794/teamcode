/*
Copyright 2026 FIRST Tech Challenge Team 38794

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
associated documentation files (the "Software"), to deal in the Software without restriction,
including without limitation the rights to use, copy, modify, merge, publish, distribute,
sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial
portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.DcMotor;


public class TeleOpGeneral {

    public static void runOp(LinearOpMode opmode, Utils utils, Gamepad gamepad1, Gamepad gamepad2) {
        opmode.waitForStart();
        
        double speed = 1;
        boolean dpadupstate = false; // so the speed doesn't change every time it loops
        boolean dpaddownstate = false;
        boolean squarestate = false;
        boolean intakerunning = false;
        CRServo windmill = opmode.hardwareMap.get(CRServo.class, "windmill");
        utils.getMotor(true).setDirection(DcMotor.Direction.REVERSE);
        
        while (opmode.opModeIsActive()) {
            
            if (gamepad1.dpad_up&&!dpadupstate) {
                dpadupstate = true;
                if (speed <= 0.9) speed += 0.1;
            }
            
            if (gamepad1.dpad_down&&!dpaddownstate) {
                dpaddownstate = true;
                if (speed >= 0.1) speed -= 0.1;
            }
            
            if (gamepad2.square&&!squarestate) {
                squarestate = true;
                intakerunning = !intakerunning;
                utils.setPower(utils.getMotor("intakeMotor"), intakerunning?1:0);
                utils.setPower(utils.getMotor("flybackMotor"), intakerunning?1:0);
                windmill.setPower(intakerunning?-1:0);
            }
            
            // reset values
            if (!gamepad1.dpad_up&&dpadupstate) dpadupstate = false;
            if (!gamepad1.dpad_down&&dpaddownstate) dpaddownstate = false;
            if (!gamepad1.square&&squarestate) squarestate = false;
            
            {
                double leftPower = 0;
                double rightPower = 0;
                double maxPower = 0;
            
                // values from drive and turn
                leftPower = -gamepad1.left_stick_y + gamepad1.right_stick_x;
                rightPower = -gamepad1.left_stick_y - gamepad1.right_stick_x;
            
                // normalization, makes the maximum value 1 while keeping the same motor ratio
                maxPower = Math.max(1, Math.max(Math.abs(leftPower), Math.abs(rightPower)));
                leftPower /= maxPower;
                rightPower /= maxPower;
            
                // multiply by speed
                leftPower *= speed;
                rightPower *= speed;
            
                utils.setTargetPower(utils.getMotor(false), leftPower);
                utils.setTargetPower(utils.getMotor(true), rightPower);
            }
            
            utils.print(String.format("Motors: %.2f, %.2f\nSpeed: %.1f\nIntake is%s running.",
            utils.getPower(false), utils.getPower(true), speed, intakerunning?"":" not"));
            utils.updatePower(getMotor(false), 0.1);
            utils.updatePower(getMotor(true), 0.1);
            opmode.telemetry.update();
        }
    }
}
