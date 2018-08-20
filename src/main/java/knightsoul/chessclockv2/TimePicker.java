package knightsoul.chessclockv2;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TimePicker extends AppCompatActivity {

    //Default selection - 10 minutes
    int p1initial = 600;  //Player 1's initial time in seconds
    int p2initial = 600;  //Player 2's initial time in seconds

    int p1increment = 0; //Player 1's increment in seconds
    int p2increment = 0; //Player 2's increment in seconds

    TextView tv_player1increment;
    TextView tv_player2increment;
    TextView tv_player1initial;
    TextView tv_player2initial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timepicker);
        tv_player1increment = findViewById(R.id.et_player1increment);
        tv_player2increment = findViewById(R.id.et_player2increment);
        tv_player1initial = findViewById(R.id.et_player1initial);
        tv_player2initial = findViewById(R.id.et_player2initial);

        Button start = findViewById(R.id.btn_start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), ClockActivity.class);

                //Passes initial times and increments to next activity
                startIntent.putExtra("tv_player1initial", p1initial);
                startIntent.putExtra("tv_player2initial", p2initial);
                startIntent.putExtra("tv_player1increment", p1increment);
                startIntent.putExtra("tv_player2increment", p2increment);
                startActivity(startIntent);
            }
        });
    }

    public void setp1Time(View view) {
        //Default selection
        int hour = 0;
        int minute = 5;

        TimePickerDialog timePickerDialog = new TimePickerDialog(TimePicker.this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar, new TimePickerDialog.OnTimeSetListener() {
            public void onTimeSet(android.widget.TimePicker view, int hour, int minute) {
                p1initial = hour * 3600 + minute * 60;
                if (hour < 10) {
                    if (minute < 10)
                        tv_player1initial.setText("0" + hour + ":0" + minute + ":00");
                    else
                        tv_player1initial.setText("0" + hour + ":" + minute + ":00");
                } else {
                    if (minute < 10)
                        tv_player1initial.setText(hour + ":0" + minute + ":00");
                    else
                        tv_player1initial.setText(hour + ":" + minute + ":00");
                }
            }
        },hour,minute,true);
        timePickerDialog.setTitle("Set Initial Time (HOUR:MINUTE)");
        timePickerDialog.show();
    }

    public void setp2Time(View view) {
        //Default selection
        int hour = 0;
        int minute = 10;

        TimePickerDialog timePickerDialog = new TimePickerDialog(TimePicker.this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar, new TimePickerDialog.OnTimeSetListener() {

            public void onTimeSet(android.widget.TimePicker view, int hour, int minute) {
                p2initial = hour * 3600 + minute * 60;
                if (hour < 10) {
                    if (minute < 10)
                        tv_player2initial.setText("0" + hour + ":0" + minute + ":00");
                    else
                        tv_player2initial.setText("0" + hour + ":" + minute + ":00");
                } else {
                    if (minute < 10)
                        tv_player2initial.setText(hour + ":0" + minute + ":00");
                    else
                        tv_player2initial.setText(hour + ":" + minute + ":00");
                }
            }
        },hour,minute,true);
        timePickerDialog.setTitle("Set Initial Time (HOUR:MINUTE)");
        timePickerDialog.show();
    }

    public void setp1Increment(View view) {
        int minute = 0;
        int second = 5;

        TimePickerDialog timePickerDialog = new TimePickerDialog(TimePicker.this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar, new TimePickerDialog.OnTimeSetListener() {

            public void onTimeSet(android.widget.TimePicker view, int minute, int second) {
                p1increment = (minute * 60) + second;
                if (second >= 10)
                    tv_player1increment.setText(minute + ":" + second);
                else
                    tv_player1increment.setText(minute + ":0" + second);
            }
        },minute,second,true);
        timePickerDialog.setTitle("Select Increment (MINUTE:SECOND)");
        timePickerDialog.show();
    }

    public void setp2Increment(View view) {
        int minute = 0;
        int second = 5;

        TimePickerDialog timePickerDialog = new TimePickerDialog(TimePicker.this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar, new TimePickerDialog.OnTimeSetListener() {

            public void onTimeSet(android.widget.TimePicker view, int minute, int second) {
                p2increment = (minute * 60) + second;
                if (second >= 10)
                    tv_player2increment.setText(minute + ":" + second);
                else
                    tv_player2increment.setText(minute + ":0" + second);
            }
        },minute,second,true);
        timePickerDialog.setTitle("Select Increment (MINUTE:SECOND)");
        timePickerDialog.show();
    }
}