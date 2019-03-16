package jasontian.chessclockpro.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RadioGroup;
import android.widget.Toast;

import jasontian.chessclockpro.Preferences.PreferenceManager;
import jasontian.chessclockpro.R;

public class TimePick extends AppCompatActivity {

    private TabLayout tabLayout;
    private EditText name;

    private CheckBox timeOddsBox;
    private boolean timeOdds = false;
    private static int currentPlayer = 1;

    private LinearLayout increPicker;

    private NumberPicker timeSeconds;
    private NumberPicker timeMinutes;
    private NumberPicker timeHours;
    private NumberPicker incSeconds;
    private NumberPicker incMinutes;
    private NumberPicker incHours;

    private RadioGroup incrementGroup;

    private ImageView iv_bullet;
    private ImageView iv_blitz;
    private ImageView iv_rapid;
    private ImageView iv_classical;
    private ImageView iv_horse;
    private ImageView iv_chess;

    private String tag;

    private static int initial1 = 900;
    private static int incrementType1;
    private static int increment1 = 10;
    private static int initial2 = 900;
    private static int incrementType2;
    private static int increment2 = 10;
    private static String settingName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timepicker);
        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        name = findViewById(R.id.name);

        tabLayout = findViewById(R.id.tablayout);
        tabLayout.setEnabled(false);
        tabLayout.setVisibility(View.GONE);

        timeOddsBox = findViewById(R.id.checkBox);

        increPicker = findViewById(R.id.increment_picker);

        timeSeconds = findViewById(R.id.time_seconds);
        timeMinutes = findViewById(R.id.time_minutes);
        timeHours = findViewById(R.id.time_hours);

        incSeconds = findViewById(R.id.inc_seconds);
        incMinutes = findViewById(R.id.inc_minutes);
        incHours = findViewById(R.id.inc_hours);

        timeHours.setMaxValue(23);
        timeMinutes.setMaxValue(59);
        timeSeconds.setMaxValue(59);

        incHours.setMaxValue(23);
        incMinutes.setMaxValue(59);
        incSeconds.setMaxValue(59);

        timeSeconds.setValue(0);
        timeMinutes.setValue(15);
        timeHours.setValue(0);

        incSeconds.setValue(0);
        incMinutes.setValue(15);
        incHours.setValue(0);

        incrementGroup = findViewById(R.id.incrementGroup);

        iv_bullet = findViewById(R.id.iv_bullet);
        iv_blitz = findViewById(R.id.iv_blitz);
        iv_rapid = findViewById(R.id.iv_rapid);
        iv_classical = findViewById(R.id.iv_classical);
        iv_horse = findViewById(R.id.iv_horse);
        iv_chess = findViewById(R.id.iv_chess);

        selectIcon(iv_bullet);
        tag = "bullet";

        incrementGroup.check(incrementGroup.getChildAt(0).getId());
        incrementType1 = incrementGroup.getChildAt(0).getId();
        incrementType2 = incrementGroup.getChildAt(0).getId();

        increPicker.setVisibility(View.INVISIBLE);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    //tabLayout.getTabAt(0).select();
                    currentPlayer = 1;
                    updateValues(2);
                    //update clock
                    updateClock(initial1, incrementType1, increment1);
                } else if (tab.getPosition() == 1) {
                    //tabLayout.getTabAt(1).select();
                    currentPlayer = 2;
                    updateValues(1);
                    //Update clock
                    updateClock(initial2, incrementType2, increment2);
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        timeOddsBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    timeOdds = true;
                    tabLayout.setEnabled(true);
                    tabLayout.setVisibility(View.VISIBLE);
                } else {
                    timeOdds = false;
                    updateValues(currentPlayer);
                    currentPlayer = 1;
                    updateClock(initial1, incrementType1, increment1);
                    tabLayout.setEnabled(false);
                    tabLayout.setVisibility(View.GONE);
                }
            }
        });

        incrementGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == group.getChildAt(0).getId()
                        || checkedId == group.getChildAt(2).getId()) {
                    increPicker.setVisibility(View.INVISIBLE);
                } else {
                    increPicker.setVisibility(View.VISIBLE);
                }
            }
        });

        iv_bullet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectIcon(iv_bullet);
                tag = "bullet";
            }
        });

        iv_blitz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectIcon(iv_blitz);
                tag = "blitz";
            }
        });

        iv_rapid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectIcon(iv_rapid);
                tag = "rapid";
            }
        });

        iv_classical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectIcon(iv_classical);
                tag = "classical";
            }
        });

        iv_horse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectIcon(iv_horse);
                tag = "horse";
            }
        });

        iv_chess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectIcon(iv_chess);
                tag = "chess";
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.timeroptions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.add:
                //Error Checking
                String setting = name.getText().toString().trim();
                if (setting.equals("times")) {
                    Toast.makeText(getApplicationContext(), "Invalid Setting Name",
                            Toast.LENGTH_LONG).show();
                    return true;
                } else if (setting.equals("start")) {
                    Toast.makeText(getApplicationContext(), "Invalid Setting Name",
                            Toast.LENGTH_LONG).show();

                    return true;
                } else if (setting.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please choose a setting name",
                            Toast.LENGTH_LONG).show();
                    return true;
                } else if (setting.contains(",")) {
                    Toast.makeText(getApplicationContext(), "The setting name cannot contain commas",
                            Toast.LENGTH_LONG).show();
                    return true;
                } else if (PreferenceManager.exists(this, setting)) {
                    Toast.makeText(getApplicationContext(), "A setting with the same name exists",
                            Toast.LENGTH_LONG).show();
                    return true;
                }

                if (!timeOdds) {
                    updateValues(1);
                    updateValues(2);
                } else {
                    updateValues(tabLayout.getSelectedTabPosition() + 1);
                }

                int type1, type2;

                if (incrementType1 == incrementGroup.getChildAt(0).getId())
                    type1 = 1;
                else if (incrementType1 == incrementGroup.getChildAt(1).getId())
                    type1 = 2;
                else
                    type1 = 3;

                if (incrementType2 == incrementGroup.getChildAt(0).getId())
                    type2 = 1;
                else if (incrementType2 == incrementGroup.getChildAt(1).getId())
                    type2 = 2;
                else
                    type2 = 3;

                SelectionScreen.preferenceManager.addSetting(this, setting);
                SelectionScreen.preferences.addSetting(this, setting, String.valueOf(initial1),
                        String.valueOf(type1), String.valueOf(increment1),
                                String.valueOf(initial2), String.valueOf(type2),
                                        String.valueOf(increment2), String.valueOf(tag));

                Intent intent = new Intent(getApplicationContext(), SelectionScreen.class);
                startActivity(intent);
                finish();
                return true;

            case R.id.cancel:
                Intent intent1 = new Intent(getApplicationContext(), SelectionScreen.class);
                startActivity(intent1);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public void updateValues(int player) {
         if (player == 2) {
             initial2 = (timeHours.getValue() * 3600) + (timeMinutes.getValue() * 60) + timeSeconds.getValue();
             incrementType2 = incrementGroup.getCheckedRadioButtonId();
             increment2 = (incHours.getValue() * 3600) + (incMinutes.getValue() * 60) + incSeconds.getValue();
         } else {
             initial1 = (timeHours.getValue() * 3600) + (timeMinutes.getValue() * 60) + timeSeconds.getValue();
             incrementType1 = incrementGroup.getCheckedRadioButtonId();
             increment1 = (incHours.getValue() * 3600) + (incMinutes.getValue() * 60) + incSeconds.getValue();
         }
    }

    public void updateClock(int initial, int incrementType, int increment) {
        timeHours.setValue(initial / 3600);
        timeMinutes.setValue((initial - ((initial / 3600) * (3600))) / 60);
        timeSeconds.setValue(initial - ((initial / 60) * 60));
        incrementGroup.check(incrementType);
        incHours.setValue(increment / 3600);
        incMinutes.setValue((increment - ((increment / 3600) * (3600))) / 60);
        incSeconds.setValue(increment - ((increment / 60) * 60));
    }

    public void selectIcon(ImageView view) {
        iv_bullet.setPadding(0, 0, 0, 0);
        iv_blitz.setPadding(0, 0, 0, 0);
        iv_rapid.setPadding(0, 0, 0, 0);
        iv_classical.setPadding(0, 0, 0, 0);
        iv_horse.setPadding(0, 0, 0, 0);
        iv_chess.setPadding(0, 0, 0, 0);

        iv_bullet.setBackgroundColor(Color.TRANSPARENT);
        iv_blitz.setBackgroundColor(Color.TRANSPARENT);
        iv_rapid.setBackgroundColor(Color.TRANSPARENT);
        iv_classical.setBackgroundColor(Color.TRANSPARENT);
        iv_horse.setBackgroundColor(Color.TRANSPARENT);
        iv_chess.setBackgroundColor(Color.TRANSPARENT);

        view.setBackgroundColor(Color.DKGRAY);
        view.setPadding(4, 4, 4, 4);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), SelectionScreen.class);
        startActivity(intent);
        finish();
    }
}