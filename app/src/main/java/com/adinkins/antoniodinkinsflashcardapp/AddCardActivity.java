package com.adinkins.antoniodinkinsflashcardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class AddCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        String question = getIntent().getStringExtra("Question");
        String answer = getIntent().getStringExtra("Answer");

        /* When the Cancel Button is clicked, it should return to the Main Screen */
        findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // finish() method closes the current activity screen
                finish();
            }
        });

        //Add hint to txtNewQuestion
        TextView defaultQuestionText = findViewById(R.id.txtNewQuestion);

        defaultQuestionText.setHint(getResources().getString(R.string.new_question_hint));

        //Add hint to txtNewAnswer
        TextView defaultAnswerText = findViewById(R.id.txtNewAnswer);
        defaultAnswerText.setHint(getResources().getString(R.string.new_answer_hint));

        if(question != null && answer != null){
            defaultQuestionText.setText(question);
            defaultAnswerText.setText(answer);
        }

        /* When the save button is clicked, it should do the following:
            - Take the inputs from txtNewQuestion and txtNewAnswer and pass them to the MainActivity
            - Close the current Activity
         */
        findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText question = findViewById(R.id.txtNewQuestion); // Get question field
                String questionString = question.getText().toString(); // Sets input text to String variable
                Log.i("Question", questionString);

                EditText answer = findViewById(R.id.txtNewAnswer);  // Get answer field
                String answerString = answer.getText().toString();  // Sets input text to String variable
                Log.i("Answer", answerString);

                // Display error if user did not make an entry
                if(questionString.equals("") || answerString.equals("") ){
                    Toast noInputError = Toast.makeText(getApplicationContext(), "Invalid Entry: Empty String", Toast.LENGTH_SHORT);
                    noInputError.show();
                }
                else {
                    // Takes data and sends it to the MainActivity
                    // fcData = flashcard data
                    Intent fcData = new Intent();                           // Creates a new Intent
                    fcData.putExtra("Question", questionString);     // Key-value pair for the question string
                    fcData.putExtra("Answer", answerString);         // Key-value pair for the answer string
                    setResult(RESULT_OK, fcData);                           // sends Result code plus data to original activity
                    finish();                                               // Closes current activity
                }


            }
        });
    }
}
