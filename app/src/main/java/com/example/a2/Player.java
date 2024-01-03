package com.example.a2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

public class Player {
    private double positionX;
    private double positionY;
    private Bitmap playerBitmap;

    // adjust the bitmap img scale factor based on desired size
    private static final float SCALE_FACTOR = .01f;

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
                (float) positionX-playerBitmap.getWidth()/2,
                (float) positionY-playerBitmap.getHeight()/2,
                null);
    }

    public void update() {
    }

    public void setPosition(double positionX, double positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }
}
