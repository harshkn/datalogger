package com.tud.cn.datalogger.entity;

/**
 * @author harshkn
 *
 */
public class Battery{
	
	public final String CHARGING = "CHARGING";
	public final String NOT_CHARGING = "NOT CHARGING";
	public final String USB_CHARGING = "USB CHARGING";
	public final String AC_CHARGING = "AC CHARGING";
	
	String isCharging;
	String howCharging;
	String batteryLevel;
	String batteryTemp;
	String batteryVoltage;
	/**
	 * @param isCharging
	 * @param howCharging
	 * @param batteryTemp
	 * @param batteryVoltage
	 * @param batteryLevel
	 */
	public Battery() {
		super();
		this.isCharging = null;
		this.howCharging = null;
		this.batteryTemp = null;
		this.batteryVoltage = null;
		this.batteryLevel = null;
	}
	
	Battery(String isCharging, String howCharging, String batteryTemp,
			String batteryVoltage, String batteryLevel) {
		super();
		this.isCharging = isCharging;
		this.howCharging = howCharging;
		this.batteryTemp = batteryTemp;
		this.batteryVoltage = batteryVoltage;
		this.batteryLevel = batteryLevel;
	}

	/**
	 * @return the isCharging
	 */
	public String getIsCharging() {
		return isCharging;
	}

	/**
	 * @param isCharging the isCharging to set
	 */
	public void setIsCharging(String isCharging) {
		this.isCharging = isCharging;
	}

	/**
	 * @return the howCharging
	 */
	public String getHowCharging() {
		return howCharging;
	}

	/**
	 * @param howCharging the howCharging to set
	 */
	public void setHowCharging(String howCharging) {
		this.howCharging = howCharging;
	}

	/**
	 * @return the batteryTemp
	 */
	public String getBatteryTemp() {
		return batteryTemp;
	}

	/**
	 * @param batteryTemp the batteryTemp to set
	 */
	public void setBatteryTemp(String batteryTemp) {
		this.batteryTemp = batteryTemp;
	}

	/**
	 * @return the batteryVoltage
	 */
	public String getBatteryVoltage() {
		return batteryVoltage;
	}

	/**
	 * @param batteryVoltage the batteryVoltage to set
	 */
	public void setBatteryVoltage(String batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

	/**
	 * @return the batteryLevel
	 */
	public String getBatteryLevel() {
		return batteryLevel;
	}

	/**
	 * @param batteryLevel the batteryLevel to set
	 */
	public void setBatteryLevel(String batteryLevel) {
		this.batteryLevel = batteryLevel;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Battery [isCharging=" + isCharging + ", howCharging="
				+ howCharging + ", batteryLevel=" + batteryLevel
				+ ", batteryTemp=" + batteryTemp + ", batteryVoltage="
				+ batteryVoltage + "]";
	}
	
	
	
	
	
	
}
