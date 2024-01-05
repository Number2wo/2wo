package com.example.a2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Enemy extends GameObject {
    private static final float SCALE_FACTOR = .2f;
    public Enemy(Context context, double positionX, double positionY) {
        super(positionX, positionY);

        // create method to pass this in as a parameter for the Enemy constructor
        // enemy image
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.desmond);
        // scale the bitmap based on the desired size
        int scaledWidth = (int) (bitmap.getWidth() * SCALE_FACTOR);
        int scaledHeight = (int) (bitmap.getHeight() * SCALE_FACTOR);
        bitmap = Bitmap.createScaledBitmap(bitmap, scaledWidth, scaledHeight, false);

    }
    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(
                bitmap,
                (float) positionX-(float) bitmap.getWidth()/2,
                (float) positionY-(float) bitmap.getHeight()/2,
                null);
    }
    @Override
    public void update() {
        // update velocity of enemy in the direction of the player


    }
}
