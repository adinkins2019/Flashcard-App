package com.adinkins.antoniodinkinsflashcardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.flashcard_app).setBackgroundColor(getResources().getColor(R.color.app_bgcolor, null));
        findViewById(R.id.txtQuestion).setBackgroundColor(getResources().getColor(R.color.question_bgcolor, null));




    findViewById(R.id.txtQuestion).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
                findViewById(R.id.txtQuestion).setVisibility(View.INVISIBLE);
                findViewById(R.id.txtAnswer).setVisibility(View.VISIBLE);

        }
    });

    findViewById(R.id.txtAnswer).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            findViewById(R.id.txtAnswer).setVisibility(View.INVISIBLE);
            findViewById(R.id.txtQuestion).setVisibility(View.VISIBLE);
        }
    });
    }


}