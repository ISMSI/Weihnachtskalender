package com.example.weihnachtskalender;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RiddleText extends AppCompatActivity {

    TextView riddleTextViewQuestion;
    Button riddleButtonSolution;
    Context context;
    Activity activity;

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

        context = this;
        activity = this;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.itemReset: StateMachine.reset(context); break;
            case R.id.itemChooseDay: StateMachine.setDay(activity); break;
        }
        return super.onOptionsItemSelected(item);
    }
}