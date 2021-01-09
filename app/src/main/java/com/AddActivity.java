package com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.flashcard.MainActivity;
import com.example.flashcard.R;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        findViewById(R.id.back_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                AddActivity.this.startActivity(intent);
            }
        });
        findViewById(R.id.save_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent(); // create a new Intent, this is where we will put our data
                data.putExtra("string1",((EditText) findViewById(R.id.question_field)).getText().toString()
); // puts one string into the Intent, with the key as 'string1'
                data.putExtra("string2", ((EditText) findViewById(R.id.answer_field)).getText().toString()
); // puts another string into the Intent, with the key as 'string2
                data.putExtra("string3", ((EditText) findViewById(R.id.answer_field2)).getText().toString()
                );
                data.putExtra("string4", ((EditText) findViewById(R.id.answer_field3)).getText().toString());

                        setResult(RESULT_OK, data); // set result code and bundle data for response
                finish(); // closes this activity and pass data to the original activity that launched this activity
            }
        });


      String s1 = getIntent().getStringExtra("stringKey1"); // this string will be 'harry potter`
      String s2 = getIntent().getStringExtra("stringKey2");
        String s3 = getIntent().getStringExtra("stringKey3");
        String s4 = getIntent().getStringExtra("stringKey4");


        ((EditText) findViewById(R.id.question_field)).setText(s1);
        ((EditText) findViewById(R.id.answer_field)).setText(s2);
        ((EditText) findViewById(R.id.answer_field2)).setText(s3);
        ((EditText) findViewById(R.id.answer_field3)).setText(s4);


    }
}
