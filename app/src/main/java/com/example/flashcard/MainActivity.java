package com.example.flashcard;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.AddActivity;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    View view;
    boolean isShowingAnswer = true;
    int currentCardDisplayedIndex = 0;
    int ADD_CARD_REQUEST_CODE = 100;
    int EDIT_CARD_REQUEST_CODE = 111;
    Flashcard cardToEdit;
    FlashcardDatabase flashcardDatabase;
    List<Flashcard> allFlashcards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = this.getWindow().getDecorView();
//        view.setBackgroundResource(R.color.purple_700);
        view.setBackgroundResource(R.color.white);

        flashcardDatabase = new FlashcardDatabase(getApplicationContext());
        allFlashcards = flashcardDatabase.getAllCards();

        if (allFlashcards != null && allFlashcards.size() > 0) {
            ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(0).getQuestion());
            ((TextView) findViewById(R.id.flashcard_answer)).setText(allFlashcards.get(0).getAnswer());
        }

        findViewById(R.id.flashcard_question).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcard_question).setVisibility(View.INVISIBLE);

                findViewById(R.id.flashcard_answer).setVisibility(View.VISIBLE);

//                findViewById(R.id.flashcard_answer).setBackgroundResource(R.color.green_rest);
                View answerSideView = findViewById(R.id.flashcard_answer);
                View questionSideView = findViewById(R.id.flashcard_question);
// get the center for the clipping circle
                int cx = answerSideView.getWidth() / 2;
                int cy = answerSideView.getHeight() / 2;

// get the final radius for the clipping circle
                float finalRadius = (float) Math.hypot(cx, cy);

// create the animator for this view (the start radius is zero)
                Animator anim = ViewAnimationUtils.createCircularReveal(answerSideView, cx, cy, 0f, finalRadius);

// hide the question and show the answer to prepare for playing the animation!
                questionSideView.setVisibility(View.INVISIBLE);
                answerSideView.setVisibility(View.VISIBLE);

                anim.setDuration(3000);
                anim.start();
            }
        });
        findViewById(R.id.flashcard_answer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcard_answer).setVisibility(View.INVISIBLE);
                findViewById(R.id.flashcard_answer).setBackgroundResource(R.color.green_rest);
                findViewById(R.id.flashcard_question).setVisibility(View.VISIBLE);
                 }
        });
//    findViewById(R.id.flashcard_answer1).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                findViewById(R.id.flashcard_answer1).setVisibility(View.VISIBLE);
//                findViewById(R.id.flashcard_answer1).setBackgroundResource(R.color.amber_dark);
//               }
//        });
//    findViewById(R.id.flashcard_answer2).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                findViewById(R.id.flashcard_answer2).setVisibility(View.VISIBLE);
//                findViewById(R.id.flashcard_answer2).setBackgroundResource(R.color.amber_dark);
//            }
//        });

        final ImageView button_eye = (ImageView) findViewById(R.id.eye_off);
        button_eye.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (isShowingAnswer == true) {
                    findViewById(R.id.flashcard_answer).setVisibility(View.INVISIBLE);
//                    findViewById(R.id.flashcard_answer1).setVisibility(View.INVISIBLE);
//                    findViewById(R.id.flashcard_answer2).setVisibility(View.INVISIBLE);
                    button_eye.setImageResource(R.drawable.ic_iconmonstr_eye_thin);
                    isShowingAnswer = false;
                } else if (isShowingAnswer == false) {
                    findViewById(R.id.flashcard_answer).setVisibility(View.VISIBLE);
//                    findViewById(R.id.flashcard_answer1).setVisibility(View.VISIBLE);
//                    findViewById(R.id.flashcard_answer2).setVisibility(View.VISIBLE);
                    button_eye.setImageResource(R.drawable.ic_iconmonstr_eye_off_thin);
                    isShowingAnswer = true;
                }
            }
        });
        findViewById(R.id.add_btn).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            MainActivity.this.startActivityForResult(intent, ADD_CARD_REQUEST_CODE);
            overridePendingTransition(R.anim.right_in, R.anim.left_in);
    }
});
        findViewById(R.id.edit_icon).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, AddActivity.class);

            intent.putExtra("stringKey1", ((TextView) findViewById(R.id.flashcard_question)).getText().toString()
            );
