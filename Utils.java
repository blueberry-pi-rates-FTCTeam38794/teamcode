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

import com.qualcomm.robotcore.hardware.HardwareMap;
import java.util.HashMap;
import java.util.Map;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.Telemetry;


public class Utils {
    private final Telemetry telemetry;
    private final HardwareMap hardwareMap;
    private final Map<DcMotor, Double> powers = new HashMap<>();
    private final Map<DcMotor, Double> targetPowers = new HashMap<>();
    
    public Utils(HardwareMap hardwareMap, Telemetry telemetry) {
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;
    }
    
    public void setMotors(double left, double right) {
        if (leftPower == left && rightPower == right) return;
        
        left = Math.max(-1, Math.min(left, 1));
        right = Math.max(-1, Math.min(right, 1));
        
        leftMotor.setPower(left);
        rightMotor.setPower(right);
        
        leftPower = left;
        rightPower = right;
    }
    
    // false if left motor, true if right motor
    public double getPower(boolean right) {
        return powers.containsKey(right?); 
    }
    
    public double getPower(DcMotor motor) {
        if (!powers.containsKey(motor)) return 0;
        return powers.get(motor);
    }
    
    public DcMotor getMotor(boolean right) {
        return right?hardwareMap.get(DcMotor.class, "rightMotor"):
        hardwareMap.get(DcMotor.class, "leftMotor");
    }
    
    public DcMotor getMotor(String motor) {
        return hardwareMap.get(DcMotor.class, motor);
    }
    
    public void print(String string) {
        telemetry.addLine(string);
    }
    
    public void setMotor(DcMotor motor, double power) {
        if (!powers.containsKey(motor)) powers.put(motor, 0);
        power = Math.max(-1, Math.min(1, power));
        if (power == powers.get(motor)) return; 
        motor.setPower(power);
        powers.put(motor, power);
    }

    public void setTargetPower(DcMotor motor, double power) {
        power = Math.max(-1, Math.min(1, power));
        targetPowers.put(motor, power);
    }

    public void updatePower(DcMotor motor, double speed) {

    }
}
