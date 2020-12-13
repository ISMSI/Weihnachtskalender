package com.example.weihnachtskalender;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class DialogChooseDay extends Dialog
{

    TextView dialogChooseDayNo;
    TextView dialogChooseDayPassword;
    Button dialogChooseDayButtonOk;
    Button dialogChooseDayButtonNo;
    public Activity c;

    public DialogChooseDay(Activity a) {
        super(a);

        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_choose_day);

        dialogChooseDayNo = (TextView) findViewById(R.id.dialogChooseDayNr);
        dialogChooseDayPassword = (TextView) findViewById(R.id.dialogChooseDayPassword);

        dialogChooseDayButtonOk = (Button) findViewById( R.id.dialogChooseDayButtonOk);
        dialogChooseDayButtonOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int number = -1;
                    try
                    {
                        number = Integer.parseInt( dialogChooseDayNo.getText().toString());

                        if(dialogChooseDayPassword.getText().toString().equals("Blender")) {
                            StateMachine.saveStateInt("number", number);
                            StateMachine.openRiddleAdmin();
                            dismiss();
                        }
                        else
                        {

                        }
                    }
                    catch (NumberFormatException e)
                    {
                        e.printStackTrace();
                        dismiss();
                    }


                }
            });

        dialogChooseDayButtonNo = (Button) findViewById( R.id.dialogChooseDayButtonNo);
        dialogChooseDayButtonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}