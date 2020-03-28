package com.adinkins.antoniodinkinsflashcardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    boolean isShowingAnswers = true;
    FlashcardDatabase flashcardDatabase;
    List<Flashcard> allFlashcards;
    int currentCardDisplayIndex = 0;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && (data != null)) {
            String question = data.getExtras().getString("Question"); // Gets question data from AddCardActivity and sets it to a string variable
            String answer = data.getExtras().getString("Answer");    // Gets answer data from the AddCardActivity and sets it to a string variable


            //Take data and display it in text fields
            TextView questionField = findViewById(R.id.txtQuestion);
            TextView answerField = findViewById(R.id.txtAnswer);
            //Add Snackbar to signify a successful creation of a flashcard
            Snackbar.make(questionField, "Successfully added a new flashcard", Snackbar.LENGTH_SHORT).show();

            questionField.setText(question);
            answerField.setText(answer);
            flashcardDatabase.insertCard(new Flashcard(question, answer));
            allFlashcards = flashcardDatabase.getAllCards();
        }


    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flashcardDatabase = new FlashcardDatabase(getApplicationContext());
        allFlashcards = flashcardDatabase.getAllCards();
        final TextView questionField = findViewById(R.id.txtQuestion);
        final TextView answerField = findViewById(R.id.txtAnswer);
        final TextView choice1 = findViewById(R.id.txtChoice1);
        final TextView choice2 = findViewById(R.id.txtChoice2);
        final TextView choice3 = findViewById(R.id.txtChoice3);
        ImageView nextBtn = findViewById(R.id.btnNext);
        ImageView editBtn = findViewById(R.id.btnEdit);
        final ImageView deleteBtn = findViewById(R.id.btnDelete);


        if(allFlashcards != null && allFlashcards.size() > 0){
            //Take data and display it in text fields
            questionField.setText(allFlashcards.get(0).getQuestion());
            answerField.setText(allFlashcards.get(0).getAnswer());
        }

        findViewById(R.id.flashcard_app).setBackgroundColor(getResources().getColor(R.color.app_bgcolor, null));
        //findViewById(R.id.txtQuestion).setBackgroundColor(getResources().getColor(R.color.question_bgcolor, null));




        questionField.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

                View answerSideView = findViewById(R.id.txtAnswer);
                int centerX = answerSideView.getWidth() / 2;
                int centerY = answerSideView.getHeight() / 2;

                float finalRadius = (float)Math.hypot(centerX, centerY);
                Animator newAnimation = ViewAnimationUtils.createCircularReveal(answerSideView, centerX, centerY, 0f, finalRadius);

            questionField.setVisibility(View.INVISIBLE);
            answerField.setVisibility(View.VISIBLE);

            newAnimation.setDuration(3000);
            newAnimation.start();
        }
    });

    answerField.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            answerField.setVisibility(View.INVISIBLE);
            questionField.setVisibility(View.VISIBLE);
        }
    });

    choice1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String firstChoiceText = choice1.getText().toString();
            if(firstChoiceText.equals(getResources().getString(R.string.answer))){
                choice1.setBackgroundColor(getResources().getColor(R.color.answer_bgcolor, null));
            }
            else {
                choice1.setBackgroundColor(getResources().getColor(R.color.wrong_answer, null));
            }
        }
    });


    choice2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String secondChoiceText = choice2.getText().toString();
            if(secondChoiceText.equals(getResources().getString(R.string.answer))){
                choice2.setBackgroundColor(getResources().getColor(R.color.answer_bgcolor, null));
            }
            else {
                choice2.setBackgroundColor(getResources().getColor(R.color.wrong_answer, null));
            }
        }
    });

    choice3.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String thirdChoiceText = choice3.getText().toString();
            if(thirdChoiceText.equals(getResources().getString(R.string.answer))){
                choice3.setBackgroundColor(getResources().getColor(R.color.answer_bgcolor, null));
            }
            else {
                choice3.setBackgroundColor(getResources().getColor(R.color.wrong_answer, null));
            }
        }
    });


        findViewById(R.id.toggleChoicesVisibility).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShowingAnswers) {
                    ((ImageView) findViewById(R.id.toggleChoicesVisibility)).setImageResource(R.drawable.show_answers);
                    choice1.setVisibility(View.INVISIBLE);
                    choice2.setVisibility(View.INVISIBLE);
                    choice3.setVisibility(View.INVISIBLE);
                    isShowingAnswers = false;

                }
                else {
                    ((ImageView)findViewById(R.id.toggleChoicesVisibility)).setImageResource(R.drawable.hide_answers);
                    choice1.setVisibility(View.VISIBLE);
                    choice2.setVisibility(View.VISIBLE);
                    choice3.setVisibility(View.VISIBLE);
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
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });


        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentCardDisplayIndex++;   //Increment the currentCardIndex by 1

                //Load Animations
                final Animation leftOut = AnimationUtils.loadAnimation(v.getContext(), R.anim.left_out);
                final Animation rightIn = AnimationUtils.loadAnimation(v.getContext(), R.anim.right_in);

                //Play Animations in sequence
                leftOut.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        findViewById(R.id.txtQuestion).startAnimation(rightIn);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                findViewById(R.id.txtQuestion).startAnimation(leftOut);
                //Resets the currentCardDisplayIndex if it is greater than the index last element in the allFlashCards List
                if(currentCardDisplayIndex > allFlashcards.size() - 1) {
                    currentCardDisplayIndex = 0;
                }

                questionField.setText(allFlashcards.get(currentCardDisplayIndex).getQuestion());
                answerField.setText(allFlashcards.get(currentCardDisplayIndex).getAnswer());
            }
        });

        //Add onClickListener to editBtn
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question = questionField.getText().toString();
                String answer = answerField.getText().toString();
                Intent editCardIntent = new Intent(MainActivity.this, AddCardActivity.class);
                editCardIntent.putExtra("Question", question);
                editCardIntent.putExtra("Answer", answer);
                MainActivity.this.startActivityForResult(editCardIntent, 100);
            }
        });

        //Add onClickListener to delete button
        //Delete deletes the current instance of the flashcard
        //It also shows the previous card in the deck (if available)
        //Shows an empty state if there are no stored cards
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String deleteString = questionField.getText().toString();
                if(currentCardDisplayIndex != 0){
                    flashcardDatabase.deleteCard(deleteString);
                    currentCardDisplayIndex--;
                    allFlashcards = flashcardDatabase.getAllCards();

                    if(allFlashcards.isEmpty()){
                        String emptyText = "No cards available. Add New Card";
                        questionField.setText(emptyText);
                    }
                    questionField.setText(allFlashcards.get(currentCardDisplayIndex).getQuestion());
                    answerField.setText(allFlashcards.get(currentCardDisplayIndex).getAnswer());
                }
                else {
                    flashcardDatabase.deleteCard(deleteString);
                    allFlashcards = flashcardDatabase.getAllCards();
                    if(allFlashcards.isEmpty()){
                        String emptyText = "No cards available. Add New Card";
                        questionField.setText(emptyText);
                    }
                    questionField.setText(allFlashcards.get(currentCardDisplayIndex).getQuestion());
                    answerField.setText(allFlashcards.get(currentCardDisplayIndex).getAnswer());
                }





            }
        });
    }




}