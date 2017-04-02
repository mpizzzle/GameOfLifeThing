package mpizzle.gameoflifething;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(new PixelGridView(this));
        setContentView(R.layout.activity_main);
    }
}
