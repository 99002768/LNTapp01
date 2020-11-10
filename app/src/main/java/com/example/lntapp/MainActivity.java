package com.example.lntapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
        Log.e(TAG,"onCreate");
    }

    public void clickhandler(View view) {
//        Toast.makeText(this, "hii thrinath", Toast.LENGTH_SHORT).show();
        EditText text;
        text=findViewById(R.id.Name);
        String thrinath=text.getText().toString();

//        TextView thrinath;
//        thrinath=findViewById(R.id.thrinath);
//        thrinath.setText(input);
        Intent intent=new Intent(MainActivity.this,HomeActivity.class);
//       intent.putExtra("mykey","sfdgjsfg");
 intent.putExtra("mykey",thrinath);
        startActivity(intent);
    }
}