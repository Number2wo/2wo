package com.example.a2;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Joystick {
    private final Paint outerCirclePaint;
    private final Paint innerCirclePaint;
    private int outerCircleRadius;
    private int innerCircleRadius;
    private int outerCircleCenterPositionX;
    private int outerCircleCenterPositionY;
    private int innerCircleCenterPositionX;
    private int innerCircleCenterPositionY;
    private double joystickCenterToTouchDistance;
    private boolean isPressed;
    private double actuatorX;
    private double actuatorY;

    public Joystick(int centerPositionX, int centerPositionY, int outerCircleRadius, int innerCircleRadius){
        // outer and inner circle make up the joystick
        outerCircleCenterPositionX = centerPositionX;
        outerCircleCenterPositionY = centerPositionY;
        innerCircleCenterPositionX = centerPositionX;
        innerCircleCenterPositionY = centerPositionY;

        //radius of circles
        this.outerCircleRadius = outerCircleRadius;
        this.innerCircleRadius = innerCircleRadius;

        //draw the circles
        outerCirclePaint = new Paint();
        outerCirclePaint.setColor(Color.BLUE);
        outerCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        innerCirclePaint = new Paint();
        innerCirclePaint.setColor(Color.WHITE);
        innerCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }
    public void draw(Canvas canvas) {
        canvas.drawCircle(
                outerCircleCenterPositionX,
                outerCircleCenterPositionY,
                outerCircleRadius,
                outerCirclePaint
        );
        canvas.drawCircle(
                innerCircleCenterPositionX,
                innerCircleCenterPositionY,
                innerCircleRadius,
                innerCirclePaint
        );
    }

    public void update() {
        updateInnerCirclePosition();
    }

    private void updateInnerCirclePosition() {
        innerCircleCenterPositionX = (int) (outerCircleCenterPositionX + actuatorX * outerCircleRadius);
        innerCircleCenterPositionY = (int) (outerCircleCenterPositionY + actuatorY * outerCircleRadius);
    }

    public boolean isPressed(double touchPositionX, double touchPositionY) {
        joystickCenterToTouchDistance = Math.sqrt(
                Math.pow(outerCircleCenterPositionX - touchPositionX,2) +
                Math.pow(outerCircleCenterPositionY - touchPositionY,2)
        );
        return joystickCenterToTouchDistance < outerCircleRadius;
    }

    public void setIsPressed(boolean isPressed) {
        this.isPressed = isPressed;
    }

    public boolean getIsPressed() {
        return isPressed;
    }

    public void setActuator(double touchPositionX, double touchPositionY) {
        //how far away we are pulling the joystick
        double deltaX = touchPositionX - outerCircleCenterPositionX;
        double deltaY = touchPositionY - outerCircleCenterPositionY;
        double deltaDistance = Math.sqrt(Math.pow(deltaX,2) + Math.pow(deltaY,2));

        //measure how far we have pulled joystick and divide by distance of center of joystick and border
        if(deltaDistance < outerCircleRadius){
            actuatorX = deltaX/outerCircleRadius;
            actuatorY = deltaY/outerCircleRadius;
        } else {
            actuatorX = deltaX/deltaDistance;
            actuatorY = deltaY/deltaDistance;
        }
    }

    public void resetActuator() {
        actuatorX = 0;
        actuatorY = 0;
    }

    public double getActuatorX() {
        return actuatorX;
    }
    public double getActuatorY() {
        return actuatorY;
    }
}
