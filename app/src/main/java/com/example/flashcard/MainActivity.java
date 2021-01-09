package com.example.flashcard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.AddActivity;
import com.google.android.material.snackbar.Snackbar;


public class MainActivity extends AppCompatActivity {
    View view;
    boolean isShowingAnswer = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = this.getWindow().getDecorView();
//        view.setBackgroundResource(R.color.purple_700);
        view.setBackgroundResource(R.color.white);

    findViewById(R.id.flashcard_question).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcard_answer).setVisibility(View.VISIBLE);
                findViewById(R.id.flashcard_answer).setBackgroundResource(R.color.green_rest);
            }
        });
    findViewById(R.id.flashcard_answer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcard_answer).setVisibility(View.VISIBLE);
                findViewById(R.id.flashcard_answer).setBackgroundResource(R.color.green_rest);
                 }
        });
    findViewById(R.id.flashcard_answer1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcard_answer1).setVisibility(View.VISIBLE);
                findViewById(R.id.flashcard_answer1).setBackgroundResource(R.color.amber_dark);
               }
        });
    findViewById(R.id.flashcard_answer2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcard_answer2).setVisibility(View.VISIBLE);
                findViewById(R.id.flashcard_answer2).setBackgroundResource(R.color.amber_dark);
            }
        });

    final ImageView button_eye = (ImageView) findViewById(R.id.eye_off);
    button_eye.setOnClickListener(new View.OnClickListener() {
    boolean isShowingAnswer = true;
            public void onClick(View v) {
                if (isShowingAnswer == true) {
                    findViewById(R.id.flashcard_answer).setVisibility(View.INVISIBLE);
                    findViewById(R.id.flashcard_answer1).setVisibility(View.INVISIBLE);
                    findViewById(R.id.flashcard_answer2).setVisibility(View.INVISIBLE);
                    button_eye.setImageResource(R.drawable.ic_iconmonstr_eye_thin);
                    isShowingAnswer = false;
                } else if (isShowingAnswer == false) {
                    findViewById(R.id.flashcard_answer).setVisibility(View.VISIBLE);
                    findViewById(R.id.flashcard_answer1).setVisibility(View.VISIBLE);
                    findViewById(R.id.flashcard_answer2).setVisibility(View.VISIBLE);
                    button_eye.setImageResource(R.drawable.ic_iconmonstr_eye_off_thin);
                    isShowingAnswer = true;
                }
            }
        });
    findViewById(R.id.add_btn).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            MainActivity.this.startActivityForResult(intent, 100);
    }
});
    findViewById(R.id.edit_icon).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            intent.putExtra("stringKey1", ((TextView) findViewById(R.id.flashcard_question)).getText().toString()
            );
            intent.putExtra("stringKey2", ((TextView) findViewById(R.id.flashcard_answer)).getText().toString()
            );
            intent.putExtra("stringKey3", ((TextView) findViewById(R.id.flashcard_answer1)).getText().toString()
            );
            intent.putExtra("stringKey4", ((TextView) findViewById(R.id.flashcard_answer2)).getText().toString()
            );
            MainActivity.this.startActivityForResult(intent, 100);
        }

    });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) { // this 100 needs to match the 100 we used when we called startActivityForResult!
            String string1 = data.getExtras().getString("string1"); // 'string1' needs to match the key we used when we put the string in the Intent
            String string2 = data.getExtras().getString("string2");
            String string3 = data.getExtras().getString("string3");
            String string4 = data.getExtras().getString("string4");



            ((TextView) findViewById(R.id.flashcard_question)).setText(string1);
            ((TextView) findViewById(R.id.flashcard_answer)).setText(string2);
            ((TextView) findViewById(R.id.flashcard_answer1)).setText(string3);
            ((TextView) findViewById(R.id.flashcard_answer2)).setText(string4);

        }
        Snackbar.make(findViewById(R.id.flashcard_question),
                "The message to display",
                Snackbar.LENGTH_SHORT)
                .show();
    }


}
