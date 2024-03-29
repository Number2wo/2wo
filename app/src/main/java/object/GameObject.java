package object;
import android.graphics.Bitmap;
import android.graphics.Canvas;
/** GameObject is an abstract class which is the foundation of all world objects in the game.
 * */
public abstract class GameObject {
    protected double positionX;
    protected double positionY;
    protected double velocityX;
    protected double velocityY;
    protected Bitmap bitmap;

    public GameObject(double positionX, double positionY){
        this.positionX = positionX;
        this.positionY = positionY;
    }

    protected static double getDistanceBetweenObjects(GameObject obj1, GameObject obj2) {
        return Math.sqrt(
                Math.pow(obj2.getPositionX() - obj1.getPositionX(),2) +
                Math.pow(obj2.getPositionY() - obj1.getPositionY(),2)
        );
    }

    public abstract void draw(Canvas canvas);
    public abstract void update();

    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }
}
