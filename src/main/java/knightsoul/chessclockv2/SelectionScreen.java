package knightsoul.chessclockv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectionScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectionscreen);

        //Presets
        Button bullet1 = findViewById(R.id.btn_bullet1);
        Button bullet2 = findViewById(R.id.btn_bullet2);
        Button blitz1 = findViewById(R.id.btn_blitz1);
        Button blitz2 = findViewById(R.id.btn_blitz2);
        Button rapid1 = findViewById(R.id.btn_rapid1);
        Button rapid2 = findViewById(R.id.btn_rapid2);
        Button classical1 = findViewById(R.id.btn_classical1);
        Button classical2 = findViewById(R.id.btn_classical2);
        Button custom = findViewById(R.id.btn_custom);

        custom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), TimePicker.class);
                //Show how to pass information to another activity
                startActivity(startIntent);
            }
        });

        bullet1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), ClockActivity.class);
                //Show how to pass information to another activity

                startIntent.putExtra("player1initial", "60");
                startIntent.putExtra("player2initial", "60");
                startIntent.putExtra("player1increment", "1");
                startIntent.putExtra("player2increment", "1");
                startActivity(startIntent);
            }
        });

        bullet2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), ClockActivity.class);
                //Show how to pass information to another activity

                startIntent.putExtra("player1initial", "120");
                startIntent.putExtra("player2initial", "120");
                startIntent.putExtra("player1increment", "1");
                startIntent.putExtra("player2increment", "1");
                startActivity(startIntent);
            }
        });

        blitz1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), ClockActivity.class);
                //Show how to pass information to another activity

                startIntent.putExtra("player1initial", "180");
                startIntent.putExtra("player2initial", "180");
                startIntent.putExtra("player1increment", "2");
                startIntent.putExtra("player2increment", "2");
                startActivity(startIntent);
            }
        });

        blitz2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), ClockActivity.class);
                //Show how to pass information to another activity

                startIntent.putExtra("player1initial", "300");
                startIntent.putExtra("player2initial", "300");
                startIntent.putExtra("player1increment", "5");
                startIntent.putExtra("player2increment", "5");
                startActivity(startIntent);
            }
        });

        rapid1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), ClockActivity.class);
                //Show how to pass information to another activity

                startIntent.putExtra("player1initial", "600");
                startIntent.putExtra("player2initial", "600");
                startIntent.putExtra("player1increment", "10");
                startIntent.putExtra("player2increment", "10");
                startActivity(startIntent);
            }
        });

        rapid2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), ClockActivity.class);
                //Show how to pass information to another activity

                startIntent.putExtra("player1initial", "900");
                startIntent.putExtra("player2initial", "900");
                startIntent.putExtra("player1increment", "10");
                startIntent.putExtra("player2increment", "10");
                startActivity(startIntent);
            }
        });

        classical1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), ClockActivity.class);
                //Show how to pass information to another activity

                startIntent.putExtra("player1initial", "1800");
                startIntent.putExtra("player2initial", "1800");
                startIntent.putExtra("player1increment", "30");
                startIntent.putExtra("player2increment", "30");
                startActivity(startIntent);
            }
        });

        classical2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), ClockActivity.class);
                //Show how to pass information to another activity

                startIntent.putExtra("player1initial", "2700");
                startIntent.putExtra("player2initial", "2700");
                startIntent.putExtra("player1increment", "45");
                startIntent.putExtra("player2increment", "45");
                startActivity(startIntent);
            }
        });
    }
}