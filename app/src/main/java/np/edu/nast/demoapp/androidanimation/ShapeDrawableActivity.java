package np.edu.nast.demoapp.androidanimation;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class ShapeDrawableActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ShapeDrawableView shapeDrawableView = new ShapeDrawableView(this);
        setContentView(shapeDrawableView);
    }
}
