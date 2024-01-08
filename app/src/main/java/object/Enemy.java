package object;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.a2.GameLoop;
import com.example.a2.R;

public class Enemy extends GameObject {
    private static final double SPEED_PIXELS_PER_SECOND = Player.SPEED_PIXELS_PER_SECOND/4;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;
    private static final float SCALE_FACTOR = .2f;
    private Player player;

    public Enemy(Context context, Player player, double positionX, double positionY) {
        super(positionX, positionY);
        this.player = player;

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

        // calc vector from enemy to player (in x and y)
        double distanceToPlayerX = player.getPositionX() - positionX;
        double distanceToPlayerY = player.getPositionY() - positionY;

        // calc (absolute) distance between enemy(this) and player
        double distanceToPlayer = GameObject.getDistanceBetweenObjects(this, player);
        // calc direction from enemy to player
        double directionX = distanceToPlayerX/distanceToPlayer;
        double directionY = distanceToPlayerY/distanceToPlayer;
        // set velocity in the direction to player
        if (distanceToPlayer > 0 ){
            velocityX = directionX*MAX_SPEED;
            velocityY = directionY*MAX_SPEED;
        } else {
            velocityX = 0;
            velocityY = 0;
        }
        // update the position of the enemy
        positionX += velocityX;
        positionY+= velocityY;
    }
}
