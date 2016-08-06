package com.example.amey.asynctaskrotation2;

import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by amey on 8/6/2016.
 */

public class Mytask extends AsyncTask {
    int filelength;
    int progress;


    Activity activity;

    public Mytask(Activity activity) {
        onAttach(activity);


    }


    public void onAttach(Activity activity) {

        this.activity = activity;
    }

    public void onDetach() {

        activity = null;
    }


    @Override
    protected void onPreExecute() {
        if (activity == null) {
            Log.d("PreMytask","actiivitytnull");

        } else {
            Log.d("PreMytask","actiivitynotnull");
            ((MainActivity) activity).showprogress();

        }
    }
    @Override
    protected Object doInBackground(Object[] url) {
        HttpURLConnection Connection = null;
        InputStream inputStream = null;
        FileOutputStream fos = null;
        File file;
        File folder;
        int count = 1;
        boolean success = false;
        try {
            URL downloadurl = new URL(url[0].toString());

            Connection = (HttpURLConnection) downloadurl.openConnection();
            //    Connection.connect();
            filelength = Connection.getContentLength();
            inputStream = Connection.getInputStream();
            byte buffer[] = new byte[2048];
            // file=getFilesDir();

            folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);


            file = new File(folder, "" + Uri.parse(url[0].toString()).getLastPathSegment());

            count++;
            //  fos=openFileOutput("",MODE_PRIVATE);
            Log.d("ab", "" + file.getAbsolutePath());
            fos = new FileOutputStream(file);
            Log.d("ed", "" + folder.getAbsolutePath());
            Log.d("cd", "" + file.getAbsolutePath());
            int read = 0;
            while ((read = inputStream.read(buffer)) != -1) {


                fos.write(buffer, 0, read);
                progress = progress + read;

                publishProgress((int) (((double) progress / filelength) * 100));
                Log.d("progress", "" + (int) (((double) progress / filelength) * 100));
                //text.setText("" + ((double) progress / filelength) * 100 + "%");

//fos.write(Buffer,0,read);
                // read=inputStream.read();
                //fos.write(read);
                //Log.d("amey", "" + read);

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {


            if (Connection != null) {
                Connection.disconnect();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            success = true;
        }
        return success;


    }

    @Override
    protected void onProgressUpdate(Object[] values) {
        if (activity == null) {

Log.d("Mytask","actiivitynull");
        } else {
            Log.d("Mytask","actiivitynotnull");
            ((MainActivity) activity).updateprogress(values[0]);

        }
    }

    @Override
    protected void onPostExecute(Object o) {
        if (activity == null) {


        } else {
            ((MainActivity) activity).hideprogress();
        }
    }
}

