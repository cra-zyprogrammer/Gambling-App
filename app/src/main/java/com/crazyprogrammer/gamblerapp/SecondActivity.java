package com.crazyprogrammer.gamblerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class SecondActivity extends AppCompatActivity {

    TextView welcomeTxt ,  luckyNumber;
    Button btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        welcomeTxt = findViewById(R.id.welconeTxt);
        luckyNumber = findViewById(R.id.luckyNumber);
        btn = findViewById(R.id.btn);

        // Recieving the data from main activity

        Intent i = getIntent();
        String UserName = i.getStringExtra("name");

        // Generating Random Numbers
        int  randomNumber =generateRandomNumbers();
        luckyNumber.setText("" + randomNumber);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareData(UserName, randomNumber);
            }
        });
    }

    public int generateRandomNumbers(){
        Random random = new Random();
        int UpperLimit = 1000;
        int randomNumberGenerated = random.nextInt(UpperLimit);
        return randomNumberGenerated;
    }

    public  void shareData(String UserName, int randomNumber){
        Intent i = new Intent(Intent.ACTION_SENDTO);
        i.setType("text/plain");

        // Additional Info
        i.putExtra(Intent.EXTRA_SUBJECT, UserName + "Got Lucky Today!");
        i.putExtra(Intent.EXTRA_TEXT, "His Lucky Number is: " +randomNumber);
        startActivity(Intent.createChooser(i, "Choose a platform"));
    }
}
