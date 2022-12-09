package com.example.englishquestion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView title, titleVN, loading;
    String loadString = "Loading";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWidgetsControl();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                titleVN.setBackgroundResource(R.drawable.logo2);
                Toast.makeText(MainActivity.this, "Hi, mate! Prove your English to become a God!", Toast.LENGTH_SHORT).show();
            }
        }, 2000); //hiện trong giây thứ 2
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loading.setText(loadString);
            }
        }, 3000); //hiện trong giây thứ 3
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadString += " ."; // hiện trong giây thứ 4
                loading.setText(loadString);
            }
        }, 4000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadString += " .";
                loading.setText(loadString);
            }
        }, 5000);   // hiện trong giây thứ 5
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadString += " .";
                loading.setText(loadString);
            }
        }, 6000);   // hiện trong giây thứ 6
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loading.setText("");
                Toast.makeText(MainActivity.this, "Let's go!", Toast.LENGTH_SHORT).show();
            }
        }, 7000);   // hiện trong giây thứ 7
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, CataList.class);
                startActivity(intent);
            }
        }, 9000); // chuyển tiếp sang màn hình của activity_main.xml trong giây thứ

        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CataList.class);
                startActivity(intent);
            }
        });

        titleVN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CataList.class);
                startActivity(intent);
            }
        });
    }

    private void getWidgetsControl() {
        title = findViewById(R.id.txtTitle);
        titleVN = findViewById(R.id.txtTitleVN);
        loading = findViewById(R.id.txtLoading);
    }
}