
 package com.example.weihnachtskalender;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

 public class CheckSolution extends AppCompatActivity {

    TextView checkSolutionPlainTextEnter;
    TextView checkSolutionTextViewWrong;

    Button checkSolutionButtonCheckSolution;
    Button checkSolutionButtonRiddle;

    Context context;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_solution);

        context = this;
        activity = this;

        checkSolutionPlainTextEnter = (TextView) findViewById(R.id.checkSolutionPlainTextEnter);
        checkSolutionTextViewWrong = (TextView) findViewById(R.id.checkSolutionTextViewWrong);

        checkSolutionButtonCheckSolution = (Button) findViewById( R.id.checkSolutionButtonCheckSolution);
        checkSolutionButtonCheckSolution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean solutionCorrect = StateMachine.openSolution( checkSolutionPlainTextEnter.getText().toString() );
                if (!solutionCorrect)
                {
                    checkSolutionTextViewWrong.setTextColor(Color.RED);
                    checkSolutionTextViewWrong.setText("Deine Lösung war leider nicht koorekt! Versuche es nochmal, Sweety!");
                }
                else
                {
                    checkSolutionTextViewWrong.setText("");
                }
            }
        });

        checkSolutionButtonRiddle = (Button) findViewById( R.id.checkSolutionButtonRiddle);
        checkSolutionButtonRiddle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StateMachine.openRiddle();
            }
        });

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