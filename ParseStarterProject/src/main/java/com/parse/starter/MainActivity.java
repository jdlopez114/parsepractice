/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*
        ParseObject score = new ParseObject("Score"); // make new parse object
        score.put("username", "rob");  // save new variable called username with value of rob as new object within the Score class
        score.put("score", 86); //automatically saves type
        score.saveInBackground(new SaveCallback() {
            // save in background is standard save, save eventually save when have opportunity
            @Override
            public void done(ParseException e) {

                if (e == null) { // look for error, if there isnt one
                    Log.i("SaveInBackground", "Successful");

                } else {
                    Log.i("SaveInBackground", "Failed" + e.toString());

                }
            }
        });

        */

//        ParseQuery<ParseObject> query = ParseQuery.getQuery("Score"); //put the class that you want the query on
//        query.getInBackground("s8Wf2wb3Wq", new GetCallback<ParseObject>() { // every object has object ID parse
//            @Override
//            public void done(ParseObject object, ParseException e) {
//
//                if (e == null && object != null){
//
//                    object.put("score", 200); //updates score
//                    object.saveInBackground();
//
//                    Log.i("ObjectValue", object.getString("username"));
//                    Log.i("ObjectValue", Integer.toString(object.getInt("score")));
//
//
//                }
//
//            }
//        });

        //create tweet class, username and tweet, save on parse, then query it and update tweet content

//        ParseObject tweet = new ParseObject("Tweet");
//
//        tweet.put("username", "tommy");
//        tweet.put("tweet", "hey there!");
//
//        tweet.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//                if (e == null) { //check if no error
//
//                    Log.i("Tweet", "Successful");
//                } else {
//                    Log.i("Tweet", "Failed");
//                }
//            }
//        });

//        ParseQuery<ParseObject> query = ParseQuery.getQuery("Tweet");
//
//        query.getInBackground("31JLxCNCMA", new GetCallback<ParseObject>() { // every object has object ID parse
//            @Override
//            public void done(ParseObject object, ParseException e) {
//
//                if (e == null && object != null){
//
//                    Log.i("Tweet", "Successful");
//
//                    object.put("tweet", "Bye!");
//                    object.saveInBackground();
//
//                } else {
//                    Log.i("Tweet", "Failed");
//                }
//            }
//        });


        ParseQuery<ParseObject> query = ParseQuery.getQuery("Score"); // add search criteria to query if you want to get score

        query.whereGreaterThan("score", 250);
        query.setLimit(2); // returns maxmimum of 1 score

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {  // calls back list

                if (e == null) {  // find out how many objects have been returned

                    Log.i("FindInBackground", "Retrived" + objects.size() + "objects");
                    if (objects.size() > 0) {

                        for (ParseObject object : objects) { // parse object called object for each item in objects
                            object.put("score", object.getInt("score") + 50);
                            object.saveInBackground();
                        }
                    }
                }
            }
        });


        ParseAnalytics.trackAppOpenedInBackground(getIntent());

    }
}
