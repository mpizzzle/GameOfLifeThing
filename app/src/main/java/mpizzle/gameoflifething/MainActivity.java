package mpizzle.gameoflifething;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GameOfLifeView lifeView = (GameOfLifeView) findViewById(R.id.pixelgrid);

        ObjectAnimator animation = ObjectAnimator.ofInt(lifeView, "nextStep", 0, 0);
        animation.setRepeatCount(ObjectAnimator.INFINITE);
        lifeView.setAnimator(animation);

        LinearLayout settings_menu = (LinearLayout) findViewById(R.id.settings_menu);
        settings_menu.setVisibility(View.INVISIBLE);

        Button playPauseButton = (Button) findViewById(R.id.btn_play_pause);
        Button frameAdvanceButton = (Button) findViewById(R.id.btn_frame_advance);
        Button randomizeButton = (Button) findViewById(R.id.randomize_button);
        Button clearButton = (Button) findViewById(R.id.clear_button);
        Button settingsButton = (Button) findViewById(R.id.btn_settings);

        CheckBox wrappingCheckBox = (CheckBox) findViewById(R.id.wrapping_checkbox);
        CheckBox drawGridCheckBox = (CheckBox) findViewById(R.id.grid_checkbox);

        RadioButton palette1 = (RadioButton) findViewById(R.id.radio_single);
        RadioButton palette2 = (RadioButton) findViewById(R.id.radio_heated);
        RadioButton palette3 = (RadioButton) findViewById(R.id.radio_rainbow);

        setMainButtons(playPauseButton, frameAdvanceButton, settingsButton,  animation);
        setSettingsButtons(wrappingCheckBox, drawGridCheckBox, palette1, palette2, palette3, randomizeButton, clearButton);
    }

    private void setMainButtons(final Button playPause, final Button frameAdvance, final Button settings, final ObjectAnimator animation) {
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
                LinearLayout settings_menu = (LinearLayout) findViewById(R.id.settings_menu);
                settings_menu.setVisibility((settings_menu.getVisibility() == View.VISIBLE) ? View.INVISIBLE : View.VISIBLE);
            }
        });
    }

    private void setSettingsButtons(final CheckBox wrapping, final CheckBox grid, final RadioButton palette1, final RadioButton palette2, final RadioButton palette3, final Button randomize, final Button clear) {
        wrapping.setChecked(true);
        wrapping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameOfLifeView lifeView = (GameOfLifeView) findViewById(R.id.pixelgrid);
                lifeView.setWrapping(wrapping.isChecked());
            }
        });

        grid.setChecked(true);
        grid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameOfLifeView lifeView = (GameOfLifeView) findViewById(R.id.pixelgrid);
                lifeView.setDrawGrid(grid.isChecked());
            }
        });

        palette2.setChecked(true);

        palette1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameOfLifeView lifeView = (GameOfLifeView) findViewById(R.id.pixelgrid);
                lifeView.setPalette(0);
            }
        });
        palette2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameOfLifeView lifeView = (GameOfLifeView) findViewById(R.id.pixelgrid);
                lifeView.setPalette(1);
            }
        });
        palette3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameOfLifeView lifeView = (GameOfLifeView) findViewById(R.id.pixelgrid);
                lifeView.setPalette(2);
            }
        });

        randomize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameOfLifeView lifeView = (GameOfLifeView) findViewById(R.id.pixelgrid);
                lifeView.randomizeCellMap();
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameOfLifeView lifeView = (GameOfLifeView) findViewById(R.id.pixelgrid);
                lifeView.clearCellMap();
            }
        });
    }
}



