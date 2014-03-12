package com.tud.cn.datalogger.Utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import android.os.Environment;
import android.util.Log;

public class FileUtility {
	public boolean writeFileToSDCard(String output, String path) {

		try {
			File root = new File(Environment.getExternalStorageDirectory(),
					path);

			if (!root.exists()) {
				Log.d("FileWriter", "Creating Directory");
				root.mkdirs();

			} else {
				Log.d("FileWriter", "Directory Already Exists!");
			}
			Log.d("FileWriter", "Creating a file");
			File gpxfile = new File(root, "TestingFileWriter.txt");

			BufferedWriter bW;

			bW = new BufferedWriter(new FileWriter(gpxfile, true));
			Log.d("FileWriter", "Writing to File!");
			bW.write(output);
			bW.newLine();
			bW.flush();
			bW.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		/*
		 * OutputStreamWriter out = null; File directory =
		 * Environment.getExternalStoragePublicDirectory(path);
		 * if(!directory.exists()){
		 * Log.d("FileWriter","Directory does not exist, Creating!");
		 * directory.mkdirs(); } else{
		 * Log.d("FileWriter","Directory already Exists!"); }
		 * 
		 * boolean fileExists = new File(directory,
		 * "TestFileAndroid.txt").isFile(); if(fileExists){
		 * Log.d("FileWriter","File exists"); } else{
		 * 
		 * Log.d("FileWriter","File does not exist");
		 * 
		 * }
		 * 
		 * try{ File file = new File(directory, "TestFileAndroid.txt"); out =
		 * new OutputStreamWriter(new FileOutputStream(file));
		 * out.append(output); out.flush(); out.close(); return true; }
		 * catch(Exception e){ e.printStackTrace(); return false; }
		 */

	}

	public String readFileFromSDCard(String fileName) {
		File fullPath = Environment.getExternalStoragePublicDirectory(fileName);
		File file = new File(fullPath, "TestFileAndroid.txt");

		if (!file.exists()) {
			throw new RuntimeException("File not found on sdcard");
		}
		BufferedReader reader = null;
		StringBuilder builder = new StringBuilder();
		try {
			reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return builder.toString();
	}
}
