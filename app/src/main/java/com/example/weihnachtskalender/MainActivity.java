package com.example.weihnachtskalender;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView mainTextPlain;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
}