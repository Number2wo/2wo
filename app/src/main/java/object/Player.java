package object;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.a2.GameLoop;
import com.example.a2.Joystick;
import com.example.a2.R;

/** Player is the main character of the game, which the user controls with the joystick
 *  the player is an extension of the GameObject
 **/
public class Player extends GameObject {
    public static final double SPEED_PIXELS_PER_SECOND = 422.22;
    public static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;
    private final Joystick joystick;
    // adjust the bitmap img scale factor based on desired size
    private static final float SCALE_FACTOR = .1f;

    public Player(Context context, Joystick joystick, double positionX, double positionY) {
       super(positionX,positionY);
       this.joystick = joystick;

       // create method to pass this in as a parameter for the Enemy constructor
       //player image
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.jr);
        //scale the bitmap based on the desired size
        int scaledWidth = (int) (bitmap.getWidth() * SCALE_FACTOR);
        int scaledHeight = (int) (bitmap.getHeight() * SCALE_FACTOR);
        bitmap = Bitmap.createScaledBitmap(bitmap, scaledWidth, scaledHeight, false);

    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(
                bitmap,
                (float) positionX-(float) bitmap.getWidth()/2,
                (float) positionY-(float) bitmap.getHeight()/2,
                null);
    }

    public void update() {
        // update velocity based on actuator of joystick
        velocityX = joystick.getActuatorX()*MAX_SPEED;
        velocityY= joystick.getActuatorY()*MAX_SPEED;
        // update position
        positionX += velocityX;
        positionY += velocityY;
    }
}
