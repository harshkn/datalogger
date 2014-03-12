package com.tud.cn.datalogger.helpers;

import com.tud.cn.datalogger.entity.Battery;

import android.content.Intent;
import android.os.BatteryManager;

public class BatteryHelper {
	
	
	
	 public Battery readBattery(Intent batteryStatus){
		 
		 Battery battery = new Battery();
		 
	    //are we charging / charged?
	    int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
	    boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL;

	    if(isCharging == true){
	    	battery.setIsCharging(battery.CHARGING);
	    }else{
	        battery.setIsCharging(battery.NOT_CHARGING);
	    }

	    //how are we charging
	    int chargePlug = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
	    boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
	    
	    @SuppressWarnings("unused")
		boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC; //Dummy- unused as of Now

	    if(usbCharge == true){
	        battery.setHowCharging(battery.USB_CHARGING);
	    }else{
	        battery.setHowCharging(battery.AC_CHARGING);
	    }

	    
	    int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
	    battery.setBatteryLevel(level + "%");
	    

	    //get battery temperature
	    int temp = batteryStatus.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1);
	    battery.setBatteryTemp(temp + "Degrees");
	   

	    //get battery voltage
	    int voltage = batteryStatus.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1);
	    battery.setBatteryVoltage(voltage + "V");
		return battery;
		}
		

}
