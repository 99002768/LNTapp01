package com.example.lntapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG,"onCreate");
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
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"onstop");
    }
}