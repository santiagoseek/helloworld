package com.example.util;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;


public final class GetAction implements Runnable {
	/** ����ɹ���ϢID */
	private final static int MSG_TYPE_SUCCESS = 0x10;
	/** ����ʧ����ϢID */
	private final static int MSG_TYPE_FAILED = 0x20;
	/** �����쳣��ϢID */
	private final static int MSG_TYPE_EXCEPTION = 0x30;
	/** ����ɹ����ز����� */
	private final static String MSG_KEY_PARAM = "param";
	/** �����쳣���ز����� */
	private final static String MSG_KEY_EXCEPTION = "exception";
	/** Action ID */
	private int id;
	/** ����Ŀ��URL */
	private String url;
	/** Action���ؼ�����ʵ�� */
	private OnActionListener listener;
	/** ��Ϣ��� */
	private Handler handler;
	/** Get������� */
	private GetParam param;
	/**
	 * ���췽��
	 * @param actionId ���������̵߳�ActionID
	 * @param baseUrl ����URL�����Ƽ�����Get����
	 */
	public GetAction(int actionId, String baseUrl) {
		this.url = baseUrl;
		this.id = actionId;
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (msg.what == id) {
					switch (msg.arg1) {
					case MSG_TYPE_SUCCESS:
						// ��óɹ���Ϣ�����
						if (null != listener) {
							
							Log.i("halfman", "get (ResponseParam) msg.getData().getSerializable(MSG_KEY_PARAM)="+(ResponseParam) msg
									.getData().getSerializable(MSG_KEY_PARAM));
							
							listener.onActionSuccess(id, (ResponseParam) msg
									.getData().getSerializable(MSG_KEY_PARAM));
						}
						return;
					case MSG_TYPE_FAILED:
						// ���ʧ����Ϣ�����
						if (null != listener) {
							listener.onActionFailed(id, msg.arg2);
						}
						return;
					case MSG_TYPE_EXCEPTION:
						// ����쳣��Ϣ�����
						if (null != listener) {
							listener.onActionException(id, msg.getData()
									.getString(MSG_KEY_EXCEPTION));
						}
						return;
					}
				}
				super.handleMessage(msg);
			}
		};
	}
	/**
	 * ���ò���
	 * @param param
	 */
	public void setParam(GetParam param) {
		this.param = param;
	}
	/**
	 * ���ü�����ʵ��
	 * @param l
	 */
	public void setOnActionListener(OnActionListener l) {
		listener = l;
	}
	/**
	 * ����Action
	 */
	public void startAction() {
		// ����һ���̣߳�ִ�б���
		new Thread(this).start();
	}
	
	@Override
	public void run() {
		try {
			Log.d("halfman", "get param="+url + param.toString());
			HttpGet get = new HttpGet(url + param.toString());
			DefaultHttpClient client = new DefaultHttpClient();
			// ���ó�ʱ
			client.getParams().setParameter(
					CoreConnectionPNames.CONNECTION_TIMEOUT, 10000);
			client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
					10000);
			HttpResponse httpResponse = client.execute(get);
			int httpCode = httpResponse.getStatusLine().getStatusCode();
			// �ж�HTTP�����Ƿ�ɹ�����
			if (httpCode == HttpStatus.SC_OK) {
				String result =  EntityUtils.toString(httpResponse.getEntity());
				Log.d("halfman", "ret="+result);
				ResponseParam param = new ResponseParam(result);
				// ���ͷ��ز��������߳�
				Message msg = Message.obtain();
				msg.what = id;
				msg.arg1 = MSG_TYPE_SUCCESS;
				Bundle data = new Bundle();
				data.putSerializable(MSG_KEY_PARAM, param);
				msg.setData(data);
				handler.sendMessage(msg);
			} else {
				// HTTP״̬��Ϊʧ�ܵ����
				Message msg = Message.obtain();
				msg.what = id;
				msg.arg1 = MSG_TYPE_FAILED;
				msg.arg2 = httpCode;
				handler.sendMessage(msg);
			}
		} catch (Exception e) {
			// ��������з����쳣�����
			Message msg = Message.obtain();
			msg.what = id;
			msg.arg1 = MSG_TYPE_EXCEPTION;
			Bundle data = new Bundle();
			data.putString(MSG_KEY_EXCEPTION, e.getMessage());
			msg.setData(data);
			handler.sendMessage(msg);
		}
	}

}

