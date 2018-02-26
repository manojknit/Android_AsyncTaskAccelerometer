# Android app to demonstrate AsyncTask behavior of android app
####                                                                                                     By Manoj Kumar
## Introduction 
Android app to demonstrate AsyncTask behavior of android. You can use AsyncTask for any threading requirement. 


## How to Run
1.	Prerequisite: Android Studio, some Java knowledge
2.	Download or clone project code and open in Android studio.
3.	Run in Nexus 5X API 26 emulator.


## Technologies Used
1.	Java.
2.	Android Studio


## Application Code and Screenshots
#### Invocation


```
   public void Accelerometer(int limit)
	{
		mlt = new MyLongTask1(this.mReportTo, this.mContext,"Accelerometer", limit);
		mlt.execute("String1","String2","String3");
	}
	public void CancelAccelerometer()
	{
		mlt.cancel(true);
	}
```

#### AsyncTask implementation class.
<img src="images/MyLongTask1.java - Android_AsyncTaskAccelerometer - [~DesktopProjectsAndroidStudioProjectsAndroid_AsyncTaskAccelerometer] 2018-02-25 19-14-43.png">

## Code
```
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

public class BaseTester 
{
	protected IReportBack mReportTo;
	protected Context mContext;

	public BaseTester(Context ctx, IReportBack target)
	{
		mReportTo = target;
		mContext = ctx;

	}
}

public interface IReportBack 
{
	public void reportBack(String tag, String message);
	//public void reportTransient(String tag, String message);
}
```
## Refrence
- [*Pro Android 5*](https://github.com/Apress/pro-android-5) by Dave MacLean, Satya Komatineni, and Grant Allen (Apress, 2015)

## Thank You
#### [*Manoj Kumar*](https://www.linkedin.com/in/manojkumar19/)
#### Solutions Architect
