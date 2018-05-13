package knightsoul.chessclockv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        final Button bullet1 = (Button) findViewById(R.id.btn_bullet1);
        final Button bullet2 = (Button) findViewById(R.id.btn_bullet2);
        final Button blitz1 = (Button) findViewById(R.id.btn_blitz1);
        final Button blitz2 = (Button) findViewById(R.id.btn_blitz2);
        final Button rapid1 = (Button) findViewById(R.id.btn_rapid1);
        final Button rapid2 = (Button) findViewById(R.id.btn_rapid2);
        final Button classical1 = (Button) findViewById(R.id.btn_classical1);
        final Button classical2 = (Button) findViewById(R.id.btn_classical2);
        final Button custom = (Button) findViewById(R.id.btn_custom);

        custom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), MainActivity.class);
                //Show how to pass information to another activity
                startActivity(startIntent);
            }
        });

        bullet1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), Main2Activity.class);
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
                Intent startIntent = new Intent(getApplicationContext(), Main2Activity.class);
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
                Intent startIntent = new Intent(getApplicationContext(), Main2Activity.class);
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
                Intent startIntent = new Intent(getApplicationContext(), Main2Activity.class);
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
                Intent startIntent = new Intent(getApplicationContext(), Main2Activity.class);
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
                Intent startIntent = new Intent(getApplicationContext(), Main2Activity.class);
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
                Intent startIntent = new Intent(getApplicationContext(), Main2Activity.class);
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
                Intent startIntent = new Intent(getApplicationContext(), Main2Activity.class);
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
