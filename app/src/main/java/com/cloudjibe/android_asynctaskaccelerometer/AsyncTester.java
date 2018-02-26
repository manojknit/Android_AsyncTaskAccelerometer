package com.cloudjibe.android_asynctaskaccelerometer;

import android.content.Context;

public class AsyncTester extends BaseTester 
{
	private static String tag = "AsyncTester1";
	public MyLongTask1 mlt = null;
	AsyncTester(Context ctx, IReportBack target)
	{
		super(ctx, target);
	}


	//All of this should execute on one worker thread
	//All inputs are processed by the same thread for this task
//	public void test1()
//	{
//		MyLongTask mlt = new MyLongTask(this.mReportTo,this.mContext,"Task1");
//		mlt.execute("String1","String2","String3");
//
//	}
	
	//This is to observer thread behavior when multiple
	//tasks are issued by a client
//	public void test3()
//	{
//		MyLongTask mlt = new MyLongTask(this.mReportTo,this.mContext,"Task1");
//		mlt.execute("String1","String2","String3");
//
//		MyLongTask mlt1 = new MyLongTask(this.mReportTo,this.mContext,"Task2");
//		mlt1.execute("String1","String2","String3");
//	}
	public void Accelerometer(int limit)
	{
		mlt = new MyLongTask1(this.mReportTo, this.mContext,"Accelerometer", limit);
		mlt.execute("String1","String2","String3");
	}
	public void CancelAccelerometer()
	{
		mlt.cancel(true);
	}

}
