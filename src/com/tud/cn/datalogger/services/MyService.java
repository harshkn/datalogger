package com.tud.cn.datalogger.services;

import java.util.List;

import com.tud.cn.datalogger.Utilities.FileUtility;
import com.tud.cn.datalogger.entity.Battery;
import com.tud.cn.datalogger.entity.NetworkState;
import com.tud.cn.datalogger.helpers.BatteryHelper;
import com.tud.cn.datalogger.helpers.CPUUsageHelper;
import com.tud.cn.datalogger.helpers.NetworkHelper;
import com.tud.cn.datalogger.helpers.TaskListHelper;

import android.app.ActivityManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {

	TelephonyManager telephonyManager;

	public MyService() {
		Log.d("Service", "Constructor");
	}

	@Override
	public IBinder onBind(Intent arg0) {
		throw new UnsupportedOperationException("Not yet implemented");

	}

	@Override
	public void onCreate() {
		Log.d("Service", "onCreate");
		/*
		 * TODO: To move this to Activity later, providing option for user to
		 * choose what data to log
		 */
		/*************** Battery Receiver Registration ********************/
		this.registerReceiver(this.batteryReceiver, new IntentFilter(
				Intent.ACTION_BATTERY_CHANGED));

		/*************** Network Connectivity Receiver Registration *******************************/
		this.registerReceiver(this.networkStateReceiver, new IntentFilter(
				ConnectivityManager.CONNECTIVITY_ACTION));

		telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		telephonyManager.listen(phoneStateListener,
				PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);

		Toast.makeText(this, "Reciever was created", Toast.LENGTH_LONG).show();

	}

	/*
	 * Receives a battery change intent and updates the battery value in the
	 * file
	 */
	/* TODO: To move this to custom Broadcast Receivers later */
	private BroadcastReceiver batteryReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context arg0, Intent intent) {
			
			Log.d("Battery", "IntentRecieved!");
			BatteryHelper batteryHelper = new BatteryHelper();
			Battery batteryValue = batteryHelper.readBattery(intent);
			Log.d("Battery", batteryValue.toString());
			FileUtility fileUtility = new FileUtility();

			
			boolean writeStatus = fileUtility.writeFileToSDCard(
					batteryValue.toString(), "TestDir");
			ActivityManager mgr = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
			TaskListHelper taskListHelper = new TaskListHelper(20);
			List<ActivityManager.RunningTaskInfo> allTasks = taskListHelper
					.getRunningTaskInfo(mgr);
			for (ActivityManager.RunningTaskInfo aTask : allTasks) {
				Log.d("TaskList", "Task: " + aTask.baseActivity.getClassName());
				writeStatus = fileUtility.writeFileToSDCard("\r\n" + aTask.baseActivity.getClassName(), "TestDir");

			}

			CPUUsageHelper cpuUsageHelper = new CPUUsageHelper();
			float usage = cpuUsageHelper.getUsage();
			int cores = cpuUsageHelper.getNumCores();
			Log.d("CPU", "Usage : " + usage);
			Log.d("CPU", "Cores : " + cores);
			writeStatus = fileUtility.writeFileToSDCard("\r\n" + "Usage : " + usage + " Cores : " + cores, "TestDir");

			/****************************************************/

			/*fileUtility.writeFileToSDCard("\r\n\r\n\r\n\r\n", "TestDir");*/
			Log.d("FileWriterService", "File is written into " + writeStatus);

		}
	};

	/*
	 * Receives a Network change intent and updates the network state into a
	 * file
	 */
	/* TODO: To move this to custom Broadcast Receivers later */
	BroadcastReceiver networkStateReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			FileUtility fileUtility = new FileUtility();
			Log.d("Network", "Network Type Changed");
			NetworkHelper networkHelper = new NetworkHelper();
			NetworkState networkState = networkHelper.readNetworkState(context);
			Log.d("Network", networkState.toString());
			fileUtility.writeFileToSDCard("\r\n" + "Network : " + networkState.toString(), "TestDir");
		}
	};

	PhoneStateListener phoneStateListener = new PhoneStateListener() {
		@Override
		public void onSignalStrengthsChanged(SignalStrength signalStrength) {
			FileUtility fileUtility = new FileUtility();
			super.onSignalStrengthsChanged(signalStrength);
			Log.d("Signal",
					"GSM Signal Strength = "
							+ String.valueOf(signalStrength
									.getGsmSignalStrength()));
			fileUtility.writeFileToSDCard("\r\n" + "GSM Signal Strength : " + String.valueOf(signalStrength
																							.getGsmSignalStrength()), 
																											"TestDir");
		}
	};

	@Override
	public void onStart(Intent intent, int startId) {
		// For time consuming an long tasks you can launch a new thread here...
		Log.d("Service", "onStart");
		Toast.makeText(this, " Service Started", Toast.LENGTH_LONG).show();

	}

	@Override
	public void onDestroy() {
		Log.d("Service", "onDestroy");
		this.unregisterReceiver(this.batteryReceiver);
		this.unregisterReceiver(this.networkStateReceiver);
		telephonyManager.listen(phoneStateListener,
				PhoneStateListener.LISTEN_NONE);
		Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();

	}

}
