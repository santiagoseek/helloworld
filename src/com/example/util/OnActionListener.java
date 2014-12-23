package com.example.util;

public interface OnActionListener {
	/**
	 * ����ɹ�
	 * @param actionId
	 * @param ret
	 */
	public void onActionSuccess(int actionId, ResponseParam ret);
	/**
	 * ����http���ش���
	 * @param actionId
	 * @param httpStatus
	 */
	public void onActionFailed(int actionId, int httpStatus);
	/**
	 * �����쳣
	 * @param actionId
	 * @param exception
	 */
	public void onActionException(int actionId, String exception);
}
