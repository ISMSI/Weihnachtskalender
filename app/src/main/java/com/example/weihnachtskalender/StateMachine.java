package com.example.weihnachtskalender;

import android.content.Context;
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
            riddleNo=0;
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
        checkOpenRiddle(riddleNo);
        loadRiddle(riddleNo);
    }

    private void checkOpenRiddle( int riddleNo)
    {
        //TODO Check if a riddle is open and if it is the same as the questioned
    }

    private void saveCurrentState()
    {

    }

    private void loadRiddle( int number)
    {
        try
        {
            BufferedInputStream stream;
            stream = new BufferedInputStream(context.getResources().openRawResource(R.raw.riddle0));
            loadedRiddle.load(stream);
            stream.close();
        }
        catch(IOException iException)
        {
            iException.printStackTrace();
        }
    }

    public void openRiddle( int number)
    {

    }

    public void openCheckSolution( int number)
    {

    }

    public void openSolution( int number)
    {

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

    private String loadStateString(String key)
    {
        return sharedPref.getString(key, "none");
    }

}
