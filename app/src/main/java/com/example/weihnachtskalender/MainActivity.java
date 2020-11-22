package com.example.weihnachtskalender;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView mainTextPlain;

    Context context;
    Activity activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        activity = this;

        StateMachine.loadPrefAndRiddle(this);


            button = (Button) findViewById( R.id.mainActivityButtonLos);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    test();
                    StateMachine.openRiddle();
                }
            });
        if (!StateMachine.isFirstOpen())
        {
            StateMachine.openRiddle();
        }

    }

    public void openActivity()
    {
        Intent intent = new Intent(this, CheckSolution.class);
        startActivity(intent);
    }

    private void test()
    {

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