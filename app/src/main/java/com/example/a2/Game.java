package com.example.a2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;


// game manages all obj in game and is responsible for updating all states & render all obj to screen

public class Game extends SurfaceView implements SurfaceHolder.Callback {
    private final Player player;
    private final Joystick joystick;
    private GameLoop gameLoop;

    public Game(Context context) {
        super(context);

        // get surface holder and add callback
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        gameLoop = new GameLoop(this, surfaceHolder);

        // initialize game objects
        joystick = new Joystick(175,850,50,20);

        // initialize player
        player = new Player(getContext(), 500, 500);

        setFocusable(true);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // handle touch event actions
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(joystick.isPressed(event.getX(),event.getY())){
                    joystick.setIsPressed(true);
                }
                // sets player where ever user touches on screen
                //player.setPosition(event.getX(), event.getY());
                return true;
            case MotionEvent.ACTION_MOVE:
                if(joystick.getIsPressed()){
                    joystick.setActuator(event.getX(),event.getY());
                }
                return true;
            case MotionEvent.ACTION_UP:
                joystick.setIsPressed(false);
                joystick.resetActuator();
                return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }

    // add all rendering calls to draw method
    // canvas is the class that you draw things on
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawUPS(canvas);
        drawFPS(canvas);

        joystick.draw(canvas);
        player.draw(canvas);
    }

    public void drawUPS(Canvas canvas) {
        String averageUPS = Double.toString(gameLoop.getAverageUPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(22);
        canvas.drawText("UPS: " + averageUPS, 100, 100, paint);
    }

    public void drawFPS(Canvas canvas) {
        String averageFPS = Double.toString(gameLoop.getAverageFPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(22);
        canvas.drawText("FPS: " + averageFPS, 100, 150, paint);
    }


    public void update() {
        // update game state
        joystick.update();
        player.update(joystick);
    }
}
