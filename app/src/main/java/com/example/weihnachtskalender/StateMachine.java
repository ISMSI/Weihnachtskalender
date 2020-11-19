package com.example.weihnachtskalender;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.annotation.Nullable;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.Properties;
import java.util.Set;


public class StateMachine implements Serializable {
    static SharedPreferences sharedPref;
    static Properties loadedRiddle;
    static Context context;

    static public void loadPrefAndRiddle (Context extContext)
    {
        sharedPref = extContext.getSharedPreferences(
                "appState", Context.MODE_PRIVATE);
        loadedRiddle = new Properties();
        context = extContext;
    }

    static public String readTest()
    {
        return "test2";//loadedRiddle.getProperty("test2");
    }

    static private void loadLastState()
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

    static private boolean riddleOpen( int riddleNo)
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

    static private void loadRiddle( int number)
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

    static public void openRiddle()
    {
        Intent intent = new Intent(context, RiddleText.class);
        context.startActivity(intent);

    }

    static public void openCheckSolution()
    {
        Intent intent = new Intent(context, CheckSolution.class);
        context.startActivity(intent);
    }

    static public boolean openSolution(String solution)
    {
        if (!solution.equals("Blender"))
        {
            return false;
        }

        Intent intent = new Intent(context, Solution.class);
        context.startActivity(intent);
        return true;
    }

    static private boolean checkAccess ()
    {
        //date
        //letzes Raetsel geloest
        //oder adminPassword
        return false;
    }

    static private void saveStateString(String key, String value)
    {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    static private void saveStateInt(String key, int value)
    {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    static private String loadStateString(String key)
    {
        return sharedPref.getString(key, "none");
    }

    static private int loadStateInt(String key)
    {
        return sharedPref.getInt(key,-1);
    }

}
