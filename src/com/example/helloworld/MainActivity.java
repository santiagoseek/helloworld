package com.example.helloworld;

import com.example.util.GetAction;
import com.example.util.GetParam;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {

	Button bt1 = null;
	TextView tv1 = null;
	Context context = null;
	
	private GetAction action;
	private GetParam  outParam;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// setContentView(R.layout.gridlayout);

		context = this;
		bt1 = (Button) findViewById(R.id.button1);
		tv1 = (TextView) findViewById(R.id.textView1);
		bt1.setOnClickListener(new View.OnClickListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				
				action = new GetAction(1, "http://m.weather.com.cn/data/101110101.html");
				outParam = new GetParam();
				action.setParam(outParam);
				Mylistener mylistener = new Mylistener(context);
				action.setOnActionListener(mylistener);
				
				action.startAction();

				
				tv1.setText("hello hello !!");
				/*
				 * AlertDialog.Builder builder = new
				 * AlertDialog.Builder(context);
				 * builder.setTitle("are you sure to leave ?"
				 * ).setIcon(R.drawable.ic_launcher).setPositiveButton("Yes",
				 * new DialogInterface.OnClickListener() {
				 * 
				 * @Override public void onClick(DialogInterface arg0, int arg1)
				 * { // TODO Auto-generated method stub
				 * showDialog("You have click the yes"); }
				 * }).setNegativeButton("cancle", new
				 * DialogInterface.OnClickListener() {
				 * 
				 * @Override public void onClick(DialogInterface arg0, int arg1)
				 * { // TODO Auto-generated method stub
				 * showDialog("You have click the cancle"); } });
				 * builder.create().show();
				 */

				/*
				 * final String[] mItems = { "item0", "item1", "itme2", "item3",
				 * "itme4", "item5", "item6" }; AlertDialog.Builder builder =
				 * new AlertDialog.Builder(context); builder.setTitle("列表选择框");
				 * builder.setItems(mItems, new
				 * DialogInterface.OnClickListener() { public void
				 * onClick(DialogInterface dialog, int which) {
				 * showDialog("你选择的id为" + which + " , " + mItems[which]); }; });
				 * builder.create().show();
				 */

				/*
				 * ProgressDialog mProgressDialog = new ProgressDialog(context);
				 * mProgressDialog.setTitle("读取ing");
				 * mProgressDialog.setMessage("正在读取中请稍候");
				 * mProgressDialog.setIndeterminate(true);
				 * mProgressDialog.setCancelable(true); mProgressDialog.show();
				 */

				
				/*final ProgressDialog mProgressDialog = new ProgressDialog(
						context);
				mProgressDialog.setIcon(R.drawable.ic_launcher);
				mProgressDialog.setTitle("进度条窗口");
				mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
				mProgressDialog.setMax(100);
				mProgressDialog.setButton("ok",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								showDialog("you click the ok " + whichButton);
							}
						});
				mProgressDialog.setButton2("cancle",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								showDialog("you click the " + whichButton);
							}
						});
				mProgressDialog.show();

				new Thread(new Runnable() {
					int Progress = 0;

					@Override
					public void run() {
						while (Progress < 100) {
							try {
								Thread.sleep(100);
								Progress++;
								mProgressDialog.incrementProgressBy(1);
							} catch (InterruptedException e) { // TODO Auto-generated
																// catch block
								e.printStackTrace();
							}
						}
						Progress = 0;
						mProgressDialog.dismiss();
					}
				}).start();*/
				 

				/*
				AlertDialog.Builder builder = new AlertDialog.Builder(context);
				LayoutInflater factory = LayoutInflater.from(context);
				View textEntryView = factory.inflate(R.layout.test, null);
				builder.setIcon(R.drawable.ic_launcher);
				builder.setTitle("input dialog");
				builder.setView(textEntryView);
				builder.setPositiveButton("ok",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								// TODO Auto-generated method stub
								// EditText userNameEditText =
								// textEntryView.findViewById(R.id.)
								showDialog("this is test");
								
							}
						});
				builder.create().show();
				*/
			}
		});
		

		/*
		 * Spinner spinner = new Spinner ( this ); spinner.setPrompt( "500" );
		 * String [ ] items = { "bam", "boo", "lab", "code", "programming",
		 * "framework", "android" }; ArrayAdapter array_adapter = new
		 * ArrayAdapter <String> ( this, android.R.layout.simple_spinner_item,
		 * items ); array_adapter.setDropDownViewResource (
		 * android.R.layout.simple_spinner_dropdown_item ); spinner.setAdapter (
		 * array_adapter ); int baseline = spinner.getBaseline ( );
		 * setContentView ( spinner );
		 */
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		MenuItem item1 = menu.add(1, 1, 1, "Item 1");
		item1.setIcon(R.drawable.ic_launcher);
		item1.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				showDialog("you click the Item 1");
				return false;
			}
		});

		return true;
	}

	private void showDialog(String str) {
		new AlertDialog.Builder(context).setMessage(str).show();
	}

}
