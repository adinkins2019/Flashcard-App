package com.adinkins.antoniodinkinsflashcardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean isShowingAnswers = true;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == 100 && (data != null)){
            String question = data.getExtras().getString("Question"); // Gets question data from AddCardActivity and sets it to a string variable
            String answer = data.getExtras().getString("Answer");    // Gets answer data from the AddCardActivity and sets it to a string variable

            //Take data and display it in text fields
            TextView questionField = findViewById(R.id.txtQuestion);
            TextView answerField = findViewById(R.id.txtAnswer);

            questionField.setText(question);
            answerField.setText(answer);
        }

    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.flashcard_app).setBackgroundColor(getResources().getColor(R.color.app_bgcolor, null));
        //findViewById(R.id.txtQuestion).setBackgroundColor(getResources().getColor(R.color.question_bgcolor, null));




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

    findViewById(R.id.txtChoice1).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            TextView firstChoice = findViewById(R.id.txtChoice1);
            String firstChoiceText = firstChoice.getText().toString();
            if(firstChoiceText.equals(getResources().getString(R.string.answer))){
                findViewById(R.id.txtChoice1).setBackgroundColor(getResources().getColor(R.color.answer_bgcolor, null));
            }
            else {
                findViewById(R.id.txtChoice1).setBackgroundColor(getResources().getColor(R.color.wrong_answer, null));
            }
        }
    });


    findViewById(R.id.txtChoice2).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            TextView secondChoice = findViewById(R.id.txtChoice2);
            String secondChoiceText = secondChoice.getText().toString();
            if(secondChoiceText.equals(getResources().getString(R.string.answer))){
                findViewById(R.id.txtChoice2).setBackgroundColor(getResources().getColor(R.color.answer_bgcolor, null));
            }
            else {
                findViewById(R.id.txtChoice2).setBackgroundColor(getResources().getColor(R.color.wrong_answer, null));
            }
        }
    });

    findViewById(R.id.txtChoice3).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            TextView thirdChoice = findViewById(R.id.txtChoice3);
            String thirdChoiceText = thirdChoice.getText().toString();
            if(thirdChoiceText.equals(getResources().getString(R.string.answer))){
                findViewById(R.id.txtChoice3).setBackgroundColor(getResources().getColor(R.color.answer_bgcolor, null));
            }
            else {
                findViewById(R.id.txtChoice3).setBackgroundColor(getResources().getColor(R.color.wrong_answer, null));
            }
        }
    });


        findViewById(R.id.toggleChoicesVisibility).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShowingAnswers) {
                    ((ImageView) findViewById(R.id.toggleChoicesVisibility)).setImageResource(R.drawable.show_answers);
                    findViewById(R.id.txtChoice1).setVisibility(View.INVISIBLE);
                    findViewById(R.id.txtChoice2).setVisibility(View.INVISIBLE);
                    findViewById(R.id.txtChoice3).setVisibility(View.INVISIBLE);
                    isShowingAnswers = false;

                }
                else {
                    ((ImageView)findViewById(R.id.toggleChoicesVisibility)).setImageResource(R.drawable.hide_answers);
                    findViewById(R.id.txtChoice1).setVisibility(View.VISIBLE);
                    findViewById(R.id.txtChoice2).setVisibility(View.VISIBLE);
                    findViewById(R.id.txtChoice3).setVisibility(View.VISIBLE);
                    isShowingAnswers = true;
                }
            }
        });


        /* Add onClickListener to Add Button
        onClicklistener should forward user to the AddCardActivity activity screen
         */
        findViewById(R.id.btnAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    /* The following code creates a connection between MainActivity and AddCardActivity
                        The way we connect Activities is by defining an Intent and passing that intent
                        from one Activity to another

                     */
                    Intent addCardIntent = new Intent(MainActivity.this, AddCardActivity.class);
                    MainActivity.this.startActivityForResult(addCardIntent, 100);
            }
        });



    }



}