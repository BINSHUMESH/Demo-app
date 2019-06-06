package com.test.bms.demoapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    TextView language;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_layout,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId()){
            case R.id.english:
                sharedPreferences.edit().putString("key","English").apply();
                language.setText(sharedPreferences.getString("key",""));
                return true;
            case R.id.spanish:
                sharedPreferences.edit().putString("key","Spanish").apply();
                language.setText(sharedPreferences.getString("key",""));
                return true;
                default:
                    return false;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        language=findViewById(R.id.language);
        sharedPreferences=this.getSharedPreferences("com.test.bms.demoapp",Context.MODE_PRIVATE);

        if(sharedPreferences.getString("key",null)==null) {
            new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_info)
                    .setTitle("Choose Language")
                    .setMessage("Which Language you want to choose?")
                    .setPositiveButton("English", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            sharedPreferences.edit().putString("key", "English").apply();
                            language.setText(sharedPreferences.getString("key", ""));
                        }
                    })
                    .setNegativeButton("Spanish", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            sharedPreferences.edit().putString("key", "Spanish").apply();
                            language.setText(sharedPreferences.getString("key", ""));
                        }
                    }).show();
        }
        else
        {
            language.setText(sharedPreferences.getString("key", ""));
        }
    }

}
