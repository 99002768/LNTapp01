package com.example.lntapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    String[] languages;
    private static final String TAG = HomeActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent intent=getIntent();
        Log.w(TAG,"onCreate");
        languages = new String[]{"english","hindi","urdu","kannada"};
        Bundle extras=intent.getExtras();
        if(extras!= null) {
            String data = extras.getString("mykey");
            TextView textView = findViewById(R.id.home1);
            textView.setText(data);

        }
        ListView countriesListVie = findViewById(R.id.countriesListview);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
//            android.R.layout.simple_list_item_1, //layout file of each row in the listview
                R.layout.row_listview,
                languages);
        countriesListVie.setAdapter(adapter);
    }

}