package com.tud.cn.datalogger;

import java.io.File;

import com.tud.cn.datalogger.services.MyService;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	// Start the service
	public void startNewService(View view) {
		Log.d("Activity", "Start Clicked!");
		startService(new Intent(this, MyService.class));
	}

	// Stop the service
	public void stopNewService(View view) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		AlertDialog.Builder alert = new AlertDialog.Builder(this);

		alert.setTitle("Email:");
		alert.setMessage("The data logger file is stored at the path : /storage/sdcard/TestDir/TestingFileWriter.txt \n If the file has to be mailed ,  Enter the email Id for the file to be sent");

		// Set an EditText view to get user input
		final EditText input = new EditText(this);
		alert.setView(input);
		
		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				Editable value = input.getText();
				System.out.println(value.toString());
				Intent email = new Intent(Intent.ACTION_SEND);
				email.putExtra(Intent.EXTRA_EMAIL, value.toString());
				email.putExtra(Intent.EXTRA_SUBJECT, "Data logger");
				File sd = Environment.getExternalStorageDirectory();
				File logFile = new File(sd, "TestDir"+"/"
						+ "TestingFileWriter.txt");

				email.putExtra(
						Intent.EXTRA_STREAM,
						Uri.fromFile(logFile));
				email.putExtra(Intent.EXTRA_TEXT,
						"Here is the data logger file containing your mobile log info");
				email.setType("text/plain");
				startActivity(Intent.createChooser(email,
						"Choose an Email client :"));
				// Write a code to send Email
			}
		});

		alert.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						// Simple close it without doing anything
					}
				});

		alert.show();

		stopService(new Intent(this, MyService.class));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
