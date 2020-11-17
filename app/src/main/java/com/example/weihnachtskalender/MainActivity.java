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


    StateMachine stateMachine;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try
        {
            stateMachine = new StateMachine(this);
        } catch (IOException ioException)
        {
            ioException.printStackTrace();
            stateMachine = null;
        }

        button = (Button) findViewById( R.id.mainActivityButtonLos);
        mainTextPlain = (TextView) findViewById (R.id.mainPlainText);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test();
                stateMachine.openRiddle();
            }
        });
    }

    public void openActivity()
    {
        Intent intent = new Intent(this, CheckSolution.class);
        startActivity(intent);
    }

    private void test()
    {
        mainTextPlain.setText("Ich liebe dich, Sweety <3");
    }
}