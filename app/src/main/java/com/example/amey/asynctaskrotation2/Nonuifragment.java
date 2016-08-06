package com.example.amey.asynctaskrotation2;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by amey on 8/6/2016.
 */
public class Nonuifragment extends Fragment {
    Activity activity;
    Mytask mytask;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        setRetainInstance(true);


    }

    public void begintask(String url) {
        mytask = new Mytask(activity);
Log.d("begintask",""+mytask);

        mytask.execute(url);
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.activity = (Activity) context;
        Log.d("onAttach",""+this.activity);

        if (mytask != null) {

            Log.d("onAttach",""+this.activity);
            mytask.onAttach(activity);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mytask != null) {
            mytask.onDetach();
        }
    }
}