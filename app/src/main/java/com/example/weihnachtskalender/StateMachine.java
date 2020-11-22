package com.example.weihnachtskalender;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TimeZone;


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
        loadLastState();

    }

    static public String getRiddleString(String key)
    {
        if (loadedRiddle == null)
        {
            System.out.println("Riddle property is null");
        }
        String tmp = loadedRiddle.getProperty(key);
        if (tmp != null) {
            System.out.println(tmp);
        }
        else
        {
            System.out.println("NULL");
        }
        return tmp;
    }

    static public String readTest()
    {
        return "test2";//loadedRiddle.getProperty("test2");
    }

    static private int getCurrentRiddleNo()
    {
        int riddleNo;
        riddleNo = loadStateInt("number");

        return  riddleNo;
    }

    static private void loadLastState()
    {
        int riddleNo = getCurrentRiddleNo();

        if ( !riddleIsOpen(riddleNo) )
        {
            loadRiddle(riddleNo);
        }
    }

    static public void openRiddle()
    {
        int riddleNo = getCurrentRiddleNo();
        loadRiddle((riddleNo));
        Intent intent = new Intent(context, RiddleText.class);
        context.startActivity(intent);
    }

    static public void openNextRiddle()
    {
        int riddleNo = getCurrentRiddleNo();
        if (riddleNo <= 23)
        {
            // Choose time zone in which you want to interpret your Date
            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
            int day = cal.get(Calendar.DAY_OF_MONTH);
            System.out.println(day);

            if (day >= riddleNo)
            {
                loadRiddle((riddleNo+1));
                Intent intent = new Intent(context, RiddleText.class);
                context.startActivity(intent);

            }
            else
            {
                openWait();
            }

        }
        else
        {
            openDone();
        }

    }

    static public boolean isFirstOpen ()
    {
        if (getCurrentRiddleNo() <= 1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    static private boolean riddleIsOpen( int riddleNo)
    {
        //TODO Check if a riddle is open and if it is the same as the questioned
        String riddleCurrentStr;
        int riddleCurrentInt;

        riddleCurrentStr = loadedRiddle.getProperty("number");
        if (riddleCurrentStr == null)
        {
            return false;
        }
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

        System.out.println("Riddle number is: " + number);
        switch (number)
        {
            case 2: riddle = R.raw.riddle2; break;
            case 3: riddle = R.raw.riddle3; break;
            case 20: riddle = R.raw.riddle20; break;
            default: riddle = R.raw.riddle1; break;
        }
        try
        {
            BufferedInputStream stream;
            stream = new BufferedInputStream(context.getResources().openRawResource(riddle));
            loadedRiddle.load(stream);
            stream.close();
            saveStateInt("number", Integer.parseInt(getRiddleString("number")));
        }
        catch(IOException iException)
        {
            iException.printStackTrace();
        }
    }

    static public void openWait()
    {

    }

    static public void openDone()
    {

    }

    static public void openCheckSolution()
    {
        Intent intent = new Intent(context, CheckSolution.class);
        context.startActivity(intent);
    }

    static public boolean openSolution(String solution)
    {
        if (!accessGranted(solution))
        {
            return false;
        }
        Intent intent = new Intent(context, Solution.class);
        context.startActivity(intent);
        return true;
    }

    static private boolean accessGranted(String solution)
    {
        if (solution.equals("Blender"))
        {
            /*Admin key*/
            return true;
        } else if (!solution.equals(loadedRiddle.getProperty("solution")))
        {
            /*Wrong solution*/
            return false;
        }
        /*Correct Solution*/
        return true;
    }

    static private void saveStateString(String key, String value)
    {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    static public void saveStateInt(String key, int value)
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
        return sharedPref.getInt(key,0);
    }

    static public void reset(Context currContext)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(currContext);
        builder.setMessage("Bist du dir sicher?");
        builder.setTitle("Kalender zurücksetzen?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                saveStateInt("number", 0);
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
            }
        });
        builder.setNegativeButton("NEIN", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    static public void setDay(Activity currActivity)
    {

        /*DialogChooseDay.Builder builder = new DialogChooseDay.Builder(currContext);
        builder.setView(R.layout.dialog_choose_day);
        builder.setMessage("Wähle den gewünschten Tag aus.");
        builder.setTitle("Tag auswählen?");
        DialogChooseDay dialog = builder.create();
        dialog.show();*/

        DialogChooseDay dialog = new DialogChooseDay(currActivity);
        dialog.show();

    }

}
