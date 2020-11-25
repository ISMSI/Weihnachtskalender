package com.example.weihnachtskalender;

import androidx.annotation.DrawableRes;
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
import android.widget.ImageView;
import android.widget.TextView;

public class RiddleImage extends AppCompatActivity {

    TextView riddleImageQuestion;
    Button riddleImageButtonSolution;
    ImageView riddleImageView;
    Context context;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riddle_image);

        context = this;
        activity = this;

        riddleImageQuestion = (TextView) findViewById (R.id.riddleImageQuestion);
        riddleImageQuestion.setText(StateMachine.getRiddleString("question"));

        riddleImageView = (ImageView) findViewById( R.id.riddleImageView);
        int id = context.getResources().getIdentifier(
                "drawable/"+StateMachine.getRiddleString("content_name"),
                null,
                context.getPackageName());
        if (id <= 0)
        {
            riddleImageView.setImageResource(R.drawable.christmas_tree);
        }
        riddleImageView.setImageResource(id);

        riddleImageButtonSolution = (Button) findViewById( R.id.riddleImageButtonSolution);
        riddleImageButtonSolution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StateMachine.openCheckSolution();
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
        switch (item.getItemId())
        {
            case R.id.itemReset: StateMachine.reset(context); break;
            case R.id.itemChooseDay: StateMachine.setDay(activity); break;
        }
        return super.onOptionsItemSelected(item);
    }
}