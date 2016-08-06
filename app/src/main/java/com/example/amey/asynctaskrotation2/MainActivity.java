package com.example.amey.asynctaskrotation2;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView listView;
    Nonuifragment fragment;
    ProgressBar progressBar;
    EditText editText;
    String url[] = {"http://herogamesworld.com/images/batman%20games%20online.jpg",
            "https://s.graphiq.com/sites/default/files/465/media/images/t2/Great_Dane_5206188.jpg"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        ArrayAdapter adapter=ArrayAdapter.createFromResource(this,R.array.images,android.R.layout.simple_list_item_1);
        editText= (EditText) findViewById(R.id.editText);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
/// test
        if(savedInstanceState==null)
        {
           fragment =new Nonuifragment();
            FragmentManager fm=getFragmentManager();
          FragmentTransaction ft= fm.beginTransaction().add(fragment,"nonui");
            ft.commit();
        }
        else
        {
            FragmentManager fm=getFragmentManager();
            fm.findFragmentByTag("nonui");

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        editText.setText(url[position]);
    }



    public void download(View view) {
        if((editText.getText().toString()!=null)&&(editText.getText().toString().length()>1))
        {

fragment.begintask(editText.getText().toString());
        }

    }


    public void showprogress(){

        if(fragment.mytask!=null)
        {
            Log.d("visibile","visi");
            if(progressBar.getVisibility()!=View.VISIBLE &&progressBar.getProgress()!=progressBar.getMax())
            {
                Log.d("visibile","sett");
                progressBar.setVisibility(View.VISIBLE);
            }
        }
    }

    public void hideprogress(){

        if(fragment.mytask!=null)
        {
            if(progressBar.getVisibility()==View.VISIBLE &&progressBar.getProgress()==progressBar.getMax())
            {
                progressBar.setVisibility(View.GONE);
            }
        }
    }



    public void updateprogress(Object values) {

        progressBar.setProgress((int)values);
    }
}
