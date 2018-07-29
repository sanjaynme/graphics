package np.edu.nast.demoapp.androidanimation;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void getPropertyAnimation(View view) {
        Intent intent = new Intent(this, PropertyAnimationActivity.class);
        startActivity(intent);

    }

    public void getViewAnimation(View view) {
        Intent intent = new Intent(this, ViewAnimationActivity.class);
        startActivity(intent);

    }

    public void getDrawableAnimation(View view) {
        Intent intent = new Intent(this,   DrawableAnimationActivity.class);
        startActivity(intent);

    }

    public void getCanvas(View view) {
        Intent intent = new Intent(this,   ShapeDrawableActivity.class);
        startActivity(intent);

    }

    public void getTransformation(View view) {
        Intent intent = new Intent(this,   RotateTextViewActivity.class);
        startActivity(intent);

    }

    public void getOpengles(View view) {
        Intent intent = new Intent(this,   OpenGLActivity.class);
        startActivity(intent);

    }
}
