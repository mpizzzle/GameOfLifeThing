package mpizzle.gameoflifething;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        GameOfLifeView lifeView = (GameOfLifeView) findViewById(R.id.pixelgrid);

        ObjectAnimator animation = ObjectAnimator.ofInt(lifeView, "nextStep", 0, 0);
        animation.setRepeatCount(ObjectAnimator.INFINITE);
        lifeView.setAnimator(animation);

        LinearLayout settings_menu = (LinearLayout) findViewById(R.id.settings_menu);
        settings_menu.setVisibility(View.INVISIBLE);

        Button playPauseButton = (Button) findViewById(R.id.btn_play_pause);
        Button frameAdvanceButton = (Button) findViewById(R.id.btn_frame_advance);
        Button settingsButton = (Button) findViewById(R.id.btn_settings);

        setMainButtons(playPauseButton, frameAdvanceButton, settingsButton, animation);

        CheckBox wrappingCheckBox = (CheckBox) findViewById(R.id.wrapping_checkbox);
        CheckBox drawGridCheckBox = (CheckBox) findViewById(R.id.grid_checkbox);
        CheckBox paletteCheckBox = (CheckBox) findViewById(R.id.heatmap_checkbox);
        Button randomizeButton = (Button) findViewById(R.id.randomize_button);
        Button clearButton = (Button) findViewById(R.id.clear_button);

        setSettingsButtons(wrappingCheckBox, drawGridCheckBox, paletteCheckBox, randomizeButton, clearButton);
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

    private void setSettingsButtons(final CheckBox wrapping, final CheckBox grid, final CheckBox palette, final Button randomize, final Button clear) {
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

        palette.setChecked(true);
        palette.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameOfLifeView lifeView = (GameOfLifeView) findViewById(R.id.pixelgrid);
                lifeView.setPalette(palette.isChecked());
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



