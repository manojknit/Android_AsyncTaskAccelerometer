package com.cloudjibe.android_asynctaskaccelerometer;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements IReportBack {

    public static final String tag="AsyncTaskDriverActivity";
    private AsyncTester asyncTester = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        asyncTester = new AsyncTester(this, this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onGenerateClick(View view) {
        EditText e=(EditText)findViewById(R.id.etSimulationCount);
        int limit = Integer.parseInt(e.getText().toString());
        asyncTester.Accelerometer(limit);

    }
    public void reportBack(String tag, String message)
    {
        this.appendText(tag + "\n" + message);
        Log.d(tag,message);
    }
    private TextView getTextView(){
        return (TextView)this.findViewById(R.id.tvSimulationLog);
    }
    private void emptyText(){
        TextView tv = getTextView();
        tv.setText("Simulation Log");
    }
    private void appendText(String s){
        TextView tv = getTextView();
        tv.setText(tv.getText() + "\n" + s);
        Log.d(tag,s);
        tv.setMovementMethod(new ScrollingMovementMethod());
    }

    public void onCancelClick(View view) {
        emptyText();
        EditText eX=(EditText)findViewById(R.id.etX);
        eX.setText("");
        EditText eY=(EditText)findViewById(R.id.etY);
        eY.setText("");
        EditText eZ=(EditText)findViewById(R.id.etZ);
        eZ.setText("");


        asyncTester.CancelAccelerometer();
    }

    @Override
    protected void onDestroy(){
        
        //you may call the cancel() method but if it is not handled in doInBackground() method
        if (asyncTester.mlt != null && asyncTester.mlt.getStatus() != AsyncTask.Status.FINISHED)
            asyncTester.mlt.cancel(true);
        super.onDestroy();
    }
}
