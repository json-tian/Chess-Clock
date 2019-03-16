package jasontian.chessclockpro.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import jasontian.chessclockpro.R;

public class ClockActivity extends AppCompatActivity {

    private int turn = 0; //0 - neither, 1-2 indicates that player's turn

    //Each player's current time in milliseconds
    private long p1Time = 0;
    private long p2Time = 0;

    //Each player's increment amount in milliseconds
    private long increment1 = 0;
    private long increment2 = 0;

    //Stores each player's original time (in case of reset)
    private long original1;
    private long original2;

    private int increType1;
    private int increType2;

    //private int NO_INCREMENT = 1;
    private int INCREMENT = 2;
    private int HOURGLASS = 3;

    private CountDownTimer p2Timer;
    private CountDownTimer p1Timer;

    AdView mAdView;

    private MediaPlayer moveSound;

    Button button1; //Player 1's button
    Button button2; //Player 2's button

    //Option buttons
    Button btn_pause;
    Button btn_switch;
    Button btn_back;
    Button btn_reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clockactivity);

        //Initialize sound
        moveSound = MediaPlayer.create(this, R.raw.move);

        // AdMob Initialization (Banner Ad)
        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        MobileAds.initialize(this, "ca-app-pub-3781115230256321~6975876312");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        button1 = findViewById(R.id.btn_player1);
        button2 = findViewById(R.id.btn_player2);
        btn_pause = findViewById(R.id.btn_pause);
        btn_back = findViewById(R.id.btn_back);
        btn_reset = findViewById(R.id.btn_reset);
        btn_switch = findViewById(R.id.btn_switch);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        btn_pause.setVisibility(View.INVISIBLE);

        //Retrieving information from previous activity (initial time, increment for each player)
        int num1 = getIntent().getExtras().getInt("time1");
        p1Time = num1 * 1000;
        original1 = p1Time;

        int num2 = getIntent().getExtras().getInt("time2");
        p2Time = num2 * 1000;
        original2 = p2Time;

        int num3 = getIntent().getExtras().getInt("incre1");
        increment1 = num3 * 1000;

        int num4 = getIntent().getExtras().getInt("incre2");
        increment2 = num4 * 1000;

        increType1 = getIntent().getExtras().getInt("increType1");
        increType2 = getIntent().getExtras().getInt("increType2");

        Log.d("INCREMENT PLAYER 1", String.valueOf(increType1));
        Log.d("INCREMENT PLAYER 2", String.valueOf(increType2));

        button1.setText(updateText(p1Time));
        button2.setText(updateText(p2Time));

        btn_reset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ClockActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Reset Timer?");
                builder.setMessage("Press 'Confirm' to reset timer.");
                builder.setPositiveButton("Confirm",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                reset();
                            }
                        });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        //Starts countdown for player1  (When player 2 clicks)
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button2.setEnabled(false);
                button1.setEnabled(true);
                btn_pause.setEnabled(true);
                btn_pause.setVisibility(View.VISIBLE);
                moveSound.start();

                button1.setBackgroundColor(Color.parseColor("#1AC02B"));
                button2.setBackgroundColor(Color.parseColor("#999999"));
                button1.setTextColor(Color.parseColor("#ffffff"));
                button2.setTextColor(Color.parseColor("#000000"));

                if (turn == 0 || turn == 2) {
                    p1Timer = new CountDownTimer(p1Time, 1) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            if (turn != 1) {    //No longer player 1's turn, stop timer
                                cancel();
                            } else {    //Otherwise, deduct time
                                if (increType2 == HOURGLASS) {
                                    p2Time -= ((millisUntilFinished - 1) - p1Time);
                                    button2.setText(updateText(p2Time));
                                }
                                p1Time = (millisUntilFinished - 1);
                                button1.setText(updateText(p1Time));
                            }
                        }

                        @Override
                        public void onFinish() {
                            button1.setBackgroundColor(Color.parseColor("#C0392B"));
                            button1.setTextColor(Color.parseColor("#ffffff"));
                            timeGone();
                        }
                    }.start();
                }

                if (turn == 2) {
                    p2Timer.cancel();
                    turn = 1;
                    if (increType2 == INCREMENT) {
                        p2Time += increment2;
                    }
                    button2.setText(updateText(p2Time));
                }

                turn = 1;
            }
        });

        //Starts countdown for player2  (When player 1 clicks)
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button1.setEnabled(false);
                button2.setEnabled(true);
                btn_pause.setVisibility(View.VISIBLE);
                btn_pause.setEnabled(true);
                moveSound.start();

                button1.setBackgroundColor(Color.parseColor("#999999"));
                button2.setBackgroundColor(Color.parseColor("#1AC02B"));
                button1.setTextColor(Color.parseColor("#000000"));
                button2.setTextColor(Color.parseColor("#ffffff"));
                if (turn == 1 || turn == 0) {
                    p2Timer = new CountDownTimer(p2Time, 1) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            if (turn != 2) {    //No longer player 2's turn, stop timer
                                cancel();
                            } else {    //Otherwise, deduct time
                                if (increType1 == HOURGLASS) {
                                    p1Time -= ((millisUntilFinished - 1) - p2Time);
                                    button1.setText(updateText(p1Time));
                                }
                                p2Time = (millisUntilFinished - 1);
                                button2.setText(updateText(p2Time));
                            }
                        }

                        @Override
                        public void onFinish() {
                            button2.setBackgroundColor(Color.parseColor("#C0392B"));
                            button2.setTextColor(Color.parseColor("#ffffff"));
                            timeGone();
                        }
                    }.start();
                }

                if (turn == 1) {
                    p1Timer.cancel();
                    turn = 2;
                    if (increType1 == INCREMENT) {
                        p1Time += increment1;
                    }
                    Log.d("INCREMENT", String.valueOf(increType1));
                    button1.setText(updateText(p1Time));
                }

                turn = 2;
            }
        });

        btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button1.setEnabled(true);
                button2.setEnabled(true);
                //Sets the button orange (indicates which player's turn it still is)
                if (turn == 1) {
                    button1.setBackgroundColor(Color.parseColor("#F9AE34"));
                    button1.setTextColor(Color.parseColor("#ffffff"));
                } else if (turn == 2) {
                    button2.setBackgroundColor(Color.parseColor("#F9AE34"));
                    button2.setTextColor(Color.parseColor("#ffffff"));
                }
                turn = 0;
                btn_pause.setVisibility(View.INVISIBLE);
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Returns to the selection screen
                Intent backIntent = new Intent(getApplicationContext(), SelectionScreen.class);
                startActivity(backIntent);
                finish();
            }
        });

        btn_switch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Show alert message - Confirm/Cancel
                //Confirm - switches each player time and increment
                AlertDialog.Builder builder = new AlertDialog.Builder(ClockActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Switch Sides?");
                builder.setMessage("Press 'Confirm' to switch time controls. This will reset the timer.");
                builder.setPositiveButton("Confirm",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Swapping initial times
                                long temp = original1;
                                original1 = original2;
                                original2 = temp;

                                //Swapping increments
                                long temp1 = increment1;
                                increment1 = increment2;
                                increment2 = temp1;

                                int temp2 = increType1;
                                increType1 = increType2;
                                increType2 = temp2;

                                reset();
                            }
                        });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    //Updates the timer - takes in total milliseconds and converts into H:M:S format
    public String updateText(long milliseconds) {
        if (milliseconds >= 3600000) {   //Greater than 1 hour and less than 24
            long hours = milliseconds / 3600000;
            long minutes = (milliseconds / 60000) - (hours * 60);
            long seconds = (milliseconds / 1000) - (hours * 3600) - (minutes * 60);

            if (minutes >= 10 && seconds >= 10)
                return ("" + hours + ":" + minutes + ":" + seconds);
            else if (minutes < 10 && seconds >= 10)
                return ("" + hours + ":0" + minutes + ":" + seconds);
            else if (minutes >= 10)
                return ("" + hours + ":" + minutes + ":0" + seconds);
            else
                return ("" + hours + ":0" + minutes + ":0" + seconds);

        } else if (milliseconds >= 60000) {     //Greater than one minute and less than 1 hour
            long minutes = milliseconds / 60000;
            long seconds = (milliseconds/1000) - (minutes * 60);

            if (seconds >= 10)
                return ("" + minutes + ":" + seconds);
            else {
                return ("" + minutes + ":0" + seconds);
            }
        } else {  //Less than one minute
            long seconds = milliseconds / 1000;
            long decisecond = (milliseconds / 100) - (seconds * 10);
            return ("" + seconds + "." + decisecond);
        }
    }

    //Resets the timers
    public void reset() {
        p1Time = original1;
        p2Time = original2;
        turn = 0;
        button2.setEnabled(true);
        button1.setEnabled(true);
        btn_pause.setEnabled(false);
        btn_pause.setVisibility(View.INVISIBLE);
        button1.setText(updateText(p1Time));
        button2.setText(updateText(p2Time));
        button1.setBackgroundColor(Color.parseColor("#999999"));
        button2.setBackgroundColor(Color.parseColor("#999999"));
        button1.setTextColor(Color.parseColor("#000000"));
        button2.setTextColor(Color.parseColor("#000000"));
    }

    //When one player runs out of time
    public void timeGone() {
        button2.setEnabled(false);
        button1.setEnabled(false);
        btn_pause.setEnabled(false);
        btn_pause.setVisibility(View.INVISIBLE);
    }
}