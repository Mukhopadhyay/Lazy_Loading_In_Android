package com.example.lazyloadingofflineimplementation;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * This class is to define the functions which are required in the
 * main application, such as loading the data into the arraylist
 * creating random strings of variable length, etc
 *
 */
public class UtilityClass {

    String type;
    String name;
    String random;
    String date;
    Date dateObject;

    /**
     *
     * @param objects
     * @param number
     * Loads random data into the ArrayList
     */
    public void setArrayList(ArrayList<Objects> objects, int number) {

        dateObject = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");


        for(int i=0;i<number;i++) {
            int temp = (int) (Math.random() * 10);

            //Randomize type
            if(temp <= 3 && temp >0) {
                type = "phone";
            }
            else if(temp > 3 && temp <= 7) {
                type = "mail";
            }
            else {
                type = "message";
            }


            name = getRandomText(6);
            random = getRandomText(10);
            date = simpleDateFormat.format(dateObject);


            objects.add(new Objects(type, name, random, date));

        }

    }

    /**
     *
     * @param objects
     * @param number
     * @param descriptionString
     * Same as previous one, but here the third argument will be
     * displayed as the description section in the View
     */
    public void setArrayList(ArrayList<Objects> objects, int number, String descriptionString) {

        dateObject = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");


        for(int i=0;i<number;i++) {
            int temp = (int) (Math.random() * 10);

            //Randomize type
            if(temp <= 3 && temp >0) {
                type = "phone";
            }
            else if(temp > 3 && temp <= 7) {
                type = "mail";
            }
            else {
                type = "message";
            }


            name = getRandomText(6);
            random = descriptionString;
            date = simpleDateFormat.format(dateObject);


            objects.add(new Objects(type, name, random, date));

        }
    }


    /**
     * @param number
     * @return
     * Generates random Strings sequence
     */
    public String getRandomText(int number) {

        String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"+
                "abcdefghijklmnopqrstuvwwxyz"+"0123456789";

        StringBuilder stringBuilder = new StringBuilder(number);

        for(int i=0;i<number;i++) {
            int index = (int) (alphaNumericString.length() * Math.random());

            stringBuilder.append(alphaNumericString.charAt(index));
        }

        return stringBuilder.toString();
    }

    /**
     *
     * @param time
     * @param number
     * @param objects
     * @param myAdapter
     *
     * Pauses the application for 'time' amount of miliseconds and
     * loads objects into the ArrayList and finally notifies the adapter
     */
    public void fetchDate(int time, int number, ArrayList<Objects> objects, MyAdapter myAdapter) {
        try {

            Thread.sleep(time);
            setArrayList(objects, number, "Lazy loaded objects!");
            myAdapter.notifyDataSetChanged();

        }
        catch (Exception e) {
            Log.d("Error",e.getMessage());
        }
    }
}
