package com.tud.cn.datalogger.helpers;

import java.util.List;

import android.app.ActivityManager;

public class TaskListHelper {

	int showLimit = 0;
	
	

	/**
	 * 
	 */
	TaskListHelper() {
		super();
		this.showLimit = 10;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param showLimit
	 */
	public TaskListHelper(int showLimit) {
		super();
		this.showLimit = showLimit;
	}

	/**
	 * @return the showLimit
	 */
	public int getShowLimit() {
		return showLimit;
	}

	/**
	 * @param showLimit
	 *            the showLimit to set
	 */
	public void setShowLimit(int showLimit) {
		this.showLimit = showLimit;
	}

	public List<ActivityManager.RunningTaskInfo> getRunningTaskInfo(
			ActivityManager mgr) {
		List<ActivityManager.RunningTaskInfo> allTasks = mgr
				.getRunningTasks(showLimit);
		return allTasks;
	}

	/*
	 * for (ActivityManager.RunningTaskInfo aTask : allTasks) { Log.d("MyApp",
	 * "Task: " + aTask.baseActivity.getClassName());
	 * 
	 * }
	 */

}
