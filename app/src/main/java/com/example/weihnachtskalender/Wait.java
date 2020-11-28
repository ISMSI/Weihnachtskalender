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

public class Wait extends AppCompatActivity {

    Context context;
    Activity activity;
    Button waitButtonAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait);

        context = this;
        activity = this;

        waitButtonAgain = (Button) findViewById(R.id.waitButtonAgain);

        waitButtonAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StateMachine.openNextRiddle();
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