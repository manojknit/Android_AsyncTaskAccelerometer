package com.cloudjibe.android_asynctaskaccelerometer;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;

import java.util.Random;

public class MyLongTask1 extends AsyncTask<String,Integer,Integer> implements OnCancelListener
{
	IReportBack r;
	Context ctx;
	public String tag = null;
	int Limit = 0;
	ProgressDialog pd = null;
	MyLongTask1(IReportBack inr, Context inCtx, String inTag, int limit)
	{
		r = inr;
		ctx = inCtx;
		tag = inTag;
		Limit = limit;
	}
	protected void onPreExecute()
	{
	}
    protected void onProgressUpdate(Integer... progress) 
    {
		//Runs on the main ui thread
		Utils.logThreadSignature(this.tag);

		//will be called multiple times
		//triggered by onPostExecute
    	Integer i1 = progress[0];
		Integer i2 = progress[1];
		Integer i3 = progress[2];
		Integer i = progress[3];

		EditText eX=(EditText)((MainActivity)ctx).findViewById(R.id.etX);
		eX.setText(String.valueOf(i1));
		EditText eY=(EditText)((MainActivity)ctx).findViewById(R.id.etY);
		eY.setText(String.valueOf(i2));
		EditText eZ=(EditText)((MainActivity)ctx).findViewById(R.id.etZ);
		eZ.setText(String.valueOf(i3));

    	r.reportBack("Simulation Count: "+String.valueOf(i+1), "X:" + i1.toString()+", Y:"+i2.toString()+", Z:"+i3.toString());
    	//pd.setProgress(i);
    }     
    protected void onPostExecute(Integer result) 
    {
		//Runs on the main ui thread
		Utils.logThreadSignature(this.tag);
    }
    protected Integer doInBackground(String...strings)
    {
		//Runs on a worker thread
    	//May even be a pool if there are 
    	//more tasks.
		Utils.logThreadSignature(this.tag);

    	for (int i=0;i<Limit;i++)
    	{
    		Utils.sleepForInSecs(1);
			int iX = Utils.getRendomNumber();
			int iY = Utils.getRendomNumber();
			int iZ = Utils.getRendomNumber();
    		publishProgress(iX, iY, iZ, i);
    	}
    	return 1;
    }

    public void onCancel(DialogInterface d)
    {
    	r.reportBack(tag,"Cancel Called");
    	this.cancel(true);
    }

}
