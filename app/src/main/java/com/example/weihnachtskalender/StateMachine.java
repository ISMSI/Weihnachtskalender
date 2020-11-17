package com.example.weihnachtskalender;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Properties;


public class StateMachine {
    SharedPreferences sharedPref;
    Properties loadedRiddle;
    Context context;

    public StateMachine (Context context) throws IOException {
        this.sharedPref = context.getSharedPreferences(
                "appState", Context.MODE_PRIVATE);
        this.context = context;
        loadedRiddle = new Properties();
    }

    public String readTest()
    {
        return loadedRiddle.getProperty("test2");
    }

    private void loadLastState()
    {
        String result;
        int riddleNo;
        result = loadStateString("number");

        if (result.equals("none"))
        {
            riddleNo = 0;
        }
        else
        {
            try
            {
                riddleNo = Integer.parseInt(result);
            }
           catch (NumberFormatException e)
           {
               riddleNo = 0;
               e.printStackTrace();
           }
        }
        if ( !riddleOpen(riddleNo) )
        {
            loadRiddle(riddleNo);
        }
    }

    private boolean riddleOpen( int riddleNo)
    {
        //TODO Check if a riddle is open and if it is the same as the questioned
        String riddleCurrentStr;
        int riddleCurrentInt;

        riddleCurrentStr = loadedRiddle.getProperty("number");
        try
        {
            riddleCurrentInt = Integer.getInteger(riddleCurrentStr);
            if (riddleNo == riddleCurrentInt)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (NumberFormatException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    private void loadRiddle( int number)
    {
        int riddle;
        switch (number)
        {
            case 1: riddle = R.raw.riddle1; break;
            case 2: riddle = R.raw.riddle2; break;
            default: riddle = R.raw.riddle0; break;
        }
        try
        {
            BufferedInputStream stream;
            stream = new BufferedInputStream(context.getResources().openRawResource(riddle));
            loadedRiddle.load(stream);
            stream.close();
        }
        catch(IOException iException)
        {
            iException.printStackTrace();
        }
    }

    public void openRiddle()
    {
        Intent intent = new Intent(context, RiddleText.class);
        context.startActivity(intent);
    }

    public void openCheckSolution()
    {
        Intent intent = new Intent(context, CheckSolution.class);
        context.startActivity(intent);
    }

    public void openSolution()
    {
        Intent intent = new Intent(context, Solution.class);
        context.startActivity(intent);
    }

    private boolean checkAccess ()
    {
        //date
        //letzes Raetsel geloest
        //oder adminPassword
        return false;
    }

    private void saveStateString(String key, String value)
    {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    private void saveStateInt(String key, int value)
    {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    private String loadStateString(String key)
    {
        return sharedPref.getString(key, "none");
    }

    private int loadStateInt(String key)
    {
        return sharedPref.getInt(key,-1);
    }

}
