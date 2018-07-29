package np.edu.nast.demoapp.androidanimation;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;


public class RotateTextViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RotateTextView rotateTextView = new RotateTextView(this);
        setContentView(rotateTextView);
    }

    /**
     * Created by Sanjay Tamata on 29/07/2018.
     */
    public static class RotateTextView extends View {

        private String message = "    Sanjay is testing";

        public RotateTextView(Context context) {
            super(context);
            this.setBackgroundColor(Color.GRAY);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            int viewWidth = getWidth();
            int viewHeight = getHeight();
            canvas.translate(viewWidth/2, viewHeight/2);

            Paint paint = new Paint();
            paint.setColor(Color.WHITE);
            paint.setAntiAlias(true);
            paint.setTextSize(45f);
            paint.setStrokeWidth(2.0f);
            paint.setStyle(Paint.Style.STROKE);
            paint.setShadowLayer(5.0f, 10.0f, 10.0f, Color.BLACK);

            for(int i = 0; i < 10; i++) {

                canvas.drawText(message, 0, 0, paint);

                canvas.rotate(36);
            }
        }
    }
}
