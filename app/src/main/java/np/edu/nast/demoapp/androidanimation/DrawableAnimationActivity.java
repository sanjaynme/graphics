package np.edu.nast.demoapp.androidanimation;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;

public class DrawableAnimationActivity extends Activity {
    AnimationDrawable slideShowAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable_animation);
        // ...

        ImageView slideShow = (ImageView) findViewById(R.id.imageViewSlideShow);
        slideShow.setBackgroundResource(R.drawable.slide_show);
        slideShowAnimation = (AnimationDrawable) slideShow.getBackground();
    }

    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            slideShowAnimation.start();
            return true;
        }

        return super.onTouchEvent(event);
    }
}
