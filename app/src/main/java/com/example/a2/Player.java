package com.example.a2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Player {
    private static final double SPEED_PIXELS_PER_SECOND = 422.22;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;
    private double positionX;
    private double positionY;
    private Bitmap playerBitmap;

    // adjust the bitmap img scale factor based on desired size
    private static final float SCALE_FACTOR = .1f;
    private double velocityX;
    private double velocityY;

    public Player(Context context, double positionX, double positionY) {
        this.positionX = positionX;
        this.positionY = positionY;

        playerBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.jr);

        //scale the bitmap based on the desired size
        int scaledWidth = (int) (playerBitmap.getWidth() * SCALE_FACTOR);
        int scaledHeight = (int) (playerBitmap.getHeight() * SCALE_FACTOR);
        playerBitmap = Bitmap.createScaledBitmap(playerBitmap, scaledWidth, scaledHeight, false);

    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(
                playerBitmap,
                (float) positionX-(float)playerBitmap.getWidth()/2,
                (float) positionY-(float)playerBitmap.getHeight()/2,
                null);
    }

    public void update(Joystick joystick) {
        velocityX = joystick.getActuatorX()*MAX_SPEED;
        velocityY= joystick.getActuatorY()*MAX_SPEED;
        positionX += velocityX;
        positionY += velocityY;
    }

    public void setPosition(double positionX, double positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }
}
