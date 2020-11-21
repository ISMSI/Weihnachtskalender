package com.example.weihnachtskalender;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Solution extends AppCompatActivity {

    TextView solutionTextViewHeadline;
    TextView solutionTextViewCode;
    Button solutionButtonNextRiddle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solution);

        solutionTextViewHeadline = (TextView) findViewById (R.id.solutionTextViewHeadline);
        solutionTextViewCode = (TextView) findViewById (R.id.solutionTextViewCode);

        solutionButtonNextRiddle = (Button) findViewById( R.id.solutionButtonNextRiddle);
        solutionButtonNextRiddle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StateMachine.openNextRiddle();
            }
        });

        solutionTextViewCode.setText(StateMachine.getRiddleString("code"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}