package mpizzle.gameoflifething;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        GameOfLifeView lifeView = (GameOfLifeView) findViewById(R.id.pixelgrid);

        ObjectAnimator animation = ObjectAnimator.ofInt(lifeView, "nextStep", 0, 0);
        animation.setRepeatCount(ObjectAnimator.INFINITE);
        lifeView.animator=animation;

        Button playPauseButton = (Button) findViewById(R.id.btn_play_pause);
        Button frameAdvanceButton = (Button) findViewById(R.id.btn_frame_advance);
        Button settingsButton = (Button) findViewById(R.id.btn_settings);
        setButtonsOnClick(playPauseButton, frameAdvanceButton, settingsButton, animation);
    }

    private void setButtonsOnClick(final Button playPause, final Button frameAdvance, final Button settings, final ObjectAnimator animation) {
        playPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (animation.isRunning()) {
                    animation.end();
                    playPause.setText("play");
                    frameAdvance.setVisibility(View.VISIBLE);
                    settings.setVisibility(View.VISIBLE);
                } else {
                    animation.start();
                    playPause.setText("pause");
                    frameAdvance.setVisibility(View.INVISIBLE);
                    settings.setVisibility(View.INVISIBLE);
                }
            }
        });

        frameAdvance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!animation.isRunning()) {
                    GameOfLifeView lifeView = (GameOfLifeView) findViewById(R.id.pixelgrid);
                    lifeView.setNextStep(0);
                }
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
}