package com.example.helloworld;

import android.content.Context;
import android.widget.Toast;

import com.example.util.OnActionListener;
import com.example.util.ResponseParam;

public class Mylistener implements OnActionListener {

	private Context context;

	public Mylistener(Context context){
		this.context = context;
	}
	@Override
	public void onActionSuccess(int actionId, ResponseParam ret) {
		// TODO Auto-generated method stub

		Toast.makeText(context, ret.getString("cityid"), Toast.LENGTH_LONG).show();
	}

	@Override
	public void onActionFailed(int actionId, int httpStatus) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onActionException(int actionId, String exception) {
		// TODO Auto-generated method stub

	}

}
