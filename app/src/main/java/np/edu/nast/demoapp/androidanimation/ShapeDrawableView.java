package np.edu.nast.demoapp.androidanimation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.Shape;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sanjay Tamata on 29/07/2018.
 */
public class ShapeDrawableView extends View {

    int startX, startY, endX, endY;

    private List<ShapeDrawable> shapeDrawables =
            new ArrayList<>();

    public ShapeDrawableView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (ShapeDrawable shapeDrawable : shapeDrawables) {
            shapeDrawable.draw(canvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            startX = (int) event.getX();
            startY = (int) event.getY();
            return true;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            endX = (int) event.getX();
            endY = (int) event.getY();
            if (startX > endX) {
                int tmp = startX;
                startX = endX;
                endX = tmp;
            }
            if (startY > endY) {
                int tmp = startY;
                startY = endY;
                endY = tmp;
            }

            Shape shape = new RectShape();
            ShapeDrawable shapeDrawable = new ShapeDrawable(shape);
            shapeDrawable.setBounds(startX, startY, endX, endY);
            shapeDrawable.getPaint().setColor(Color.BLUE);
            shapeDrawables.add(shapeDrawable);
            invalidate();

            return true;
        }
        return false;
    }
}