package com.example.weihnachtskalender;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class RiddleText extends AppCompatActivity {

    TextView riddleTextViewQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riddle_text);

        riddleTextViewQuestion.setText("Test");
    }

}