//            for (int i = 0; i < allFlashcards.size();i++){
//                if (i == currentCardDisplayedIndex){
//                    cardToEdit.setQuestion("");
//                    flashcardDatabase.updateCard(cardToEdit);
//                }
//            }
            intent.putExtra("stringKey2", ((TextView) findViewById(R.id.flashcard_answer)).getText().toString()
            );
//            intent.putExtra("stringKey3", ((TextView) findViewById(R.id.flashcard_answer1)).getText().toString()
//            );
//            intent.putExtra("stringKey4", ((TextView) findViewById(R.id.flashcard_answer2)).getText().toString()
//            );
            MainActivity.this.startActivityForResult(intent, ADD_CARD_REQUEST_CODE);

        }

    });
        findViewById(R.id.empty_state).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                MainActivity.this.startActivityForResult(intent, ADD_CARD_REQUEST_CODE);
            }
        });


        findViewById(R.id.next_icon).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            currentCardDisplayedIndex++;
            if (currentCardDisplayedIndex > allFlashcards.size() - 1) {
                currentCardDisplayedIndex = 0;
            }
                ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(currentCardDisplayedIndex).getQuestion());
                ((TextView) findViewById(R.id.flashcard_answer)).setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());
            if (findViewById(R.id.flashcard_question).getVisibility() == View.INVISIBLE) {
                findViewById(R.id.flashcard_question).setVisibility(View.VISIBLE);
                findViewById(R.id.flashcard_answer).setVisibility(View.INVISIBLE);
            }
            final Animation leftOutAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left_in);
            final Animation rightInAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right_in);
            leftOutAnim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    // this method is called when the animation first starts
                    findViewById(R.id.flashcard_question).startAnimation(leftOutAnim);
                }
                @Override
                public void onAnimationEnd(Animation animation) {
                    // this method is called when the animation is finished playing
                    findViewById(R.id.flashcard_question).startAnimation(rightInAnim);
                }
                @Override
                public void onAnimationRepeat(Animation animation) {
                    // we don't need to worry about this method
                }
            });
            findViewById(R.id.flashcard_question).startAnimation(leftOutAnim);
        }

    });

        findViewById(R.id.trash_icon).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongViewCast")
            @Override
            public void onClick(View v) {
                flashcardDatabase.deleteCard(((TextView) findViewById(R.id.flashcard_question)).getText().toString());
                allFlashcards = flashcardDatabase.getAllCards();
                currentCardDisplayedIndex++;
                if (allFlashcards != null && allFlashcards.size() > 0) {
                    ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(0).getQuestion());
                    ((TextView) findViewById(R.id.flashcard_answer)).setText(allFlashcards.get(0).getAnswer());
                }
                else{
                    findViewById(R.id.flashcard_answer).setVisibility(View.INVISIBLE);
                    ((TextView) findViewById(R.id.flashcard_question)).setText("PLEASE ADD A NEW\n FLASH CARDS!");
                    // ((TextView) findViewById(R.id.empty_view)).setText("No More Flash Cards\n Please Add new Flash Cards!");
                }

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_CARD_REQUEST_CODE) { // this 100 needs to match the 100 we used when we called startActivityForResult!
            String string1 = data.getExtras().getString("string1"); // 'string1' needs to match the key we used when we put the string in the Intent
            String string2 = data.getExtras().getString("string2");
//            String string3 = data.getExtras().getString("string3");
//            String string4 = data.getExtras().getString("string4");

            ((TextView) findViewById(R.id.flashcard_question)).setText(string1);
            ((TextView) findViewById(R.id.flashcard_answer)).setText(string2);
//            ((TextView) findViewById(R.id.flashcard_answer1)).setText(string3);
//            ((TextView) findViewById(R.id.flashcard_answer2)).setText(string4);
            flashcardDatabase.insertCard(new Flashcard(string1, string2));
            allFlashcards = flashcardDatabase.getAllCards();
        }

       Snackbar.make(findViewById(R.id.flashcard_question),
                "The message to display",
                Snackbar.LENGTH_SHORT)
                .show();
    }





}
