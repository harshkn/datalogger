package com.tud.cn.datalogger.entity;

public class NetworkState {

	public final String CONNECTED = "CONNECTED";
	public final String WIFI = "CONNECTED TO WIFI";
	public final String MOBILE_DATA = "CONNECTED TO MOBILE DATA";
	public final String NOT_CONNECTED = "NOT CONNECTED";
	public final String MOBILE_TYE_3G = "3G";
	public final String MOBILE_TYE_2G = "2G";
	public final String MOBILE_TYE_OTHER = "OTHER";

	String connectionType = null;
	String mDataConnectionType = null;

	/**
	 * 
	 */
	public NetworkState() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param connectionType
	 * @param mDataConnectionType
	 */
	NetworkState(String isConnected, String connectionType,
			String mDataConnectionType) {
		super();
		this.connectionType = connectionType;
		this.mDataConnectionType = mDataConnectionType;
	}

	/**
	 * @return the connectionType
	 */
	public String getConnectionType() {
		return connectionType;
	}

	/**
	 * @param connectionType
	 *            the connectionType to set
	 */
	public void setConnectionType(String connectionType) {
		this.connectionType = connectionType;
	}

	/**
	 * @return the mDataConnectionType
	 */
	public String getmDataConnectionType() {
		return mDataConnectionType;
	}

	/**
	 * @param mDataConnectionType
	 *            the mDataConnectionType to set
	 */
	public void setmDataConnectionType(String mDataConnectionType) {
		this.mDataConnectionType = mDataConnectionType;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NetworkState [connectionType=" + connectionType
				+ "]";

		//return "NetworkState [connectionType=" + connectionType
			//	+ ", mDataConnectionType=" + mDataConnectionType + "]";
	}
	
	

}
