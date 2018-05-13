package knightsoul.chessclockv2;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import org.w3c.dom.Text;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView player1initial;//invisible
    TextView player1increment;
    TextView player2initial;//invisible
    TextView player2increment;
    TextView tv_player1initial;
    TextView tv_player2initial;
    TextView tv_player1increment;//invisible
    TextView tv_player2increment;   //invisible


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        player1initial = (TextView) findViewById(R.id.et_player1initial);
        player2initial = (TextView) findViewById(R.id.et_player2initial);
        player1increment = (TextView) findViewById(R.id.et_player1increment);
        player2increment = (TextView) findViewById(R.id.et_player2increment);
        tv_player1initial = (TextView) findViewById(R.id.tv_player1initial);
        tv_player2initial = (TextView) findViewById(R.id.tv_player2initial);

        tv_player1increment = (TextView) findViewById(R.id.tv_player1increment);
        tv_player2increment = (TextView) findViewById(R.id.tv_player2increment);


        Button secondActivityBtn = (Button) findViewById(R.id.btn_start);
        secondActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), Main2Activity.class);
                //Show how to pass information to another activity

                startIntent.putExtra("player1initial", player1initial.getText().toString());
                startIntent.putExtra("player2initial", player2initial.getText().toString());
                startIntent.putExtra("player1increment", tv_player1increment.getText().toString());
                startIntent.putExtra("player2increment", tv_player2increment.getText().toString());
                startActivity(startIntent);
            }
        });
    }

    public void setp1Time(View view) {
        Calendar calendar = Calendar.getInstance();
        int hour = 0;
        int minute = 5;

        TimePickerDialog timePickerDialog;

        timePickerDialog = new TimePickerDialog(MainActivity.this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar, new TimePickerDialog.OnTimeSetListener() {
            public void onTimeSet(TimePicker view, int hour, int minute) {
                int seconds = (hour * 3600) + (minute * 60);
                player1initial.setText("" + seconds);
                if (hour < 10)
                    if (minute < 10)
                        tv_player1initial.setText("0" + hour + ":0" + minute + ":00");
                    else
                        tv_player1initial.setText("0" + hour + ":" + minute + ":00");

                else
                if (minute < 10)
                    tv_player1initial.setText(hour + ":0" + minute + ":00");
                else
                    tv_player1initial.setText(hour + ":" + minute + ":00");

            }
        },hour,minute,true);
        timePickerDialog.setTitle("Set Initial Time (HOUR:MINUTE)");
        timePickerDialog.show();

    }

    public void setp2Time(View view) {
        Calendar calendar = Calendar.getInstance();
        int hour = 0;
        int minute = 10;

        final TimePickerDialog timePickerDialog;

        timePickerDialog = new TimePickerDialog(MainActivity.this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar, new TimePickerDialog.OnTimeSetListener() {

            public void onTimeSet(TimePicker view, int hour, int minute) {
                int seconds = (hour * 3600) + (minute * 60);
                player2initial.setText("" + seconds);
                if (hour < 10)
                    if (minute < 10)
                        tv_player2initial.setText("0" + hour + ":0" + minute + ":00");
                    else
                        tv_player2initial.setText("0" + hour + ":" + minute + ":00");

                else
                    if (minute < 10)
                        tv_player2initial.setText(hour + ":0" + minute + ":00");
                    else
                        tv_player2initial.setText(hour + ":" + minute + ":00");


            }
        },hour,minute,true);
        timePickerDialog.setTitle("Set Initial Time (HOUR:MINUTE)");
        timePickerDialog.show();

    }

    public void setp1Increment(View view) {
        int minute = 0;
        int second = 5;

        final TimePickerDialog timePickerDialog;

        timePickerDialog = new TimePickerDialog(MainActivity.this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar, new TimePickerDialog.OnTimeSetListener() {

            public void onTimeSet(TimePicker view, int minute, int second) {
                int seconds = (minute * 60) + (second);
                tv_player1increment.setText("" + seconds);

                if (second >= 10)
                    player1increment.setText(minute + ":" + second);
                else
                    player1increment.setText(minute + ":0" + second);

            }
        },minute,second,true);
        timePickerDialog.setTitle("Select Increment (MINUTE:SECOND)");
        timePickerDialog.show();

    }

    public void setp2Increment(View view) {
        int minute = 0;
        int second = 5;

        final TimePickerDialog timePickerDialog;

        timePickerDialog = new TimePickerDialog(MainActivity.this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar, new TimePickerDialog.OnTimeSetListener() {

            public void onTimeSet(TimePicker view, int minute, int second) {
                int seconds = (minute * 60) + (second);
                tv_player2increment.setText("" + seconds);
                if (second >= 10)
                    player2increment.setText(minute + ":" + second);
                else
                    player2increment.setText(minute + ":0" + second);

            }
        },minute,second,true);
        timePickerDialog.setTitle("Select Increment (MINUTE:SECOND)");
        timePickerDialog.show();

    }
}
