package com.tud.cn.datalogger.helpers;

import com.tud.cn.datalogger.entity.NetworkState;

import android.content.Context;
import android.net.ConnectivityManager;
import android.widget.Toast;

public class NetworkHelper {
	
	public NetworkState readNetworkState(Context context){
		
		NetworkState networkState = new NetworkState();
		
		final ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		final android.net.NetworkInfo wifi = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		final android.net.NetworkInfo mobile = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

	    if( wifi.isAvailable() ){
	  	    networkState.setConnectionType(networkState.WIFI);
	    	Toast.makeText(context, "Wifi" , Toast.LENGTH_LONG).show();
	    }
	    else if( mobile.isAvailable() ){
	    	
	    	networkState.setConnectionType(networkState.MOBILE_DATA);
	    	Toast.makeText(context, "Cell Data " , Toast.LENGTH_LONG).show();
	    }
	    else
	    {
	    	
	    	networkState.setConnectionType(networkState.NOT_CONNECTED);
	    	Toast.makeText(context, "No Network " , Toast.LENGTH_LONG).show();
	    }
	    return networkState;
		
	}

}
