package com.example.weihnachtskalender;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Solution extends AppCompatActivity {

    TextView solutionTextViewHeadline;
    TextView solutionTextViewCode;
    Button solutionButtonNextRiddle;

    Context context;
    Activity activity;
    int currentRiddle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solution);

        context = this;
        activity = this;

        solutionTextViewHeadline = (TextView) findViewById (R.id.solutionTextViewHeadline);
        solutionTextViewCode = (TextView) findViewById (R.id.solutionTextViewCode);

        solutionButtonNextRiddle = (Button) findViewById( R.id.solutionButtonNextRiddle);
        solutionButtonNextRiddle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StateMachine.openNextRiddle(currentRiddle);
            }
        });

        solutionTextViewCode.setText(StateMachine.getRiddleString("code"));
        currentRiddle = Integer.parseInt( StateMachine.getRiddleString("number") );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemReset:
                StateMachine.reset(context);
                break;
            case R.id.itemChooseDay:
                StateMachine.setDay(activity);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}