package com.example.lntapp;
import android.provider.CallLog;
import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.CursorAdapter;
import com.example.lntapp.database.DbAccessObj;
import com.example.lntapp.database.FeedReaderContract.FeedEntry;

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
        nameEditText =  findViewById(R.id.Name);
        pwdEditText = findViewById(R.id.password);
        dbAccessObj = new DbAccessObj(this);
        dbAccessObj.openDb();
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
                getCredentials();
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
        Log.i(TAG,"onStart");
        ListView dbListView = findViewById(R.id.dblist);
//        Uri uriSms = Uri.parse("content://sms/inbox");
//        Cursor dataCursor =  getContentResolver().query(uriSms,null,null,null,null);
        // Cursor dataCursor = dbAccessObj.getRows();
        //put the data into adapter
        // Uri uriSms = Uri.parse("content://sms/inbox");
        Uri callOgUri = CallLog.Calls.CONTENT_URI;
        Cursor dataCursor =  getContentResolver().query(callOgUri,null,null,null,null);
        CursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.row_listview,
                dataCursor,
                //new String[]{"body","address"},
                //FeedEntry.COLUMN_NAME_TITLE,FeedEntry.COLUMN_NAME_SUBTITLE},
                //"title","subtitle"},
                new String[]{CallLog.Calls.NUMBER,CallLog.Calls.CACHED_NAME},
                new int[] {R.id.textviewRow,R.id.textViewsubtitle});
        //set the adapter onto the listview
        dbListView.setAdapter(adapter);
    }

    private void getCredentials() {
        dbAccessObj.query(nameEditText.getText().toString());
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

    private void startHome() {
        Intent hIntent = new Intent(MainActivity.this, HomeActivity.class);
        hIntent.putExtra("mykey","abdul");
        int c = add(10,20);
        startActivity(hIntent);
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