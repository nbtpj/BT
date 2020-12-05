package interfaces;

import engine.Sprite;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public interface Collision {

    default void setupCircleBound(Circle collisionBound, double x, double y, double r) {
        collisionBound.setTranslateX(x);
        collisionBound.setTranslateY(y);
        collisionBound.setRadius(r);
    }

    default void setupRectangleBound(Rectangle collisionBound, double x, double y, double height, double width) {
        collisionBound.setTranslateX(x);
        collisionBound.setTranslateY(y);
        collisionBound.setHeight(height);
        collisionBound.setWidth(width);
    }

    default boolean collideCircleCircle(Circle C1, Circle C2) {
        double R1 = C1.getRadius();
        double R2 = C2.getRadius();
        double X1 = C1.getTranslateX();
        double Y1 = C1.getTranslateY();
        double X2 = C2.getTranslateX();
        double Y2 = C2.getTranslateY();
        return (X1 - X2) * (X1 - X2) + (Y1 - Y2) * (Y1 - Y2) < (R1 + R2) * (R1 + R2);
    }

    default boolean inCircle(double x, double y, double xC, double yC, double rC) {
        return (x - xC) * (x - xC) + (y - yC) * (y - yC) < rC * rC;
    }

    default boolean inRectangle(double x, double y, double x1, double x2, double y1, double y2) {
        return (x >= x1 && x <= x2 && y >= y1 && y <= y2);
    }

    default boolean collideCircleRectangle(Circle C, Rectangle R) {
        double xC1 = C.getTranslateX();
        double xC2 = C.getTranslateX() + C.getRadius();
        double xC3 = C.getTranslateX() + 2 * C.getRadius();
        double yC1 = C.getTranslateY();
        double yC2 = C.getTranslateY() + C.getRadius();
        double yC3 = C.getTranslateY() + 2 * C.getRadius();
        double xR1 = R.getTranslateX();
        double xR2 = R.getTranslateX() + R.getWidth();
        double yR1 = R.getTranslateY();
        double yR2 = R.getTranslateY() + R.getHeight();

        return inCircle(xR1, yR1, C.getTranslateX(), C.getTranslateY(), C.getRadius()) ||
                inCircle(xR1, yR2, C.getTranslateX(), C.getTranslateY(), C.getRadius()) ||
                inCircle(xR2, yR1, C.getTranslateX(), C.getTranslateY(), C.getRadius()) ||
                inCircle(xR2, yR2, C.getTranslateX(), C.getTranslateY(), C.getRadius()) ||
                inRectangle(xC1, yC2, xR1, xR2, yR1, yR2) ||
                inRectangle(xC3, yC2, xR1, xR2, yR1, yR2) ||
                inRectangle(xC2, yC1, xR1, xR2, yR1, yR2) ||
                inRectangle(xC2, yC3, xR1, xR2, yR1, yR2);
    }

    default boolean collideRectangleRectangle(Rectangle R1, Rectangle R2) {
        double xR11 = R1.getTranslateX();
        double xR12 = R1.getTranslateX() + R1.getWidth() - 1;
        double yR11 = R1.getTranslateY();
        double yR12 = R1.getTranslateY() + R1.getHeight() - 1;
        double xR21 = R2.getTranslateX();
        double xR22 = R2.getTranslateX() + R2.getWidth() - 1;
        double yR21 = R2.getTranslateY();
        double yR22 = R2.getTranslateY() + R1.getHeight() - 1;

        return inRectangle(xR11, yR11, xR21, xR22, yR21, yR22) ||
                inRectangle(xR11, yR12, xR21, xR22, yR21, yR22) ||
                inRectangle(xR12, yR11, xR21, xR22, yR21, yR22) ||
                inRectangle(xR12, yR12, xR21, xR22, yR21, yR22) ||
                inRectangle(xR21, yR21, xR11, xR12, yR11, yR12) ||
                inRectangle(xR21, yR22, xR11, xR12, yR11, yR12) ||
                inRectangle(xR22, yR21, xR11, xR12, yR11, yR12) ||
                inRectangle(xR22, yR22, xR11, xR12, yR11, yR12);

    }

    default boolean collide(Collision other) {
        if (this instanceof Sprite) {
            if (((Sprite) this).collisionBound instanceof Circle && ((Sprite) other).collisionBound instanceof Circle) {
                return collideCircleCircle(
                        (Circle) ((Sprite) this).collisionBound,
                        (Circle) ((Sprite) other).collisionBound);
            }
            if (((Sprite) this).collisionBound instanceof Circle && ((Sprite) other).collisionBound instanceof Rectangle) {
                return collideCircleRectangle(
                        (Circle) ((Sprite) this).collisionBound,
                        (Rectangle) ((Sprite) other).collisionBound);
            }
            if (((Sprite) this).collisionBound instanceof Rectangle && ((Sprite) other).collisionBound instanceof Circle) {
                return collideCircleRectangle(
                        (Circle) ((Sprite) other).collisionBound,
                        (Rectangle) ((Sprite) this).collisionBound);
            }
            if (((Sprite) this).collisionBound instanceof Rectangle && ((Sprite) other).collisionBound instanceof Rectangle) {
                return collideRectangleRectangle(
                        (Rectangle) ((Sprite) this).collisionBound,
                        (Rectangle) ((Sprite) other).collisionBound);
            }
        }
        return false;
    }
}
