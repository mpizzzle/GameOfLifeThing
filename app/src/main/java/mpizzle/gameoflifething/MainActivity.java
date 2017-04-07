package mpizzle.gameoflifething;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        PixelGridView p = (PixelGridView)findViewById(R.id.pixelgrid);
        ObjectAnimator anim = ObjectAnimator.ofInt(p, "customIntProperty", 0, 0);
        anim.setRepeatCount(ObjectAnimator.INFINITE);
        anim.start();
    }
}
