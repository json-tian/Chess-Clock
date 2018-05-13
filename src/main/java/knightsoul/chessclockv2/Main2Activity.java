package knightsoul.chessclockv2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class Main2Activity extends AppCompatActivity {

    public int moves = 0;
    public boolean player1Timer = false; //True - still going , false - stopped
    public boolean player2Timer = false;
    public boolean paused = false;
    public long temp1 = 0;
    public long temp2 = 0;
    public long increment1 = 0;
    public long increment2 = 0;
    public long original1;
    public long original2;

    private AdView mAdView;


    Button button1;
    Button button2;

    public String setText(long milliseconds) {

        if (milliseconds > 86400000) {


        } else if (milliseconds >= 3600000) {
            long hours = milliseconds / 3600000;
            long minutes = (milliseconds / 60000) - (hours * 60);
            long seconds = (milliseconds/1000) - (hours * 3600) - (minutes * 60);

            if (minutes >= 10 && seconds >= 10)
                return ("" + hours + ":" + minutes + ":" + seconds);
            else if (minutes < 10 && seconds >= 10)
                return ("" + hours + ":0" + minutes + ":" + seconds);
            else if (minutes >= 10 && seconds < 10)
                return ("" + hours + ":" + minutes + ":0" + seconds);
            else if (minutes < 10 && seconds < 10)
                return ("" + hours + ":0" + minutes + ":0" + seconds);

        } else if (milliseconds >= 60000) {
            long minutes = milliseconds / 60000;
            long seconds = (milliseconds/1000) - (minutes * 60);

            if (seconds >= 10)
                return ("" + minutes + ":" + seconds);
            else {
                return ("" + minutes + ":0" + seconds);
            }

        } else if (milliseconds < 60000) {
            long seconds = milliseconds / 1000;
            long decisecond = (milliseconds / 100) - (seconds * 10);
            return ("" + seconds + "." + decisecond);
        }

        return "Error";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        final MediaPlayer moveSound = MediaPlayer.create(this, R.raw.move);
        final MediaPlayer timeSound = MediaPlayer.create(this, R.raw.alarm);

        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        MobileAds.initialize(this, "ca-app-pub-3781115230256321~6975876312");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        final Button button1 = (Button) findViewById(R.id.btn_player1);

        final Button button2 = (Button) findViewById(R.id.btn_player2);

        final Button btn_pause = (Button) findViewById(R.id.btn_pause);

        final Button btn_back = (Button) findViewById(R.id.btn_back);

        final Button btn_reset = (Button) findViewById(R.id.btn_reset);

        final Button btn_switch = (Button) findViewById(R.id.btn_switch);

        String string1 = getIntent().getExtras().getString("player1initial");
        int num1 = Integer.parseInt(string1);
        temp1 = num1 * 1000;
        original1 = temp1;

        String string2 = getIntent().getExtras().getString("player2initial");
        int num2 = Integer.parseInt(string2);
        temp2 = num2 * 1000;
        original2 = temp2;

        String string3 = getIntent().getExtras().getString("player1increment");
        int num3 = Integer.parseInt(string3);
        increment1 = num3 * 1000;

        String string4 = getIntent().getExtras().getString("player2increment");
        int num4 = Integer.parseInt(string4);
        increment2 = num4 * 1000;


        button1.setText(setText(temp1));
        button2.setText(setText(temp2));

        btn_reset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Main2Activity.this);
                builder.setCancelable(true);
                builder.setTitle("Reset Timer?");
                builder.setMessage("Press 'Confirm' to reset timer.");
                builder.setPositiveButton("Confirm",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                temp1 = original1;
                                temp2 = original2;
                                moves = 0;
                                player1Timer = false;
                                player2Timer = false;
                                button2.setEnabled(true);
                                button1.setEnabled(true);
                                btn_pause.setEnabled(true);
                                button1.setText(setText(temp1));
                                button2.setText(setText(temp2));
                                button1.setBackgroundColor(Color.parseColor("#999999"));
                                button2.setBackgroundColor(Color.parseColor("#999999"));
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


        //public void countdown1 (View v) { //Starts countdown for player1  (When player 2 clicks)
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_pause.setVisibility(View.VISIBLE);

                button1.setBackgroundColor(Color.parseColor("#1AC02B"));
                button2.setBackgroundColor(Color.parseColor("#999999"));

                if (player1Timer == false && moves == 0) {

                    moveSound.start();

                    player2Timer = false;
                    player1Timer = true;
                    paused = false;
                    moves++;

                    CountDownTimer count1;

                    count1 = new CountDownTimer(temp1, 1) {

                        @Override
                        public void onTick(long millisUntilFinished) {


                            if (player1Timer == false) {
                                cancel();
                            } else {
                                temp1 = (millisUntilFinished - 1);
                                button1.setText(setText(temp1));

                            }
                        }

                        @Override
                        public void onFinish() {

                            button1.setBackgroundColor(Color.parseColor("#C0392B"));
                            button2.setEnabled(false);
                            button1.setEnabled(false);
                            btn_pause.setEnabled(false);
                            timeSound.start();


                        }
                    }.start();

                } else if (player1Timer == false && moves != 0) {

                    moveSound.start();

                    player1Timer = true;
                    player2Timer = false;

                    if (paused == true) {
                        paused = false;
                    } else {
                        temp2 += increment2;
                    }
                    button2.setText(setText(temp2));
                    button1.setText(setText(temp1));

                    long millisInFuture = temp1;

                    new CountDownTimer(millisInFuture, 1) {

                        @Override
                        public void onTick(long millisUntilFinished) {

                            if (player1Timer == false) {
                                cancel();

                            } else {
                                temp1 = (millisUntilFinished - 1);
                                button1.setText(setText(temp1));
                            }
                        }

                        @Override
                        public void onFinish() {

                            button1.setBackgroundColor(Color.parseColor("#C0392B"));
                            button2.setEnabled(false);
                            button1.setEnabled(false);
                            btn_pause.setEnabled(false);
                            timeSound.start();


                        }
                    }.start();
                }
            }
        });


        //public void countdown2 (View v) { //Starts countdown for player2  (When player 1 clicks)
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                btn_pause.setVisibility(View.VISIBLE);

                button1.setBackgroundColor(Color.parseColor("#999999"));
                button2.setBackgroundColor(Color.parseColor("#1AC02B"));

                if (player2Timer == false && moves == 0) {

                    moveSound.start();

                    paused = false;
                    player1Timer = false;
                    player2Timer = true;
                    moves++;

                    CountDownTimer count2;

                    count2 = new CountDownTimer(temp2, 1) {
                        @Override
                        public void onTick(long millisUntilFinished) {

                            if (player2Timer == false) {
                                cancel();
                            } else {
                                temp2 = (millisUntilFinished - 1);

                                button2.setText(setText(temp2));

                            }
                        }

                        @Override
                        public void onFinish() {

                            button2.setBackgroundColor(Color.parseColor("#C0392B"));
                            button2.setEnabled(false);
                            button1.setEnabled(false);
                            btn_pause.setEnabled(false);
                            timeSound.start();

                        }
                    }.start();

                } else if (player2Timer == false && moves != 0) {

                    moveSound.start();

                    player2Timer = true;
                    player1Timer = false;

                    if (paused == true) {
                        paused = false;
                    } else {
                        temp1 += increment1;
                    }
                    button1.setText(setText(temp1));

                    long millisInFuture = temp2;

                    new CountDownTimer(millisInFuture, 1) {

                        @Override
                        public void onTick(long millisUntilFinished) {

                            if (player2Timer == false) {
                                cancel();
                            } else {
                                temp2 = (millisUntilFinished - 1);
                                button2.setText(setText(temp2));

                            }
                        }

                        @Override
                        public void onFinish() {

                            button2.setBackgroundColor(Color.parseColor("#C0392B"));
                            button2.setEnabled(false);
                            button1.setEnabled(false);
                            btn_pause.setEnabled(false);
                            timeSound.start();

                        }
                    }.start();
                }

            }
        });

        btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (player1Timer == true) {
                    button1.setBackgroundColor(Color.parseColor("#F9AE34"));

                } else if (player2Timer == true) {
                    button2.setBackgroundColor(Color.parseColor("#F9AE34"));
                }

                player2Timer = false;
                player1Timer = false;
                paused = true;

                btn_pause.setVisibility(View.GONE);
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backIntent = new Intent(getApplicationContext(), Main3Activity.class);
                startActivity(backIntent);
            }

        });

        btn_switch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Main2Activity.this);
                builder.setCancelable(true);
                builder.setTitle("Switch Sides?");
                builder.setMessage("Press 'Confirm' to switch sides.");
                builder.setPositiveButton("Confirm",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                temp1 = original2;
                                original2 = original1;
                                original1 = temp1;
                                temp2 = original2;
                                long temp = increment1;
                                increment1 = increment2;
                                increment2 = temp;

                                moves = 0;
                                player1Timer = false;
                                player2Timer = false;
                                button2.setEnabled(true);
                                button1.setEnabled(true);
                                btn_pause.setEnabled(true);
                                button1.setText(setText(temp1));
                                button2.setText(setText(temp2));
                                button1.setBackgroundColor(Color.parseColor("#999999"));
                                button2.setBackgroundColor(Color.parseColor("#999999"));
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

}


