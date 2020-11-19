package com.example.weihnachtskalender;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RiddleText extends AppCompatActivity {

    TextView riddleTextViewQuestion;
    Button riddleButtonSolution;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riddle_text);

        riddleTextViewQuestion = (TextView) findViewById (R.id.riddleTextViewQuestion);
        riddleTextViewQuestion.setText(StateMachine.getRiddleString("question"));

        riddleButtonSolution = (Button) findViewById( R.id.riddleButtonSolution);
        riddleButtonSolution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StateMachine.openCheckSolution();
            }
        });
    }

}