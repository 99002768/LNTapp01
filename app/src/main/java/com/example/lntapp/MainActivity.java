package com.example.lntapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lntapp.database.DbAccessObj;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName() ;
    DbAccessObj dbAccessObj;
    public static final String MYPREFS = "myprefs";
    public static final String NAMEKEY = "namekey";
    public static final String PWDKEY = "pwdkey";
    EditText nameEditText,pwdEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG,"onCreate");
        dbAccessObj = new DbAccessObj(this);
        dbAccessObj.openDb();
        nameEditText =  findViewById(R.id.Name);
        pwdEditText = findViewById(R.id.password);
    }

    public void clickhandler(View view) {
//        Toast.makeText(this, "hii thrinath", Toast.LENGTH_SHORT).show();
//        EditText text;
//        text=findViewById(R.id.Name);
//        String thrinath=text.getText().toString();

//        TextView thrinath;
//        thrinath=findViewById(R.id.thrinath);
//        thrinath.setText(input);
        switch (view.getId()){
            case R.id.button:
                System.out.println("added");
                startmethod();
                break;
            case R.id.button2:
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("tel:12345678 "));
                startActivity(intent);

                break;
        }

    }

    private void startmethod() {
        Intent intent=new Intent(MainActivity.this, HomeActivity.class);
        intent.putExtra("mykey","sfdgjsfg");
        int c=add(10,20);
// intent.putExtra("mykey",thrinath);
        startActivity(intent);
    }

    private int add(int i, int i1) {
        return  0;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"onstart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"onPause");
        saveData();
    }
    /**
     * this method will save data from edittexts into a sharedprefs
     */
    private void saveData() {
        Log.i(TAG,"saveData");

        //get the data from the edittext
        String name = nameEditText.getText().toString();
        String pwd = pwdEditText.getText().toString();
        //create a file names myprefs

        SharedPreferences preferences = getSharedPreferences(MYPREFS,MODE_PRIVATE);
        //open the file
        SharedPreferences.Editor editor = preferences.edit();
        //write to the file
        editor.putString(NAMEKEY,name);
        editor.putString(PWDKEY,pwd);
        //save the file
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"onResume");
        restoreData();
    }

    private void restoreData() {
        Log.i(TAG,"restoreData");

        //open the file
        SharedPreferences preferences = getSharedPreferences(MYPREFS,MODE_PRIVATE);
        //read the file
        String name = preferences.getString(NAMEKEY,"");
        String pwd = preferences.getString(PWDKEY,"");
        //set the data in edittexts
        nameEditText.setText(name);
        pwdEditText.setText(pwd);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"onstop");
    }
    public void handleDb(View view) {
        switch (view.getId()){
            case R.id.buttonput:
                String title = nameEditText.getText().toString();
                String subtitle = pwdEditText.getText().toString();

                dbAccessObj.createRow(title,subtitle);
                break;
            case R.id.buttonget:
                //get the data from db
                String data =  dbAccessObj.readRow();
                //set the data onto textview
                TextView dbTextView = findViewById(R.id.textViewdb);
                dbTextView.setText(data);
                break;
        }
    }
}