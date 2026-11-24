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

import org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.ExportToBlocks;

// this allows people coding in blocks to use the Utils class
public class BlockUtils extends BlocksOpModeCompanion {
    
    private static Utils utils;
    private static boolean initialized = false;

    @ExportToBlocks(comment="Initializes the utility variables.\nThis is optional as it is called automatically but it would be faster if it's done during the block initialization.")
    public static void init() {
        if(!initialized){utils=new Utils(hardwareMap,telemetry);initialized=true;}
    }
    
    @ExportToBlocks(comment="Sets the power of the left and right motors.",
    parameterLabels={"leftPower", "rightPower"})
    public static void setMotors(double left, double right) {init();utils.setMotors(left,right);}
    
    @ExportToBlocks(comment="Sets the power of any motor. The first argument must be a motor variable obtained by getMotor.",
    parameterLabels={"motor", "power"})
    public static void setPower(DcMotor motor, double power) {init();utils.setMotor(motor,power);}
    
    @ExportToBlocks(comment="Gets the power of the left and right motors. If boolean 'right' is true, it gets the power of the right motor. If it's false, it gets the power of the left motor.",
    parameterLabels={"right"})
    public static double getPower(boolean right) {init();return utils.getPower(right);}
    
    @ExportToBlocks(comment="Gets the power of any motor. The argument must be a motor variable obtained by getMotor.",
    parameterLabels={"motor"})
    public static double getPower(DcMotor motor) {init();return utils.getPower(motor);}
    
    @ExportToBlocks(comment="Gets the left or right motor as a single variable. Only usable with other BlockUtils methods. Refer to getPower's comment for use.",
    parameterLabels={"right"})
    public static DcMotor getMotor(boolean right) {init();return utils.getMotor(right);}
    
    @ExportToBlocks(comment="Gets any motor as a single variable. Only usable with other BlockUtils methods.",
    parameterLabels={"motor"})
    public static DcMotor getMotor(String motor) {init();return utils.getMotor(motor);}
    
    @ExportToBlocks(comment="Adds text to the driver hub telemetry screen and updates it.",
    parameterLabels={"string"})
    public static void print(String string) {init();utils.print(string);}

    @ExportToBlocks(comment="Sets the target power of a motor. Use this with updatePower to make the speed gradually accelerate.",
    parameterLabels={"motor", "power"})
    public static void setTargetPower(DcMotor motor, double power) {init();utils.setTargetPower(motor, power);}

    @ExportToBlocks(comment="Gets the target power of a motor.",
    parameterLabels={"motor"})
    public static void getTargetPower(DcMotor motor) {init();utils.getTargetPower(motor);}

    @ExportToBlocks(comment="Removes a target power, so you can manually set the power where it doesn't update towards the target.",
    parameterLabels={"motor"})
    public static void removeTargetPower(DcMotor motor) {init();utils.removeTargetPower(motor);}

    @ExportToBlocks(comment="Makes the motor's power value slowly go towards the motor's target power at a given speed. The speed must be positive. This also won't work correctly if you have changed the motor's power without BlockUtils before.")
    public static void updatePower(DcMotor motor, double speed) {init();utils.updatePower(motor, speed);}
}
